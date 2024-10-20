#!/bin/sh

# Determine path separator based on OS
if [ "$(uname)" = "Linux" ] || [ "$(uname)" = "Darwin" ]; then
    SEP=":"
else
    SEP=";"
fi

# Variables for directories and files
BUILD_DIR="build"
LIB_DIR="lib"
MAIN_CLASS="src.PointSalad"
CLASSPATH=$(find "$LIB_DIR" -name "*.jar" | tr '\n' "$SEP")

# Run the main class
echo "Running client for $MAIN_CLASS..."
java -cp "$BUILD_DIR$SEP$CLASSPATH" "$MAIN_CLASS" 127.0.0.1