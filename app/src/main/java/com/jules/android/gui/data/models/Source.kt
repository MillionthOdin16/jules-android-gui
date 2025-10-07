package com.jules.android.gui.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents a source (GitHub repository) connected to Jules
 */
@JsonClass(generateAdapter = true)
data class Source(
    @Json(name = "name")
    val name: String,
    
    @Json(name = "displayName")
    val displayName: String? = null,
    
    @Json(name = "type")
    val type: String? = "GITHUB",
    
    @Json(name = "repository")
    val repository: String? = null,
    
    @Json(name = "branch")
    val branch: String? = null
)

/**
 * Response containing list of sources
 */
@JsonClass(generateAdapter = true)
data class SourcesResponse(
    @Json(name = "sources")
    val sources: List<Source> = emptyList()
)
