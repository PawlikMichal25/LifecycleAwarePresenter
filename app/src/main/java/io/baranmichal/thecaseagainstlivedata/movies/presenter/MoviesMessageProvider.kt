package io.baranmichal.thecaseagainstlivedata.movies.presenter

import android.content.Context
import io.baranmichal.thecaseagainstlivedata.R
import io.baranmichal.thecaseagainstlivedata.testing.OpenForTesting

@OpenForTesting
class MoviesMessageProvider(
    private val context: Context
) {

    fun getConnectionErrorMessage(): String {
        return context.getString(R.string.connection_error_occurred)
    }

    fun getUnknownErrorMessage(): String {
        return context.getString(R.string.unknown_error_occurred)
    }
}