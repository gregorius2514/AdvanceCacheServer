import java.io.FileInputStream
import java.nio.file.Paths
import java.util.*

object DatabasePoolInitializer {
    fun initPool(): Properties {
        val applicationPropertiesFilePath = this.javaClass.classLoader.getResource("application.properties").path

        val path = Paths.get(applicationPropertiesFilePath)
        if (!path.toFile().exists()) {

        }
        val file = FileInputStream(path.toFile())
        val prop = Properties()

        prop.load(file)
        return prop
    }
}

enum class AdvanceCacheProperties(val propertyName : String) {
    DATABASE_JDBC_URL("databaseJdbcUrl"),
    DATABASE_USERNAME("databaseUsername"),
    DATABASE_PASSWORD("databasePassword"),
    DATABASE_SOURCE_CLASS_NAME("databaseSourceClassName"),
    DATABASE_DIALECT("databaseDialect"),
    MONGO_HOSTNAME("databaseHostname"),
    MONGO_DATABASE_NAME("databaseName"),
    MONGO_COLLECTION_NAME("collectionName")
}


