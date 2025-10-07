# Jules Android GUI

A modern Android application that provides a graphical user interface for Google's Jules CLI tools. Built with Kotlin and Material Design 3, this app allows users to easily interact with Jules commands through an intuitive mobile interface.

## 📑 Table of Contents

- [Features](#features)
- [Screenshots](#screenshots)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Development](#development)
- [Dependencies](#dependencies)
- [Contributing](#contributing)
- [Documentation](#documentation)
- [License](#license)
- [Support](#support)

## Features

- 🎨 **Modern Material Design 3 UI** - Clean, intuitive interface following Google's latest design guidelines
- 📱 **Bottom Navigation** - Easy access to Home, Commands, Settings, and About screens
- ⚡ **Command Execution** - Execute Jules CLI commands directly from your Android device
- 📝 **Output Display** - View command results in a scrollable, formatted output area
- ⚙️ **Customizable Settings** - Configure CLI path, auto-scroll behavior, and more
- 💾 **Command History** - Track previously executed commands (configurable)
- 🌙 **Dark Mode Support** - Automatically adapts to system theme preferences

## Screenshots

The app features four main screens:
- **Home**: Welcome screen with quick action cards
- **Commands**: Execute Jules commands and view output
- **Settings**: Configure app preferences
- **About**: Application information and version details

📐 For detailed UI design documentation and mockups, see [DESIGN.md](DESIGN.md).

## Requirements

- Android 8.0 (API level 26) or higher
- Jules CLI tools installed on the device (or accessible path configured)

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

## Usage

1. **Launch the app** - You'll see the home screen with quick actions
2. **Navigate to Commands** - Tap the Commands icon in the bottom navigation
3. **Enter a command** - Type your Jules command (e.g., `--help`, `--version`)
4. **Execute** - Tap the Execute button or press Enter
5. **View output** - Results appear in the output area below
6. **Configure settings** - Adjust CLI path and preferences in the Settings screen

## Architecture

The app follows Android best practices and modern architecture patterns:

- **MVVM Pattern** - Separation of concerns with ViewModels and LiveData (ready for expansion)
- **View Binding** - Type-safe view access
- **Navigation Component** - Single Activity with multiple Fragments
- **Coroutines** - Asynchronous command execution
- **Material Design 3** - Latest Material components and theming
- **SharedPreferences** - Persistent settings storage

## Project Structure

```
app/
├── src/main/
│   ├── java/com/jules/android/gui/
│   │   ├── MainActivity.kt           # Main activity with bottom navigation
│   │   ├── ui/
│   │   │   ├── HomeFragment.kt       # Home screen
│   │   │   ├── CommandsFragment.kt   # Command execution interface
│   │   │   ├── SettingsFragment.kt   # Settings configuration
│   │   │   └── AboutFragment.kt      # About information
│   │   └── utils/
│   │       ├── CommandExecutor.kt    # CLI command execution logic
│   │       └── PreferencesHelper.kt  # Settings management
│   └── res/
│       ├── layout/                   # XML layouts
│       ├── values/                   # Colors, strings, themes
│       ├── navigation/               # Navigation graph
│       └── menu/                     # Bottom navigation menu
```

## Configuration

### CLI Path Configuration

By default, the app looks for the `jules` command in the system PATH. To configure a custom path:

1. Navigate to **Settings**
2. Enter the full path to your Jules CLI executable
3. Tap **Save**

### Auto-scroll Output

Enable or disable automatic scrolling to the bottom of command output in Settings.

## Development

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or newer
- JDK 17
- Android SDK with API level 34
- Gradle 8.2

### Building

```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Run tests
./gradlew test

# Run lint checks
./gradlew lint
```

## Dependencies

- AndroidX Core KTX
- AndroidX AppCompat
- Material Components (Material Design 3)
- Navigation Component
- Lifecycle Components
- Kotlin Coroutines
- ConstraintLayout
- RecyclerView

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