#!/usr/bin/env bash
javapackager -createjar -appclass package.ClassName \
    -srcdir out/production \
    -outdir package \
    -outfile outjar -v