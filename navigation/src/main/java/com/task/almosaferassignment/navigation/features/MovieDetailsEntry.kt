package com.task.almosaferassignment.navigation.features

import com.task.almosaferassignment.navigation.Feature
import com.task.almosaferassignment.navigation.FeatureEntry
import com.task.almosaferassignment.navigation.R
import javax.inject.Inject

class MovieDetailsEntry  @Inject constructor() : FeatureEntry<Unit, Unit>, Feature {

    override val destination: Int
        get() = R.id.fragment_movie_details
}