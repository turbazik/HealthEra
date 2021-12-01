package com.turbazik.healthera.ui.adherence

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turbazik.healthera.domain.usecase.adherence.PatientsUseCase
import com.turbazik.healthera.domain.usecase.auth.AuthUseCase
import com.turbazik.healthera.ui.mapper.AdherenceDvoMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

private const val START_TIME = 1630001113
private const val END_TIME = 1638381113

class AdherenceViewModel(
    private val patientsUseCase: PatientsUseCase,
    private val authUseCase: AuthUseCase,
    private val adherenceDvoMapper: AdherenceDvoMapper
) : ViewModel() {

    private val _viewState = MutableStateFlow<AdherenceState>(AdherenceState.Default)

    val viewState: StateFlow<AdherenceState> = _viewState

    init {
        getAdherenceList()
    }

    fun onLogout() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _viewState.emit(
                    AdherenceState.StartLoading
                )
            }
            try {
                authUseCase.logout()
                withContext(Dispatchers.Main) {
                    _viewState.emit(
                        AdherenceState.LogoutRequestSucceeded
                    )
                }
            } catch (e: Exception) {
                _viewState.emit(
                    AdherenceState.RequestFailed(
                        message = e.message.orEmpty()
                    )
                )
            } finally {
                withContext(Dispatchers.Main) {
                    _viewState.emit(
                        AdherenceState.EndLoading
                    )
                }
            }
        }
    }

    private fun getAdherenceList() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _viewState.emit(
                    AdherenceState.StartLoading
                )
            }
            try {
                val response = patientsUseCase.getAdherence(
                    startTime = START_TIME,
                    endTime = END_TIME
                )
                withContext(Dispatchers.Main) {
                    _viewState.emit(
                        AdherenceState.AdherenceRequestSucceeded(
                            data = response.map {
                                adherenceDvoMapper.map(from = it)
                            }
                        )
                    )
                }
            } catch (e: Exception) {
                _viewState.emit(
                    AdherenceState.RequestFailed(
                        message = e.message.orEmpty()
                    )
                )
            } finally {
                withContext(Dispatchers.Main) {
                    _viewState.emit(
                        AdherenceState.EndLoading
                    )
                }
            }
        }
    }
}
