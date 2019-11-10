import AdvanceCacheProperties.*
import com.mongodb.MongoClient

fun main() {
    val properties = DatabasePoolInitializer.initPool()

    MongoClient(properties.getProperty(MONGO_HOSTNAME.propertyName)).use { client ->
        val database = client.getDatabase(MONGO_DATABASE_NAME.propertyName)
        val test = database.getCollection(MONGO_COLLECTION_NAME.propertyName)

        test.find().forEach { e -> println("elemnt : $e") }
    }
}