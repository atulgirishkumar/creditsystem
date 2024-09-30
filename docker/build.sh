#!/bin/bash

# Store the current directory
working_dir=$(pwd)

# Remove any existing fat JAR files from postservice and emailservice directories
rm -f "$working_dir"*.jar

# Build and package graviton
cd ..
if ! mvn clean install; then
    echo "Failed to build graviton"
    exit 1
fi
graviton_jar=$(basename $(find target -name "*-fat.jar"))
cp "target/$graviton_jar" "$working_dir"


cd "$working_dir"
echo "$graviton_jar"
ls

docker build \
  --build-arg GRAVITON_ARTIFACT="$graviton_jar" \
  -t graviton-app:latest .