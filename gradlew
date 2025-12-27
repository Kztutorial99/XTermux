#!/usr/bin/env bash
set -e
ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
WRAPPER_DIR="$ROOT_DIR/gradle/wrapper"
JAR="$WRAPPER_DIR/gradle-wrapper.jar"
VERSION=8.6
MAVEN_URL="https://repo1.maven.org/maven2/org/gradle/gradle-wrapper/${VERSION}/gradle-wrapper-${VERSION}.jar"

if [ ! -f "$JAR" ]; then
  echo "gradle wrapper jar not found, downloading from Maven Central..."
  mkdir -p "$WRAPPER_DIR"
  curl -fsSL "$MAVEN_URL" -o "$JAR"
  if [ $? -ne 0 ] || [ ! -f "$JAR" ]; then
    echo "Failed to download gradle-wrapper.jar from $MAVEN_URL"
    exit 1
  fi
fi

java -cp "$JAR" org.gradle.wrapper.GradleWrapperMain "$@"
