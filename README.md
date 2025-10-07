# Jules Android GUI

A modern Android application that provides a beautiful interface for Google's Jules AI coding assistant. Built with Kotlin and Material Design 3, this app allows users to interact with Jules through an intuitive mobile interface, abstracting away the complexities of the API.

## 📑 Table of Contents

- [Features](#features)
- [Screenshots](#screenshots)
- [Requirements](#requirements)
- [Installation](#installation)
- [Setup](#setup)
- [Usage](#usage)
- [Architecture](#architecture)
- [Technical Stack](#technical-stack)
- [Contributing](#contributing)
- [Documentation](#documentation)
- [License](#license)

## Features

- 🔐 **API Key Authentication** - Secure authentication with Jules API using API keys
- 📚 **Source Management** - Connect and manage GitHub repositories as sources
- 🤖 **AI Sessions** - Create coding sessions with Jules AI using natural language prompts
- 🎨 **Modern Material Design 3 UI** - Clean, intuitive interface following Google's latest design guidelines
- 📱 **Bottom Navigation** - Easy access to Home, Sessions, Settings, and About screens
- ⚡ **Async API Calls** - Non-blocking network operations using Kotlin Coroutines
- 💾 **Persistent Settings** - API key and preferences saved securely
- 🌙 **Dark Mode Support** - Automatically adapts to system theme preferences
- 🔔 **Real-time Feedback** - Snackbar notifications for all actions
- 📊 **Session History** - Track your coding sessions and their states

## Screenshots

The app features four main screens:
- **Home**: Welcome screen with quick action cards
- **Commands**: Execute Jules commands and view output
- **Settings**: Configure app preferences
- **About**: Application information and version details

📐 For detailed UI design documentation and mockups, see [DESIGN.md](DESIGN.md).

## Requirements

- Android 8.0 (API level 26) or higher
- Jules API Key (get yours at [jules.google](https://jules.google))
- Connected GitHub repositories on Jules
- Internet connection for API calls

## Installation

### Building from Source

1. Clone the repository:
```bash
git clone https://github.com/MillionthOdin16/jules-android-gui.git
cd jules-android-gui
```

2. Open the project in Android Studio

3. Build and run the application on your device or emulator

### Using Gradle

```bash
./gradlew assembleDebug
```

The APK will be generated in `app/build/outputs/apk/debug/`

## Setup

### 1. Get Your Jules API Key

1. Visit [jules.google](https://jules.google)
2. Sign in with your Google account
3. Navigate to Settings → API Keys
4. Generate a new API key
5. Copy the key (you'll need it for the app)

### 2. Connect GitHub Repositories

1. On [jules.google](https://jules.google), connect your GitHub repositories
2. Install the Jules GitHub app on your repos
3. These will become available as "sources" in the Android app

### 3. Configure the App

1. Open the Jules Android app
2. Navigate to Settings (gear icon)
3. Enter your API key
4. Tap "Load Sources"
5. Select your preferred GitHub repository
6. Save settings

## Usage

### Creating a Session

1. **Navigate to Sessions** - Tap the sessions icon in the bottom navigation
2. **Enter a prompt** - Type what you want Jules to help you with
   - Example: "Add error handling to the authentication module"
   - Example: "Write unit tests for the UserRepository class"
3. **Create Session** - Tap the "Create Session" button
4. **View result** - See the session details and status
5. **Visit Jules Web** - Go to [jules.google](https://jules.google) to view full session details, plans, and activities

### Managing Sources

- Settings → Load Sources: Fetch your connected GitHub repositories
- Settings → Selected Source: Choose which repo to use for sessions
- All sources must be connected on jules.google first

### Session States

- **PENDING**: Session is being initialized
- **ACTIVE**: Jules is working on your request
- **COMPLETED**: Session finished successfully
- **FAILED**: Session encountered an error

## Architecture

The app follows Android best practices and modern architecture patterns:

- **MVVM Pattern** - Separation of concerns with Repository pattern
- **View Binding** - Type-safe view access
- **Navigation Component** - Single Activity with multiple Fragments
- **Kotlin Coroutines** - Asynchronous API calls and background operations
- **Retrofit + OkHttp** - RESTful API client with interceptors
- **Moshi** - JSON parsing and serialization
- **Repository Pattern** - Clean abstraction over API calls

### Data Flow

1. User interacts with Fragment (UI layer)
2. Fragment calls Repository methods
3. Repository uses Retrofit to make API calls to Jules API
4. API responses parsed with Moshi
5. Results displayed back in Fragment

### Key Components

**API Layer:**
- `JulesApiService` - Retrofit interface defining API endpoints
- `JulesApiClient` - API configuration with authentication interceptor
- Data models for Sources, Sessions, Activities, Plans

**Repository Layer:**
- `JulesRepository` - Abstraction over API calls with Result wrapper
- Handles error responses and network failures

**UI Layer:**
- `HomeFragment` - Welcome screen with quick actions
- `CommandsFragment` - Session creation interface
- `SettingsFragment` - API key and source configuration
- `AboutFragment` - App information

**Utilities:**
- `PreferencesHelper` - SharedPreferences management
- Stores API key, selected source, and app preferences

## Technical Stack

### Core Technologies
- **Language**: Kotlin 1.9.20
- **Min SDK**: API 26 (Android 8.0)
- **Target SDK**: API 34 (Android 14)
- **Build Tool**: Gradle 8.2

### Libraries
- **AndroidX Core KTX** - Kotlin extensions
- **AppCompat** - Backward compatibility
- **Material Components 1.11.0** - Material Design 3
- **Navigation Component 2.7.6** - Fragment navigation
- **Lifecycle Components 2.7.0** - Lifecycle-aware components
- **Kotlin Coroutines 1.7.3** - Asynchronous programming
- **Retrofit 2.9.0** - HTTP client
- **OkHttp 4.12.0** - HTTP/2 client
- **Moshi 1.15.0** - JSON parser
- **ConstraintLayout 2.1.4** - Flexible layouts
- **RecyclerView 1.3.2** - Efficient list display

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is open source and available under the MIT License.

## Acknowledgments

- Built for Google's Jules CLI tools
- Uses Material Design 3 components
- Follows Android best practices and conventions

## Support

For issues, questions, or contributions, please visit the [GitHub repository](https://github.com/MillionthOdin16/jules-android-gui).

## Documentation

For more detailed information, see:

- 📘 [Quick Start Guide](QUICKSTART.md) - Get started quickly
- 🔧 [Development Guide](DEVELOPMENT.md) - For developers
- 🎨 [Design Documentation](DESIGN.md) - UI/UX details  
- 🤝 [Contributing Guide](CONTRIBUTING.md) - How to contribute
- 📋 [Project Overview](PROJECT_OVERVIEW.md) - Complete implementation summary

---

**Built with ❤️ using Kotlin and Material Design 3**