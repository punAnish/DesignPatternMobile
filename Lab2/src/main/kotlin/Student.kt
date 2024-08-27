package org.example

class Student(name: String, age: Int) : Human(name, age) {
    val courses = mutableListOf<CourseRecord>()

    fun addCourse(course: CourseRecord) {
        courses.add(course)
    }

    fun weightedAverage(): Double {
        val totalWeightedGrades = courses.sumOf { it.grade * it.credits }
        val totalCredits = courses.sumOf { it.credits }
        return totalWeightedGrades / totalCredits
    }

    fun weightedAverage(year: Int): Double {
        val coursesForYear = courses.filter { it.yearCompleted == year }
        val totalWeightedGrades = coursesForYear.sumOf { it.grade * it.credits }
        val totalCredits = coursesForYear.sumOf { it.credits }
        return if (totalCredits > 0) totalWeightedGrades / totalCredits else 0.0
    }

    fun minMaxGrades(): Pair<Double, Double> {
        val grades = courses.map { it.grade }
        return Pair(grades.minOrNull() ?: 0.0, grades.maxOrNull() ?: 0.0)
    }
}
