FROM ubuntu:latest
LABEL authors="diais"

ENTRYPOINT ["top", "-b"]