package com.task.almosaferassignment.navigation.features

import android.os.Parcelable
import com.task.almosaferassignment.navigation.Feature
import com.task.almosaferassignment.navigation.FeatureEntry
import com.task.almosaferassignment.navigation.R
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

class MovieDetailsEntry @Inject constructor() : FeatureEntry<MovieDetailsEntry.Arguments, Unit>, Feature {

    override val destination: Int
        get() = R.id.fragment_movie_details

    @Parcelize
    data class Arguments(
        val image: String,
        val name: String,
        val rate: Double,
        val overView: String
    ) : Parcelable
}