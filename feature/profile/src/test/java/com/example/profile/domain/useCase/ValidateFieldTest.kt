package com.example.profile.domain.useCase

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ValidateFieldTest{

    private lateinit var validateField: ValidateField

    @Before
    fun setup(){
        validateField = ValidateField()
    }

    @Test
    fun validateFirstNames() {
        val oldName = "Chris"
        var newName = "John"

        val validateNames = validateField.validateInputFields(newName, oldName, DataFieldType.FIRST_NAME)
        assertThat(validateNames).isTrue()

        newName = oldName
        val validateNames2 = validateField.validateInputFields(newName, oldName, DataFieldType.FIRST_NAME)
        assertThat(validateNames2).isFalse()
    }

    @Test
    fun validateLastNames() {
        val oldName = "Prat"
        var newName = "Pratt"

        val validateNames = validateField.validateInputFields(newName, oldName, DataFieldType.LAST_NAME)
        assertThat(validateNames).isTrue()

        newName = oldName
        val validateNames2 = validateField.validateInputFields(newName, oldName, DataFieldType.LAST_NAME)
        assertThat(validateNames2).isFalse()
    }
}