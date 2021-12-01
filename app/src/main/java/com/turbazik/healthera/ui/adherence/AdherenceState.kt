package com.turbazik.healthera.ui.adherence

import com.turbazik.healthera.ui.model.AdherenceDvo

sealed class AdherenceState {
    object Default : AdherenceState()
    object StartLoading : AdherenceState()
    object EndLoading : AdherenceState()
    class RequestFailed(val message: String) : AdherenceState()
    class RequestSucceeded(val data: List<AdherenceDvo>) : AdherenceState()
}
