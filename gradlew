#!/usr/bin/env bash
set -e
ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
WRAPPER_DIR="$ROOT_DIR/gradle/wrapper"
VERSION=8.6
DIST_URL="https://services.gradle.org/distributions/gradle-${VERSION}-bin.zip"
LOCAL_DIST_DIR="$ROOT_DIR/.gradle/gradle-${VERSION}"

if [ -x "$WRAPPER_DIR/gradle-wrapper.jar" ]; then
  java -cp "$WRAPPER_DIR/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
  exit $?
fi

echo "Gradle wrapper jar not available; bootstrapping Gradle ${VERSION} distribution..."
mkdir -p "$ROOT_DIR/.gradle"
if [ ! -d "$LOCAL_DIST_DIR" ]; then
  TMPZIP="$ROOT_DIR/.gradle/gradle-${VERSION}.zip"
  echo "Downloading $DIST_URL ..."
  curl -fSL "$DIST_URL" -o "$TMPZIP" || { echo "Failed to download $DIST_URL"; exit 1; }
  echo "Extracting to $LOCAL_DIST_DIR ..."
  mkdir -p "$LOCAL_DIST_DIR"
  unzip -q "$TMPZIP" -d "$ROOT_DIR/.gradle"
  # extracted folder name is gradle-<version>
  rm -f "$TMPZIP"
fi

GRADLE_BIN="$LOCAL_DIST_DIR/bin/gradle"
if [ ! -x "$GRADLE_BIN" ]; then
  echo "Gradle binary not found: $GRADLE_BIN";
  exit 1
fi

exec "$GRADLE_BIN" "$@"
