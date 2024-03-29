package dev.emad.di

import dev.emad.business.service.SearchService
import dev.emad.business.service.SeederService
import dev.emad.business.service.UserService
import dev.emad.framework.data.local.dao.AudioDao
import dev.emad.framework.data.local.dao.DatabaseFactory
import dev.emad.framework.data.local.dao.UserDao
import dev.emad.framework.data.remote.service.SearchServiceImpl
import dev.emad.framework.data.remote.service.SeederServiceImpl
import dev.emad.framework.data.remote.service.UserServiceImpl
import dev.emad.framework.data.repository.AudioRepository
import dev.emad.framework.data.repository.AudioRepositoryImpl
import dev.emad.framework.data.repository.UserRepository
import dev.emad.framework.data.repository.UserRepositoryImpl
import org.koin.dsl.module

val ProjectModule = module {
    single<UserRepository> {
        UserRepositoryImpl(get())
    }
    single<UserService> {
        UserServiceImpl(get())
    }
    single {
        DatabaseFactory.create()
    }
    single {
        UserDao(get())
    }
    single {
        AudioDao(get())
    }
    single<SeederService> {
        SeederServiceImpl(get())
    }
    single<SearchService> {
        SearchServiceImpl(get())
    }
    single<AudioRepository> {
        AudioRepositoryImpl(get())
    }
}