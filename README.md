# Proof Of Conecpt

This is a proof of concept to use the jsonb type from Postgres.
I use mvn 3.6, micronaut 1.3.1, hibernate, flyway and postgres 10.12. 

# Postgres

```sh
docker run -it --rm \         
    -p 5432:5432 \
    -e POSTGRES_USER=my_user \
    -e POSTGRES_PASSWORD=geheim \
    -e POSTGRES_DB=my_database \
    postgres:10.12
```
