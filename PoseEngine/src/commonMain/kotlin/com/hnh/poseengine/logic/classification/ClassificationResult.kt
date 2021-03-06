package com.hnh.poseengine.logic.classification


class ClassificationResult {
    // For an entry in this map, the key is the class name, and the value is how many times this class
    // appears in the top K nearest neighbors. The value is in range [0, K] and could be a float after
    // EMA smoothing. We use this number to represent the confidence of a pose being in this class.
    private val classConfidences: MutableMap<String, Float>
    fun getAllClasses(): Set<String> {
        return classConfidences.keys
    }
    fun getClassConfidence(className: String): Float {
        return if (classConfidences.containsKey(className)) classConfidences[className]!! else 0F
    }

    fun getMaxConfidenceClass(): String {
        val maxValue = classConfidences.values.maxByOrNull {
            it
        }
        for (item in classConfidences) {
            if (item.value == maxValue) {
                return item.key
            }
        }
        return "ERROR"
    }

    fun incrementClassConfidence(className: String?) {
        classConfidences[className!!] =
            if (classConfidences.containsKey(className)) classConfidences[className]!! + 1F else 1F
    }

    fun putClassConfidence(className: String, confidence: Float) {
        classConfidences[className] = confidence
    }

    init {
        classConfidences = HashMap()
    }
}
