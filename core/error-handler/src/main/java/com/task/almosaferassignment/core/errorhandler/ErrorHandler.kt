package com.task.almosaferassignment.core.errorhandler

interface ErrorHandler {

    fun getError(throwable: Throwable): Throwable
}
