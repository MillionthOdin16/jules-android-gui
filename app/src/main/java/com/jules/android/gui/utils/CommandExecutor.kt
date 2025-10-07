package com.jules.android.gui.utils

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Utility class for executing CLI commands
 * Handles command execution and output capture
 */
class CommandExecutor(private val context: Context) {
    
    private val preferencesHelper = PreferencesHelper(context)
    
    /**
     * Execute a Jules CLI command and return its output
     * @param command The command to execute (without the 'jules' prefix)
     * @return The command output as a string
     * @throws Exception if command execution fails
     */
    fun execute(command: String): String {
        val cliPath = preferencesHelper.getCliPath()
        
        return try {
            // Build the full command
            val fullCommand = if (command.startsWith(cliPath)) {
                command
            } else {
                "$cliPath $command"
            }
            
            // Execute the command
            val process = Runtime.getRuntime().exec(fullCommand)
            
            // Read output
            val output = StringBuilder()
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            var line: String?
            
            while (reader.readLine().also { line = it } != null) {
                output.append(line).append("\n")
            }
            
            // Read error stream if output is empty
            if (output.isEmpty()) {
                val errorReader = BufferedReader(InputStreamReader(process.errorStream))
                while (errorReader.readLine().also { line = it } != null) {
                    output.append(line).append("\n")
                }
                errorReader.close()
            }
            
            reader.close()
            
            // Wait for process to complete
            val exitCode = process.waitFor()
            
            if (output.isEmpty()) {
                if (exitCode == 0) {
                    "Command completed successfully (no output)"
                } else {
                    "Command failed with exit code: $exitCode"
                }
            } else {
                output.toString().trim()
            }
            
        } catch (e: Exception) {
            when {
                e.message?.contains("cannot run") == true || 
                e.message?.contains("No such file") == true -> {
                    throw Exception("Jules CLI not found. Please install Jules or configure the correct path in settings.\n\nError: ${e.message}")
                }
                else -> {
                    throw Exception("Failed to execute command: ${e.message}")
                }
            }
        }
    }
    
    /**
     * Check if the Jules CLI is available
     * @return true if CLI is accessible, false otherwise
     */
    fun isCliAvailable(): Boolean {
        return try {
            val cliPath = preferencesHelper.getCliPath()
            val process = Runtime.getRuntime().exec("$cliPath --version")
            val exitCode = process.waitFor()
            exitCode == 0
        } catch (e: Exception) {
            false
        }
    }
}
