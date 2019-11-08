import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jooq.Record
import org.jooq.RecordMapper
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.jooq.impl.DSL.table
import java.sql.DriverManager
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime

fun main() {
    val hikariPool = HikariConfig()
    hikariPool.jdbcUrl = "jdbc:postgresql://localhost:5432/postgres"
    hikariPool.username = "postgres"
    hikariPool.password = "postgres"
    hikariPool.dataSourceClassName = "org.postgresql.ds.PGSimpleDataSource"
    
    val dataSource = HikariDataSource(hikariPool)
    val dbContext = DSL.using(dataSource, SQLDialect.POSTGRES)

    val result = dbContext.select()
        .from(table("account"))
        .fetch(AccountRecordMapper())    
    
    println("results: $result")
}

class AccountRecordMapper : RecordMapper<Record, Account> {
    override fun map(record: Record): Account {
        val userId = record["user_id"] as Integer
        val username = record["username"] as String 
        val password = record["password"] as String
        val email = record["email"] as String 
        val createdOn = record["created_on"] as Timestamp
        
       return Account(userId.toLong(), username, password, email, createdOn.toLocalDateTime())
    }

}

data class Account(val userId: Long, 
                   val username: String,
                   val password: String,
                   val email: String,
                   val createdOn: LocalDateTime
)