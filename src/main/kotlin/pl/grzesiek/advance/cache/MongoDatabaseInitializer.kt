package pl.grzesiek.advance.cache

import com.mongodb.MongoClient
import com.mongodb.client.MongoCollection
import org.bson.Document

class MongoDatabaseInitializer : DatabaseContextInitializer<MongoCollection<Document>> {

    override fun createDatabaseSource(applicationPropertiesReader: ApplicationPropertiesReader): MongoCollection<Document> {
        val prop = applicationPropertiesReader.loadConfig()

        val client = MongoClient(prop.getProperty(MongoDbProperties.MONGO_HOSTNAME.propertyName))
        val database = client.getDatabase(MongoDbProperties.MONGO_DATABASE_NAME.propertyName)
        return database.getCollection(MongoDbProperties.MONGO_COLLECTION_NAME.propertyName)
    }
}

private enum class MongoDbProperties(val propertyName: String) {
    MONGO_HOSTNAME("databaseHostname"),
    MONGO_DATABASE_NAME("databaseName"),
    MONGO_COLLECTION_NAME("collectionName")
}
