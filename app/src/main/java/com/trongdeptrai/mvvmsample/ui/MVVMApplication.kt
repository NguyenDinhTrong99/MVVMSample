package com.trongdeptrai.mvvmsample.ui

import android.app.Application
import com.trongdeptrai.mvvmsample.data.db.entities.AppDatabase
import com.trongdeptrai.mvvmsample.data.network.api.UserAPI
import com.trongdeptrai.mvvmsample.data.network.repositories.UserRepositories
import com.trongdeptrai.mvvmsample.data.network.responses.NetworkConnectionInternet
import com.trongdeptrai.mvvmsample.data.viewmodel.UserModelFactory
import com.trongdeptrai.mvvmsample.ui.profile.ProfileViewModel
import com.trongdeptrai.mvvmsample.ui.profile.ProfileViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication: Application(), KodeinAware {
    override val kodein: Kodein
        get() = Kodein.lazy {
            import(androidXModule(this@MVVMApplication))
            bind() from singleton { NetworkConnectionInternet(instance()) }
            bind() from singleton { UserAPI(instance()) }
            bind() from singleton { AppDatabase(instance()) }
            bind() from singleton { UserRepositories(instance(), instance()) }
            bind() from provider { UserModelFactory(instance()) }
            bind() from provider { ProfileViewModelFactory(instance()) }
        }

}