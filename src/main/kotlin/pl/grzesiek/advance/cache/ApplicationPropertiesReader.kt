package pl.grzesiek.advance.cache

import java.io.FileInputStream
import java.nio.file.Paths
import java.util.*

class ApplicationPropertiesReader {

    private val globalConfigFileName = "application.properties"

    fun loadConfig(): Properties {
        val applicationPropertiesFilePath = this.javaClass.classLoader.getResource(globalConfigFileName).path

        val path = Paths.get(applicationPropertiesFilePath)
        val file = FileInputStream(path.toFile())
        val prop = Properties()
        prop.load(file)

        return prop
    }
}