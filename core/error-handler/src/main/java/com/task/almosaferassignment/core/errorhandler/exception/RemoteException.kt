package com.task.almosaferassignment.core.errorhandler.exception

import com.task.almosaferassignment.core.errorhandler.model.HttpErrorBodyModel


sealed class RemoteException constructor(message: String, throwable: Throwable) : Throwable(message, throwable) {

    class Network(message: String, throwable: Throwable) : RemoteException(message, throwable)

    class Http(
        message: String,
        throwable: Throwable,
        val body: HttpErrorBodyModel
    ) : RemoteException(message, throwable)

    class Unknown(message: String, throwable: Throwable) : RemoteException(message, throwable)
}
