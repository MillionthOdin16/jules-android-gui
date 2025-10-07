package com.jules.android.gui.data.repository

import com.jules.android.gui.api.JulesApiClient
import com.jules.android.gui.data.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository for Jules API operations
 * Provides a clean abstraction layer over the API
 */
class JulesRepository {
    
    private val api = JulesApiClient.apiService
    
    /**
     * Result wrapper for API calls
     */
    sealed class Result<out T> {
        data class Success<T>(val data: T) : Result<T>()
        data class Error(val message: String, val code: Int? = null) : Result<Nothing>()
        object Loading : Result<Nothing>()
    }
    
    /**
     * Fetch all sources connected to Jules
     */
    suspend fun getSources(): Result<List<Source>> = withContext(Dispatchers.IO) {
        try {
            val response = api.listSources()
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!.sources)
            } else {
                Result.Error(
                    message = response.errorBody()?.string() ?: "Failed to fetch sources",
                    code = response.code()
                )
            }
        } catch (e: Exception) {
            Result.Error(message = e.message ?: "Network error occurred")
        }
    }
    
    /**
     * Create a new session with a prompt and source
     */
    suspend fun createSession(prompt: String, sourceName: String): Result<Session> = 
        withContext(Dispatchers.IO) {
            try {
                val request = CreateSessionRequest(
                    prompt = prompt,
                    sourceContext = SourceContext(source = sourceName)
                )
                val response = api.createSession(request)
                if (response.isSuccessful && response.body() != null) {
                    Result.Success(response.body()!!)
                } else {
                    Result.Error(
                        message = response.errorBody()?.string() ?: "Failed to create session",
                        code = response.code()
                    )
                }
            } catch (e: Exception) {
                Result.Error(message = e.message ?: "Network error occurred")
            }
        }
    
    /**
     * Fetch all sessions
     */
    suspend fun getSessions(pageSize: Int? = 20, pageToken: String? = null): Result<SessionsResponse> = 
        withContext(Dispatchers.IO) {
            try {
                val response = api.listSessions(pageSize, pageToken)
                if (response.isSuccessful && response.body() != null) {
                    Result.Success(response.body()!!)
                } else {
                    Result.Error(
                        message = response.errorBody()?.string() ?: "Failed to fetch sessions",
                        code = response.code()
                    )
                }
            } catch (e: Exception) {
                Result.Error(message = e.message ?: "Network error occurred")
            }
        }
    
    /**
     * Fetch a specific session by name
     */
    suspend fun getSession(sessionName: String): Result<Session> = 
        withContext(Dispatchers.IO) {
            try {
                val response = api.getSession(sessionName)
                if (response.isSuccessful && response.body() != null) {
                    Result.Success(response.body()!!)
                } else {
                    Result.Error(
                        message = response.errorBody()?.string() ?: "Failed to fetch session",
                        code = response.code()
                    )
                }
            } catch (e: Exception) {
                Result.Error(message = e.message ?: "Network error occurred")
            }
        }
    
    /**
     * Delete a session
     */
    suspend fun deleteSession(sessionName: String): Result<Unit> = 
        withContext(Dispatchers.IO) {
            try {
                val response = api.deleteSession(sessionName)
                if (response.isSuccessful) {
                    Result.Success(Unit)
                } else {
                    Result.Error(
                        message = response.errorBody()?.string() ?: "Failed to delete session",
                        code = response.code()
                    )
                }
            } catch (e: Exception) {
                Result.Error(message = e.message ?: "Network error occurred")
            }
        }
}
