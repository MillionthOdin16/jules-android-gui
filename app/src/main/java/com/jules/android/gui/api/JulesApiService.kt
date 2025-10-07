package com.jules.android.gui.api

import com.jules.android.gui.data.models.*
import retrofit2.Response
import retrofit2.http.*

/**
 * Jules API Service Interface
 * Base URL: https://jules.googleapis.com/v1alpha/
 */
interface JulesApiService {
    
    /**
     * List all sources connected to Jules
     * GET /sources
     */
    @GET("sources")
    suspend fun listSources(): Response<SourcesResponse>
    
    /**
     * Get a specific source by name
     * GET /sources/{name}
     */
    @GET("sources/{name}")
    suspend fun getSource(@Path("name") name: String): Response<Source>
    
    /**
     * Create a new session
     * POST /sessions
     */
    @POST("sessions")
    suspend fun createSession(@Body request: CreateSessionRequest): Response<Session>
    
    /**
     * List all sessions
     * GET /sessions
     */
    @GET("sessions")
    suspend fun listSessions(
        @Query("pageSize") pageSize: Int? = null,
        @Query("pageToken") pageToken: String? = null
    ): Response<SessionsResponse>
    
    /**
     * Get a specific session by name
     * GET /sessions/{name}
     */
    @GET("sessions/{name}")
    suspend fun getSession(@Path("name") name: String): Response<Session>
    
    /**
     * Delete a session
     * DELETE /sessions/{name}
     */
    @DELETE("sessions/{name}")
    suspend fun deleteSession(@Path("name") name: String): Response<Unit>
}
