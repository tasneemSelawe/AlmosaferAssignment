package com.task.almosaferassignment.core.errorhandler.di


import com.task.almosaferassignment.core.errorhandler.ErrorHandler
import com.task.almosaferassignment.core.errorhandler.handlers.NetworkErrorHandler
import com.task.almosaferassignment.errorhandler.di.Network
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface ErrorHandlerModule {

    @Binds
    @Network
    fun bindNetworkErrorHandler(networkErrorHandler: NetworkErrorHandler): ErrorHandler
}
