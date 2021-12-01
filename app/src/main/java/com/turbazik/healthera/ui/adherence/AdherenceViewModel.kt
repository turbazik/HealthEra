package com.turbazik.healthera.ui.adherence

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turbazik.healthera.domain.usecase.adherence.PatientsUseCase
import com.turbazik.healthera.ui.mapper.AdherenceDvoMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.Calendar

class AdherenceViewModel(
    private val patientsUseCase: PatientsUseCase,
    private val adherenceDvoMapper: AdherenceDvoMapper
) : ViewModel() {

    private val _viewState = MutableStateFlow<AdherenceState>(AdherenceState.Default)

    val viewState: StateFlow<AdherenceState> = _viewState

    init {
        getAdherenceList()
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
                    startTime = 0,
                    endTime = Calendar.getInstance().time.time.toInt()
                )
                withContext(Dispatchers.Main) {
                    _viewState.emit(
                        AdherenceState.RequestSucceeded(
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
