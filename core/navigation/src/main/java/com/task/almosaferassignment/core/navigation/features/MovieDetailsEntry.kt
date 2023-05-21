package com.task.almosaferassignment.core.navigation.features

import com.task.almosaferassignment.core.navigation.Feature
import com.task.almosaferassignment.core.navigation.FeatureEntry
import com.task.almosaferassignment.core.navigation.R
import javax.inject.Inject

class MovieDetailsEntry  @Inject constructor() : FeatureEntry<Unit, Unit>, Feature {

    override val destination: Int
        get() = R.id.fragment_movie_details
}