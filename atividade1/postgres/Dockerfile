FROM postgres
ENV POSTGRES_DB atividade1-dac
ENV POSTGRES_USER paulo
ENV POSTGRES_PASSWORD 123

COPY create.sql /docker-entrypoint-initdb.d/
COPY insert.sql /docker-entrypoint-initdb.d/
