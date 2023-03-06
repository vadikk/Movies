package com.example.profile.domain.useCase

internal class ValidateField {

    private val changes = mutableSetOf<DataFieldType>()

    fun validateInputFields(newText: String, oldText: String, type: DataFieldType): Boolean {
        when(type.name){
            DataFieldType.FIRST_NAME.name -> {
                if (newText != oldText) changes.add(DataFieldType.FIRST_NAME)
                else changes.remove(DataFieldType.FIRST_NAME)
            }
            DataFieldType.LAST_NAME.name -> {
                if (newText != oldText) changes.add(DataFieldType.LAST_NAME)
                else changes.remove(DataFieldType.LAST_NAME)
            }
        }
        return changes.isNotEmpty()
    }
}

internal enum class DataFieldType{
    FIRST_NAME, LAST_NAME
}