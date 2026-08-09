#!/usr/bin/env bash
set -euo pipefail
# Generates a temporary TestNG suite XML from test classes under src/test/java
# (including nested folders such as testCase1/..../testCase4) and runs `mvn test`
# using that suite (similar to how IntelliJ creates a temp testng XML to run all tests).

ROOT="$(cd "$(dirname "$0")" && pwd)"
TMPXML="$ROOT/target/generated-testng.xml"
mkdir -p "$(dirname "$TMPXML")"

echo "Scanning for test classes under src/test/java..."
# Produce fully-qualified class names from paths, e.g. src/test/java/testCase1/NoUsername.java -> testCase1.NoUsername
# Use a portable loop instead of readarray (macOS bash may be older and lacks readarray)
classes=()
while IFS= read -r -d '' f; do
  classes+=("$f")
done < <(find "$ROOT/src/test/java" \( -name '*Test.java' -o -name '*.java' \) -print0 2>/dev/null || true)

if [ ${#classes[@]} -eq 0 ]; then
  echo "No test classes found in src/test/java. Exiting."
  exit 1
fi

# Normalize to fully-qualified names
for i in "${!classes[@]}"; do
  p="${classes[$i]}"
  # remove prefix
  p="${p#$ROOT/src/test/java/}"
  # remove .java and convert slashes to dots
  p="${p%.java}"
  p="${p//\//.}"
  classes[$i]="$p"
done

echo "Generating TestNG suite at: $TMPXML"
cat > "$TMPXML" <<EOF
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Generated Suite">
  <test name="All Tests">
    <classes>
EOF

for c in "${classes[@]}"; do
  echo "      <class name=\"$c\">" >> "$TMPXML"
done

cat >> "$TMPXML" <<EOF
    </classes>
  </test>
</suite>
EOF

echo "Wrote $TMPXML with ${#classes[@]} classes"
echo
echo "Running Maven tests using the generated suite..."
echo "If Maven/Surefire doesn't pick up the suite, try passing the property name 'surefire.suiteXmlFiles' explicitly as below."
echo
echo "Command: mvn -Dsurefire.suiteXmlFiles=\"$TMPXML\" test"
echo

# Run mvn with the generated suite XML. Forward any arguments to mvn.
mvn -Dsurefire.suiteXmlFiles="$TMPXML" test "$@"

