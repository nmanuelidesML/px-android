package com.mercadopago.android.px.internal.tracking

import android.content.SharedPreferences
import com.mercadopago.android.px.internal.tracking.TrackingRepository.Companion.OLD_STATIC_FLOW_DETAIL
import com.mercadopago.android.px.internal.tracking.TrackingRepository.Companion.OLD_STATIC_FLOW_ID
import com.mercadopago.android.px.internal.util.JsonUtil

class TrackingRepositoryImpl(private val sharedPreferences: SharedPreferences) : TrackingRepository {

    private var internalSessionId: String? = null
    override val sessionId: String
        get() {
            if (internalSessionId == null) {
                internalSessionId = sharedPreferences.getString(PREF_SESSION_ID, null)
            }
            return internalSessionId ?: DEFAULT_SESSION_ID
        }

    private var internalFlowId: String? = null
    override val flowId: String
        get() {
            if (internalFlowId == null) {
                internalFlowId = sharedPreferences.getString(PREF_FLOW_ID, null)
            }
            return internalFlowId ?: OLD_STATIC_FLOW_ID ?: DEFAULT_FLOW_ID
        }

    private var internalFlowDetail: Map<String, Any>? = null
    override val flowDetail: Map<String, Any>
        get() {
            if (internalFlowDetail == null) {
                internalFlowDetail = JsonUtil.getMapFromJson(sharedPreferences.getString(PREF_FLOW_DETAIL, null))
            }
            return internalFlowDetail ?: OLD_STATIC_FLOW_DETAIL ?: emptyMap()
        }

    override fun configure(model: TrackingRepository.Model) {
        internalSessionId = model.sessionId
        internalFlowId = model.flowId
        internalFlowDetail = model.flowDetail
        with(sharedPreferences.edit()) {
            putString(PREF_SESSION_ID, internalSessionId)
            internalFlowId?.let { putString(PREF_FLOW_ID, it) }
            internalFlowDetail?.let { putString(PREF_FLOW_DETAIL, JsonUtil.toJson(it)) }
            apply()
        }
    }

    override fun reset() {
        internalSessionId = null
        internalFlowId = null
        internalFlowDetail = null
        sharedPreferences.edit()
            .remove(PREF_SESSION_ID)
            .remove(PREF_FLOW_ID)
            .remove(PREF_FLOW_DETAIL)
            .apply()
    }

    companion object {
        private const val DEFAULT_SESSION_ID = "no-value"
        private const val DEFAULT_FLOW_ID = "unknown"
        private const val PREF_SESSION_ID = "PREF_SESSION_ID"
        private const val PREF_FLOW_ID = "PREF_FLOW_ID"
        private const val PREF_FLOW_DETAIL = "PREF_FLOW_DETAIL"
    }
}