package com.jules.android.gui.utils

import android.content.Context
import android.content.SharedPreferences
import com.jules.android.gui.api.JulesApiClient

/**
 * Helper class for managing application preferences
 */
class PreferencesHelper(context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE
    )
    
    companion object {
        private const val PREFS_NAME = "jules_android_gui_prefs"
        private const val KEY_API_KEY = "api_key"
        private const val KEY_AUTO_SCROLL = "auto_scroll"
        private const val KEY_SAVE_HISTORY = "save_history"
        private const val KEY_SELECTED_SOURCE = "selected_source"
        
        private const val DEFAULT_AUTO_SCROLL = true
        private const val DEFAULT_SAVE_HISTORY = true
    }
    
    /**
     * Get the Jules API key
     */
    fun getApiKey(): String {
        return prefs.getString(KEY_API_KEY, "") ?: ""
    }
    
    /**
     * Set the Jules API key and update the API client
     */
    fun setApiKey(key: String) {
        prefs.edit().putString(KEY_API_KEY, key).apply()
        JulesApiClient.setApiKey(key)
    }
    
    /**
     * Check if API key is configured
     */
    fun hasApiKey(): Boolean {
        return getApiKey().isNotBlank()
    }
    
    /**
     * Clear the API key
     */
    fun clearApiKey() {
        prefs.edit().remove(KEY_API_KEY).apply()
        JulesApiClient.clearApiKey()
    }
    
    /**
     * Get the selected source name
     */
    fun getSelectedSource(): String? {
        return prefs.getString(KEY_SELECTED_SOURCE, null)
    }
    
    /**
     * Set the selected source name
     */
    fun setSelectedSource(sourceName: String?) {
        if (sourceName != null) {
            prefs.edit().putString(KEY_SELECTED_SOURCE, sourceName).apply()
        } else {
            prefs.edit().remove(KEY_SELECTED_SOURCE).apply()
        }
    }
    
    fun isAutoScrollEnabled(): Boolean {
        return prefs.getBoolean(KEY_AUTO_SCROLL, DEFAULT_AUTO_SCROLL)
    }
    
    fun setAutoScrollEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_AUTO_SCROLL, enabled).apply()
    }
    
    fun isSaveHistoryEnabled(): Boolean {
        return prefs.getBoolean(KEY_SAVE_HISTORY, DEFAULT_SAVE_HISTORY)
    }
    
    fun setSaveHistoryEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_SAVE_HISTORY, enabled).apply()
    }
}
