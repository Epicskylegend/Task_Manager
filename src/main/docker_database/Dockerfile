FROM debian:bookworm-slim

ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update
RUN apt-get install -y postgresql-client-15

# clean up
RUN apt-get clean \
        && rm -rf /var/lib/apt/lists/*

ADD --chmod=0777 ./DatabaseSetup.sh /usr/local/bin/DatabaseSetup.sh
ADD --chmod=0777 ./Init.sql /usr/local/bin/Init.sql