# Development Guide

This guide provides detailed information for developers working on the Jules Android GUI project.

## Setup

### Prerequisites

1. **Android Studio** (Hedgehog 2023.1.1 or newer recommended)
   - Download from: https://developer.android.com/studio
   
2. **JDK 17**
   - Android Studio includes JDK, or install separately
   
3. **Android SDK**
   - API Level 26 (minimum)
   - API Level 34 (target)
   - Installed via Android Studio SDK Manager

### Initial Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/MillionthOdin16/jules-android-gui.git
   cd jules-android-gui
   ```

2. Open the project in Android Studio:
   - File → Open
   - Select the `jules-android-gui` directory
   - Wait for Gradle sync to complete

3. Install required SDK components:
   - Android Studio will prompt for missing SDK components
   - Accept and install all required packages

## Building the Project

### Using Android Studio

1. **Debug Build**:
   - Build → Make Project (Ctrl+F9)
   - Or: Build → Build Bundle(s) / APK(s) → Build APK(s)

2. **Release Build**:
   - Build → Generate Signed Bundle / APK
   - Follow the signing wizard

### Using Command Line

Once Android Studio has synced the project, you can use the Gradle wrapper:

```bash
# Make gradlew executable (Linux/Mac)
chmod +x gradlew

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Run tests
./gradlew test

# Run lint checks
./gradlew lint

# Clean build
./gradlew clean
```

## Running the App

### On Emulator

1. Create an AVD (Android Virtual Device):
   - Tools → Device Manager
   - Create Device
   - Select a device definition
   - Choose system image (API 26+)
   - Finish setup

2. Run the app:
   - Click Run button (or Shift+F10)
   - Select the emulator from the list

### On Physical Device

1. Enable Developer Options on your Android device:
   - Go to Settings → About Phone
   - Tap Build Number 7 times
   - Go back to Settings → Developer Options
   - Enable USB Debugging

2. Connect device via USB and run the app:
   - Click Run button (or Shift+F10)
   - Select your device from the list

## Project Structure

```
jules-android-gui/
├── app/
│   ├── build.gradle                 # App module build configuration
│   ├── proguard-rules.pro          # ProGuard rules for release builds
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml  # App manifest
│           ├── java/com/jules/android/gui/
│           │   ├── MainActivity.kt              # Main entry point
│           │   ├── ui/                          # UI components
│           │   │   ├── HomeFragment.kt         # Home screen
│           │   │   ├── CommandsFragment.kt     # Command execution
│           │   │   ├── SettingsFragment.kt     # Settings screen
│           │   │   └── AboutFragment.kt        # About screen
│           │   └── utils/                       # Utility classes
│           │       ├── CommandExecutor.kt      # CLI execution
│           │       └── PreferencesHelper.kt    # Settings storage
│           └── res/                             # Resources
│               ├── layout/                      # XML layouts
│               ├── values/                      # Strings, colors, etc.
│               ├── navigation/                  # Navigation graph
│               └── menu/                        # Menu definitions
├── build.gradle                     # Project-level build configuration
├── settings.gradle                  # Gradle settings
├── gradle.properties               # Gradle properties
└── README.md                       # Project README
```

## Architecture

### MVVM Pattern (Ready for Expansion)

The app is structured to support the MVVM (Model-View-ViewModel) pattern:

- **View**: Fragments (UI layer)
- **ViewModel**: Can be added for complex state management
- **Model**: Data classes and repositories (can be expanded)

### Navigation

Uses Android Jetpack Navigation Component:
- Single Activity architecture
- Bottom Navigation for main screens
- Navigation graph defined in `res/navigation/nav_graph.xml`

### Data Flow

1. User interacts with Fragment (View)
2. Fragment calls methods in utility classes
3. CommandExecutor runs CLI commands asynchronously
4. Results are displayed back in the Fragment

## Key Components

### CommandExecutor

Handles CLI command execution:
- Runs commands in background thread using Coroutines
- Captures stdout and stderr
- Provides error handling and timeout management

```kotlin
val executor = CommandExecutor(context)
val result = executor.execute("jules --help")
```

### PreferencesHelper

Manages app settings using SharedPreferences:
- CLI path configuration
- Auto-scroll preference
- Command history settings

```kotlin
val prefs = PreferencesHelper(context)
prefs.setCliPath("/path/to/jules")
val path = prefs.getCliPath()
```

## Testing

### Unit Tests

Run unit tests:
```bash
./gradlew test
```

Add unit tests in:
```
app/src/test/java/com/jules/android/gui/
```

### UI Tests

Run instrumented tests:
```bash
./gradlew connectedAndroidTest
```

Add UI tests in:
```
app/src/androidTest/java/com/jules/android/gui/
```

## Code Style

### Kotlin Conventions

Follow official Kotlin coding conventions:
- https://kotlinlang.org/docs/coding-conventions.html

### Android Best Practices

- Use ViewBinding instead of findViewById
- Follow Material Design guidelines
- Implement proper lifecycle management
- Use Coroutines for async operations
- Handle configuration changes properly

### Formatting

Use Android Studio's code formatter:
- Code → Reformat Code (Ctrl+Alt+L)

## Adding New Features

### Adding a New Fragment

1. Create the Fragment class in `ui/` package:
   ```kotlin
   class NewFragment : Fragment() {
       private var _binding: FragmentNewBinding? = null
       private val binding get() = _binding!!
       
       override fun onCreateView(
           inflater: LayoutInflater,
           container: ViewGroup?,
           savedInstanceState: Bundle?
       ): View {
           _binding = FragmentNewBinding.inflate(inflater, container, false)
           return binding.root
       }
       
       override fun onDestroyView() {
           super.onDestroyView()
           _binding = null
       }
   }
   ```

2. Create the layout XML in `res/layout/fragment_new.xml`

3. Add to navigation graph in `res/navigation/nav_graph.xml`

4. Add menu item in `res/menu/bottom_navigation_menu.xml` if needed

### Adding Dependencies

Add to `app/build.gradle`:
```gradle
dependencies {
    implementation 'androidx.library:artifact:version'
}
```

Then sync Gradle files.

## Debugging

### Logcat

View logs in Android Studio:
- View → Tool Windows → Logcat

Add logging in code:
```kotlin
import android.util.Log

Log.d("TAG", "Debug message")
Log.e("TAG", "Error message", exception)
```

### Debug Builds

Debug builds include:
- Debug symbols
- Logging statements
- No ProGuard obfuscation

### Breakpoints

Set breakpoints in Android Studio:
- Click in the left gutter next to line numbers
- Run in debug mode (Shift+F9)

## Performance Optimization

### ProGuard

Configure in `app/proguard-rules.pro` for release builds.

### Lint

Run lint checks:
```bash
./gradlew lint
```

View report in:
```
app/build/reports/lint-results.html
```

### Memory Profiling

Use Android Studio Profiler:
- View → Tool Windows → Profiler
- Monitor CPU, Memory, Network, and Energy usage

## Troubleshooting

### Gradle Sync Failed

1. Check internet connection
2. Invalidate caches: File → Invalidate Caches / Restart
3. Clean and rebuild: Build → Clean Project, then Build → Rebuild Project

### SDK Missing

1. Open SDK Manager: Tools → SDK Manager
2. Install required SDK platforms and tools
3. Sync Gradle again

### Emulator Issues

1. Wipe data: AVD Manager → Actions → Wipe Data
2. Or create a new AVD

### Build Errors

1. Check `build.gradle` for syntax errors
2. Ensure all dependencies are compatible
3. Check Gradle console for detailed error messages

## Resources

- [Android Developers Documentation](https://developer.android.com/docs)
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- [Material Design 3](https://m3.material.io/)
- [Android Jetpack](https://developer.android.com/jetpack)

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) for contribution guidelines.

## Support

For issues and questions:
- GitHub Issues: https://github.com/MillionthOdin16/jules-android-gui/issues
- Discussions: https://github.com/MillionthOdin16/jules-android-gui/discussions
