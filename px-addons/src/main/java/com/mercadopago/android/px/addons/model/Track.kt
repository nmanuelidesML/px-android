package com.mercadopago.android.px.addons.model

import com.mercadopago.android.px.addons.tracking.TrackerWrapper
import com.mercadopago.android.px.addons.model.internal.Experiment
import com.mercadopago.android.px.addons.tracking.Tracker

class Track(builder: Builder) {

    private val trackersMask: Long
    val applicationContext: String
    val type: Type
    val path: String
    val data: Map<String, Any?>
    val experiments: List<Experiment>

    init {
        applicationContext = builder.applicationContext
        type = builder.type
        path = builder.path
        data = builder.data
        experiments = builder.experiments.toList()
        trackersMask = builder.trackersMask
    }

    fun send(trackerWrapper: List<TrackerWrapper>) = trackerWrapper.map { it.internalSend(this) }

    internal fun shouldTrack(tracker: Tracker) = tracker.shouldTrack(trackersMask)

    enum class Type {
        VIEW,
        EVENT
    }

    /**
     * @constructor create a track with the following
     * @param tracker obligatory tracker to be used
     * @param type if it is event or view track
     * @param applicationContext to whom belong the track
     * @param path path of the track
     */
    class Builder(tracker: Tracker, val applicationContext: String, val type: Type, val path: String) {

        val data = mutableMapOf<String, Any?>()
        val experiments: MutableSet<Experiment> = mutableSetOf()
        var trackersMask = tracker.bit

        /**
         * add a list of experiments to apply in the track
         */
        fun addExperiments(experiments: List<Experiment>) = apply { this.experiments.addAll(experiments) }

        /**
         * add a experiment to apply in the track
         */
        fun addExperiment(experiment: Experiment) = apply { experiments.add(experiment) }

        /**
         * add a list of trackers type to be used
         */
        fun addTrackers(trackers: List<Tracker>) = apply { trackers.map { trackersMask = trackersMask or it.bit } }

        /**
         * add a tracker type to be used
         */
        fun addTracker(tracker: Tracker) = apply { trackersMask = trackersMask or tracker.bit }

        /**
         * add the data to be tracked
         */
        fun addData(data: Map<String, Any?>) = apply { this.data.putAll(data) }

        fun build() = Track(this)
    }
}