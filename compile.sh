#!/bin/sh

# Determine path separator based on OS
if [ "$(uname)" = "Linux" ] || [ "$(uname)" = "Darwin" ]; then
    SEP=":"
else
    SEP=";"
fi

# Variables for directories and files
SRC_DIR="src"
BUILD_DIR="build"
LIB_DIR="lib"
MAIN_CLASS="src.PointSalad"

# Create the build directory if it doesn't exist
mkdir -p "$BUILD_DIR"

# Create a classpath string that includes all .jar files in the lib directory
CLASSPATH=$(find "$LIB_DIR" -name "*.jar" | tr '\n' "$SEP")

# Compile the Java source files
javac -d "$BUILD_DIR" -sourcepath "$SRC_DIR" -cp "$CLASSPATH" $(find "$SRC_DIR" -name "*.java")

if [ $? -eq 0 ]; then
    echo "Compilation successful."
else
    echo "Compilation failed."
fi