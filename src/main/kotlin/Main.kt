import com.mongodb.MongoClient

fun main() {
    val client = MongoClient("localhost")
    
    val database = client.getDatabase("advance-cache")
    
    val test = database.getCollection("test")
    test.find().forEach { e -> println("elemnt : $e")}
    
    
    client.close()
}