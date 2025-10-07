package com.jules.android.gui.utils

import android.content.Context
import android.content.SharedPreferences

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
        private const val KEY_CLI_PATH = "cli_path"
        private const val KEY_AUTO_SCROLL = "auto_scroll"
        private const val KEY_SAVE_HISTORY = "save_history"
        
        private const val DEFAULT_CLI_PATH = "jules"
        private const val DEFAULT_AUTO_SCROLL = true
        private const val DEFAULT_SAVE_HISTORY = true
    }
    
    fun getCliPath(): String {
        return prefs.getString(KEY_CLI_PATH, DEFAULT_CLI_PATH) ?: DEFAULT_CLI_PATH
    }
    
    fun setCliPath(path: String) {
        prefs.edit().putString(KEY_CLI_PATH, path).apply()
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
