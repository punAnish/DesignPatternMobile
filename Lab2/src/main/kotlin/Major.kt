import org.example.Student

class Major(val name: String) {
    private val students = mutableListOf<Student>()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun stats(): Triple<Double, Double, Double> {
        val averages = students.map { it.weightedAverage() }
        val minAverage = averages.minOrNull() ?: 0.0
        val maxAverage = averages.maxOrNull() ?: 0.0
        val avgOfAverages = averages.average()
        return Triple(minAverage, maxAverage, avgOfAverages)
    }

    fun stats(courseName: String): Triple<Double, Double, Double> {
        val courseAverages = students.mapNotNull { student ->
            val courses = student.courses.filter { it.name == courseName }
            if (courses.isNotEmpty()) {
                courses.sumOf { it.grade * it.credits } / courses.sumOf { it.credits }
            } else {
                null
            }
        }

        // Handling the case when no averages are found for the specified course
        if (courseAverages.isEmpty()) {
            return Triple(0.0, 0.0, 0.0)
        }

        val minAverage = courseAverages.minOrNull() ?: 0.0
        val maxAverage = courseAverages.maxOrNull() ?: 0.0
        val avgOfAverages = courseAverages.average()
        return Triple(minAverage, maxAverage, avgOfAverages)
    }
}

