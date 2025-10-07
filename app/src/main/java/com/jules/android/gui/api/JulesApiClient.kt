package com.jules.android.gui.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Jules API Client
 * Handles API configuration, authentication, and service creation
 */
object JulesApiClient {
    
    private const val BASE_URL = "https://jules.googleapis.com/v1alpha/"
    private const val API_KEY_HEADER = "X-Goog-Api-Key"
    
    private var apiKey: String? = null
    
    /**
     * Set the API key for authentication
     */
    fun setApiKey(key: String) {
        apiKey = key
    }
    
    /**
     * Get the current API key
     */
    fun getApiKey(): String? = apiKey
    
    /**
     * Check if API key is configured
     */
    fun hasApiKey(): Boolean = !apiKey.isNullOrBlank()
    
    /**
     * Clear the API key
     */
    fun clearApiKey() {
        apiKey = null
    }
    
    /**
     * Moshi instance for JSON parsing
     */
    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    
    /**
     * API Key Interceptor - adds X-Goog-Api-Key header to all requests
     */
    private val apiKeyInterceptor = Interceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
        
        // Add API key if available
        apiKey?.let {
            requestBuilder.addHeader(API_KEY_HEADER, it)
        }
        
        val request = requestBuilder.build()
        chain.proceed(request)
    }
    
    /**
     * Logging Interceptor for debugging
     */
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    
    /**
     * OkHttp Client with interceptors
     */
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    
    /**
     * Retrofit instance
     */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
    
    /**
     * Jules API Service instance
     */
    val apiService: JulesApiService by lazy {
        retrofit.create(JulesApiService::class.java)
    }
}
