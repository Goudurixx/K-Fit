package com.example.k_fit.presentation.features.register

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import androidx.lifecycle.viewModelScope
import com.example.k_fit.R
import com.example.k_fit.domain.models.CreateNewUser
import com.example.k_fit.domain.usecases.auth.RegisterFirebaseUseCase
import com.example.k_fit.presentation.base.BaseViewModel
import com.example.k_fit.presentation.common.Gender
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerFirebaseUseCase: RegisterFirebaseUseCase,
) : BaseViewModel() {

    private val _registerProfileState = MutableStateFlow(RegisterProfileState())
    val registerProfileState: StateFlow<RegisterProfileState> = _registerProfileState

    fun updateScreenStep(step: Float) {
        _registerProfileState.update { currentState ->
            currentState.copy(screenStep = step)
        }
    }

    fun updateEmail(newEmail: String) {
        _registerProfileState.update { currentState ->
            currentState.copy(email = newEmail)
        }
    }

    fun updatePassword(newPassword: String) {
        _registerProfileState.update { currentState ->
            currentState.copy(password = newPassword)
        }
    }

    fun updatePasswordConfirmation(confirmPassword: String) {
        _registerProfileState.update { currentState ->
            currentState.copy(passwordConfirm = confirmPassword)
        }
    }

    private fun updateIsEmailValid(email: String?): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$"
        pattern = Pattern.compile(EMAIL_PATTERN)
        matcher = pattern.matcher(email)
        _registerProfileState.update { currentState ->
            currentState.copy(isEmailValid = matcher.matches())
        }
        return _registerProfileState.value.isEmailValid
    }

    private fun updateIsPasswordValid(password: String?) {
        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^!?*&+=-])(?=\\S+$).{6,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(password)
        _registerProfileState.update { currentState ->
            currentState.copy(isPasswordValid = matcher.matches())
        }
    }

    private fun updateIsPasswordDifferent(password: String, passwordConfirm: String) {
        _registerProfileState.update { currentState ->
            currentState.copy(isPasswordDifferent = (password != passwordConfirm))
        }
    }

    fun updateNickName(nickName: String) {
        _registerProfileState.update { currentState ->
            currentState.copy(nickName = nickName)
        }
    }

    fun updateFirstName(firstName: String) {
        _registerProfileState.update { currentState ->
            currentState.copy(firstName = firstName)
        }
    }

    fun updateLastName(lastName: String) {
        _registerProfileState.update { currentState ->
            currentState.copy(lastName = lastName)
        }
    }

    fun isFormValid() {
        var isValid = false
        if (registerProfileState.value.screenStep == 1f) {
            val email: String = _registerProfileState.value.email
            val password: String = _registerProfileState.value.password
            val passwordConfirm: String = _registerProfileState.value.passwordConfirm

            updateIsEmailValid(email)
            updateIsPasswordValid(password)
            updateIsPasswordDifferent(password, passwordConfirm)

            isValid =
                _registerProfileState.value.isEmailValid && _registerProfileState.value.isPasswordValid && !_registerProfileState.value.isPasswordDifferent
        }
        if (registerProfileState.value.screenStep == 2f) {
            isValid =
                registerProfileState.value.nickName.isNotEmpty() && registerProfileState.value.firstName.isNotEmpty() && registerProfileState.value.lastName.isNotEmpty() && registerProfileState.value.birthDate.isNotEmpty()
        }
        if (registerProfileState.value.screenStep == 3f) {
            isValid =
                registerProfileState.value.height > 1f && registerProfileState.value.weight > 1f
        }
        updateIsScreenValid(isValid)
    }

    fun showDatePickerDialog(context: Context) {
        val calendar = Calendar.getInstance()
        val mDatePickerDialog = DatePickerDialog(
            context,
            R.style.DatePickerTheme,
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
                _registerProfileState.update { currentState ->
                    currentState.copy(
                        birthDate = "$selectedDayOfMonth/${
                            String.format(
                                "%02d", selectedMonth + 1
                            )
                        }/$selectedYear"
                    )
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    fun updateGender(gender: Gender) {
        _registerProfileState.update { currentState ->
            currentState.copy(gender = gender)
        }
    }

    fun updateWeight(weight: String) {
        if (weight.isNotEmpty() && weight != ".") {
            _registerProfileState.update { currentState ->
                currentState.copy(weight = weight.toFloat())
            }
        }
    }

    fun updateHeight(height: String) {
        if (height.isNotEmpty() && height != ".") {
            _registerProfileState.update { currentState ->
                currentState.copy(height = height.toFloat())
            }
        }
    }

    fun updateIsScreenValid(isScreenValid: Boolean) {
        _registerProfileState.update { currentState ->
            currentState.copy(isScreenValid = isScreenValid)
        }
    }

    fun registerUser(redirection: () -> Unit) {
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            Log.e("Error to register request", e.toString())
        }) {
            registerFirebaseUseCase(
                CreateNewUser(
                    _registerProfileState.value.email,
                    _registerProfileState.value.nickName,
                    _registerProfileState.value.firstName,
                    _registerProfileState.value.lastName,
                    _registerProfileState.value.birthDate,
                    _registerProfileState.value.gender!!,
                    _registerProfileState.value.weight,
                    _registerProfileState.value.height
                ), _registerProfileState.value.password
            ).collect { result ->
                if (result.isSuccess)
                    redirection()
            }
        }
    }
}

