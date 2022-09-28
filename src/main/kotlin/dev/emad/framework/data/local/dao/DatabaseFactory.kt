package dev.emad.framework.data.local.dao

import dev.emad.framework.data.local.model.LocalAudios
import dev.emad.framework.data.local.model.LocalUsers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun create(): Database {
        val debugMode = System.getenv("DEBUG_MODE").toBoolean()
        val database: Database = if (debugMode) {
            val driverClassName = "org.h2.Driver"
            val jdbcURL = "jdbc:h2:file:./build/db"
            Database.connect(jdbcURL, driverClassName)
        } else {
            val driverClassName = "org.postgresql.Driver"
            val dbHost: String = System.getenv("POSTGRES_DB_HOST") ?: "db"
            val dbName: String = System.getenv("POSTGRES_DB_NAME") ?: "pollux-db"
            val dbPort: String = System.getenv("POSTGRES_DB_PORT") ?: "5432"
            val dbUser: String = System.getenv("POSTGRES_DB_USER") ?: "postgres"
            val dbPassword: String = System.getenv("POSTGRES_DB_PASSWORD") ?: ""
            val jdbcURL = "jdbc:postgresql://${dbHost}:${dbPort}/${dbName}"
            Database.connect(jdbcURL, driverClassName, user = dbUser, password = dbPassword)
        }
        transaction(database) {
            SchemaUtils.create(LocalUsers)
            SchemaUtils.create(LocalAudios)
        }
        return database
    }
}
