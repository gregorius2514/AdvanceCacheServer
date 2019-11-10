package pl.grzesiek.advance.cache

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL

object PostgresDatabaseContextInitializer : DatabaseContextInitializer<DSLContext> {

    override fun createDatabaseSource(applicationPropertiesReader: ApplicationPropertiesReader): DSLContext {
        val prop = applicationPropertiesReader.loadConfig()

        val hikariPool = HikariConfig()
        hikariPool.jdbcUrl = prop.getProperty(PostgresDatabaseConfigProperties.DATABASE_JDBC_URL.propertyName)
        hikariPool.username = prop.getProperty(PostgresDatabaseConfigProperties.DATABASE_USERNAME.propertyName)
        hikariPool.password = prop.getProperty(PostgresDatabaseConfigProperties.DATABASE_PASSWORD.propertyName)

        val datasource = HikariDataSource(hikariPool)
        return DSL.using(datasource, SQLDialect.POSTGRES)
    }
}

private enum class PostgresDatabaseConfigProperties(val propertyName: String) {
    DATABASE_JDBC_URL("databaseJdbcUrl"),
    DATABASE_USERNAME("databaseUsername"),
    DATABASE_PASSWORD("databasePassword"),
}

