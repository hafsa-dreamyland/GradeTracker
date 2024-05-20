package it.progmob.gradetracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Subject(val name: String, var grade: Double, var credits: Double) {
    override fun toString(): String {
        return "$name: grade:$grade credits:$credits"
    }

}

class MainViewModel : ViewModel() {
    var studentName: String = ""
    var studentID: String = ""
    private val _subjects = MutableLiveData<MutableList<Subject>>()
    val subjects: LiveData<MutableList<Subject>>
        get() = _subjects

    init {
        _subjects.value = mutableListOf(
            Subject("Math", 0.0, 0.0),
            Subject("Science", 0.0, 0.0),
            Subject("History", 0.0, 0.0)
        )
    }

    fun addSubject(name: String, grade: Double, credits: Double) {
        _subjects.value?.add(Subject(name,grade,credits))
    }

    fun addOrUpdateSubject(subject: Subject) {
        val subjectsList = _subjects.value ?: mutableListOf()

        // Check if the subject with the same name already exists
        val existingSubject = subjectsList.find { it.name == subject.name }

        if (existingSubject != null) {
            // Update the existing subject with the new grade and credits
            existingSubject.grade = subject.grade
            existingSubject.credits = subject.credits
        } else {
            // Add the new subject to the list
            subjectsList.add(subject)
        }

        // Update the LiveData with the modified list
        _subjects.value = subjectsList
    }

    fun getSubject(index: Int): Subject? {
        return _subjects.value?.get(index)
    }

    fun getSubjectList(): List<String> {
        val result = mutableListOf<String>()
        _subjects.value?.forEach{
            result += it.name
        }
        return result.toList()
    }

    fun calculateWeightedAverage(): Double {
        val totalGrade = _subjects.value?.sumOf { it.grade * it.credits } ?: 0.0
        val totalCredits = _subjects.value?.sumOf { it.credits } ?: 0.0
        return if (totalCredits == 0.0) 0.0 else totalGrade / totalCredits
    }

    fun updateSubject(subjectName: String, newGrade: Double, newCredits: Double) {
        val currentSubjects = _subjects.value.orEmpty()
        val updatedSubjects = currentSubjects.map {
            if (it.name == subjectName) {
                it.copy(grade = newGrade, credits = newCredits)
            } else {
                it
            }
        }
        _subjects.value = updatedSubjects.toMutableList()
    }
}