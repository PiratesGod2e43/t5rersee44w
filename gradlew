#!/bin/sh
set -e
if ! command -v gradle >/dev/null 2>&1; then
  echo "Gradle is not installed. Install Gradle 9.2.1 or use the included GitHub Actions workflow." >&2
  exit 1
fi
exec gradle "$@"
