# Quick Start Guide

Get up and running with Jules Android in minutes!

## Installation

1. **Download** the APK from the [Releases](https://github.com/MillionthOdin16/jules-android-gui/releases) page
2. **Install** the APK on your Android device
3. **Launch** the app from your app drawer

## First Time Setup

### Step 1: Get Your Jules API Key

1. Visit [jules.google](https://jules.google)
2. Sign in with your Google account
3. Navigate to **Settings** → **API Keys**
4. Click **Generate New API Key**
5. Copy the API key (you'll need it in the next step)

### Step 2: Connect GitHub Repositories

Before using the app, you need to connect GitHub repositories to Jules:

1. On [jules.google](https://jules.google), click **Connect Repository**
2. Authorize the Jules GitHub app
3. Select repositories you want Jules to access
4. These repositories will become available as "sources" in the Android app

### Step 3: Configure the App

1. Open the Jules Android app
2. Tap the **Settings** icon (⚙️) in the bottom navigation
3. In the **Jules API Key** field, paste your API key
4. Tap **Load Sources** to fetch your connected repositories
5. Select a repository from the **Selected Source** dropdown
6. Tap **Save Settings**

You're all set! 🎉

## Using the App

### Creating a Session

1. **Navigate to Sessions** - Tap the Sessions icon (📤) in the bottom navigation
2. **Enter your prompt** - Describe what you want Jules to help with:
   - Example: "Add error handling to the authentication module"
   - Example: "Write unit tests for UserRepository"
   - Example: "Refactor the API client to use coroutines"
   - Example: "Fix the bug where users can't log out"
3. **Create Session** - Tap the "Create Session" button
4. **View response** - You'll see:
   - Session name (e.g., `sessions/abc123`)
   - Session state (PENDING, ACTIVE, COMPLETED, etc.)
   - Message to visit jules.google for full details
5. **Check Jules Web** - Go to [jules.google](https://jules.google) to:
   - View the generated plan
   - See code changes
   - Approve or provide feedback
   - Monitor execution progress

### Understanding Session States

- **PENDING**: Session is being initialized
- **ACTIVE**: Jules is actively working on your request
- **COMPLETED**: Session finished successfully
- **FAILED**: Session encountered an error

### Managing Sources

**What are Sources?**  
Sources are GitHub repositories you've connected to Jules. Each session requires a source (repository) to work with.

**Switching Sources:**
1. Go to Settings
2. Select a different repository from the dropdown
3. Save settings
4. New sessions will use the selected repository

**Adding More Sources:**
1. Visit [jules.google](https://jules.google)
2. Connect additional repositories
3. In the app, tap "Load Sources" to refresh the list

## Common Prompts

Here are some example prompts to try with Jules:

| Prompt | Description |
|--------|-------------|
| "Add error handling to UserService" | Add try-catch blocks and error handling |
| "Write unit tests for AuthRepository" | Generate comprehensive unit tests |
| "Refactor the API client" | Improve code structure and organization |
| "Fix memory leak in MainActivity" | Identify and fix memory issues |
| "Add logging to all network calls" | Implement logging throughout |
| "Update dependencies to latest versions" | Dependency management help |
| "Implement dark mode support" | Add theme support |
| "Optimize database queries" | Performance improvements |

**Tips for Better Prompts:**
- Be specific about what you want
- Mention the file or module name
- Describe the expected outcome
- Include any constraints or requirements

## Settings

### Available Settings

**Jules API Key**
- Required for authentication
- Get your key from jules.google
- Stored securely in the app
- Password field with toggle for security

**Selected Source**
- Choose which GitHub repository to use
- Must be connected on jules.google first
- Use "Load Sources" to refresh the list

**Auto-scroll Output**
- Enabled by default
- Automatically scrolls to show the latest session info
- Disable if you want to manually review output

**Save Session History**
- Enabled by default
- Persists session history between app sessions
- Disable to clear history on app restart

## Troubleshooting

### "API key not configured" error

**Problem**: The app doesn't have your API key

**Solution**:
1. Go to Settings
2. Enter your API key from jules.google
3. Tap Save Settings
4. Make sure the API key is valid

### "No source selected" error

**Problem**: No GitHub repository is selected

**Solution**:
1. Go to Settings
2. Tap "Load Sources"
3. Select a repository from the dropdown
4. Tap Save Settings

### "Authentication failed" error

**Problem**: Invalid or expired API key

**Solution**:
1. Visit [jules.google](https://jules.google)
2. Regenerate your API key
3. Update the key in Settings
4. Try again

### Sources list is empty

**Problem**: No repositories connected to Jules

**Solution**:
1. Visit [jules.google](https://jules.google)
2. Click "Connect Repository"
3. Authorize and select repositories
4. In the app, tap "Load Sources" again

### Network errors

**Problem**: Cannot connect to Jules API

**Solution**:
1. Check your internet connection
2. Verify the API is accessible (visit jules.google)
3. Check if your firewall is blocking the app
4. Try again later if Jules is experiencing issues

## Tips and Tricks

### Reusing Commands
- Previous commands and outputs stay visible
- Scroll up to see command history
- Copy output by long-pressing the text

## Tips and Tricks

### Session Management
- Sessions are persistent on jules.google
- You can have multiple active sessions
- Check jules.google regularly for updates on session progress

### Best Prompts
- Be specific and clear about what you want
- Include file or module names when relevant
- Describe the desired outcome
- Mention any constraints or requirements

### Workflow
1. Create a session with a clear prompt
2. Visit jules.google to review the plan
3. Approve or provide feedback
4. Let Jules execute the changes
5. Review the results on GitHub
6. Create follow-up sessions as needed

## Getting Help

### In-App Help
- **About Screen**: App information and links to Jules
- **Settings**: Configuration options

### External Resources
- **Jules Web App**: [jules.google](https://jules.google)
- **API Documentation**: [developers.google.com/jules](https://developers.google.com/jules)
- **Full Documentation**: [README.md](README.md)
- **Development Guide**: [DEVELOPMENT.md](DEVELOPMENT.md)
- **GitHub Issues**: [Report bugs or request features](https://github.com/MillionthOdin16/jules-android-gui/issues)

### Reporting Issues

If you encounter a problem:

1. Check this guide and troubleshooting section
2. Search [existing issues](https://github.com/MillionthOdin16/jules-android-gui/issues)
3. Create a new issue with:
   - Steps to reproduce
   - Expected behavior
   - Actual behavior
   - Device and Android version
   - Screenshots if applicable
   - API error messages if any

## Next Steps

Now that you're set up:

1. ✅ Create your first session
2. ✅ Explore jules.google to see full session details
3. ✅ Try different types of prompts
4. ✅ Connect multiple repositories as sources
5. ✅ Provide feedback to help improve the app

## Version History

### v2.0 (Current)
- Jules API integration
- API key authentication
- Source (repository) management
- Session creation and tracking
- Material Design 3 UI
- Settings and configuration
- Proper error handling

### v1.0
- Initial CLI-based version (deprecated)

## Feedback

We'd love to hear from you!

- 🐛 Found a bug? [Report it](https://github.com/MillionthOdin16/jules-android-gui/issues)
- 💡 Have an idea? [Suggest it](https://github.com/MillionthOdin16/jules-android-gui/discussions)
- ⭐ Like the app? [Star the repo](https://github.com/MillionthOdin16/jules-android-gui)
- 📝 Questions? Check [jules.google](https://jules.google) documentation

---

**Happy coding with Jules! 🚀**
