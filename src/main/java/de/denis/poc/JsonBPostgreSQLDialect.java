package de.denis.poc;

import org.hibernate.dialect.PostgreSQL10Dialect;

import java.sql.Types;

public class JsonBPostgreSQLDialect extends PostgreSQL10Dialect {
    public JsonBPostgreSQLDialect() {
        super();
        registerColumnType(Types.JAVA_OBJECT, "jsonb");
    }
}
