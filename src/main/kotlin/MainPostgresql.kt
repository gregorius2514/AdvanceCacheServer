import AdvanceCacheProperties.*
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jooq.Record
import org.jooq.RecordMapper
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.jooq.impl.DSL.field
import org.jooq.impl.DSL.table


fun main() {
   val properties = DatabasePoolInitializer.initPool()
    
    val hikariPool = HikariConfig()
    hikariPool.jdbcUrl = properties.getProperty(DATABASE_JDBC_URL.propertyName)
    hikariPool.username = properties.getProperty(DATABASE_USERNAME.propertyName)
    hikariPool.password = properties.getProperty(DATABASE_PASSWORD.propertyName)
    hikariPool.dataSourceClassName = properties.getProperty(DATABASE_SOURCE_CLASS_NAME.propertyName)

    // TODO extract that logic to separate class
    val dialect = properties.getProperty(AdvanceCacheProperties.DATABASE_DIALECT.propertyName).toLowerCase()
    val databaseDialect = when (dialect) {
        "postgres" -> SQLDialect.POSTGRES
        else -> SQLDialect.DEFAULT
    }

    val dataSource = HikariDataSource(hikariPool)
    val dbContext = DSL.using(dataSource, databaseDialect)

    val result = dbContext.select()
        .from(table("app_user"))
        .fetch(UserRecordMapper())

    println("results: $result")


    val resultCode = dbContext
        .insertInto(table("app_user"))
        .columns(
            field("firstname"),
            field("lastname"),
            field("age"),
            field("gender")
        )
        .values("Joanna", "Kalwat", 18, "F")
        .execute()
    
    println("result code: $resultCode")

}

class UserRecordMapper : RecordMapper<Record, User> {
    override fun map(record: Record): User {
        val userId = record["id"] as Integer
        val firstname = record["firstname"] as String
        val lastname = record["lastname"] as String
        val age = record["age"] as Integer
        val gender = record["gender"] as String

        return User(userId.toLong(), firstname, lastname, age, gender[0])
    }

}

data class User(
    val id: Long,
    val firstname: String,
    val lastname: String,
    val age: Integer,
    val gender: Char
)

