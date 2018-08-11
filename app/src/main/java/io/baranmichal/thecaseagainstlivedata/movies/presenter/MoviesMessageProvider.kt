package io.baranmichal.thecaseagainstlivedata.movies.presenter

import android.content.Context
import io.baranmichal.thecaseagainstlivedata.R

class MoviesMessageProvider(
    private val context: Context
) {

    fun getServerErrorMessage(): String {
        return context.getString(R.string.server_error_occurred)
    }

    fun getUnknownErrorMessage(): String {
        return context.getString(R.string.unknown_error_occurred)
    }
}