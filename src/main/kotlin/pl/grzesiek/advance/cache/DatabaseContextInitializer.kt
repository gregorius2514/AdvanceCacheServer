package pl.grzesiek.advance.cache

import org.jooq.DSLContext


interface DatabaseContextInitializer<T> {

    fun createDatabaseSource(applicationPropertiesReader: ApplicationPropertiesReader): T
}
