#!/usr/bin/env bash
javapackager -deploy -native \
-BsystemWide=true \
-BjvmOptions=-Xmx128m \
-BjvmOptions=-Xms128m \
-outdir packages \
-outfile PokerTest \
-srcdir ./src \
-appclass com.asolod.test.poker.Main