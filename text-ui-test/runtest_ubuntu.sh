#!/usr/bin/env bash
# Runs the regression test with $JAVA_HOME modified such that it will work even if the
# default Java version is different.

cd "$(dirname "$0")"
JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/ ./runtest.sh
