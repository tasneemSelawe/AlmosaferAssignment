package com.task.almosaferassignment.core.errorhandler.handlers

import android.content.Context
import com.squareup.moshi.Moshi
import com.task.almosaferassignment.core.errorhandler.ErrorHandler
import com.task.almosaferassignment.core.errorhandler.R
import com.task.almosaferassignment.core.errorhandler.exception.RemoteException
import com.task.almosaferassignment.core.errorhandler.exception.RemoteException.*
import com.task.almosaferassignment.core.errorhandler.model.HttpErrorBodyModel
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

internal class NetworkErrorHandler @Inject constructor(
    private val moshi: Moshi,
    @ApplicationContext private val context: Context
) : ErrorHandler {

    override fun getError(throwable: Throwable): RemoteException {
        return when (throwable) {
            is HttpException -> {
                val errorBody = throwable.response()?.errorBody()?.string()
                val error = if (errorBody != null) {
                    moshi.adapter(HttpErrorBodyModel::class.java).fromJson(errorBody)!!
                } else {
                    HttpErrorBodyModel()
                }
                Http(error.message ?: throwable.message(), throwable, error)
            }
            is IOException -> Network(context.getString(R.string.error_network_connection), throwable)
            else -> Unknown(context.getString(R.string.error_unknown), throwable)
        }
    }
}
