# Quick Start Guide

Get up and running with Jules Android GUI in minutes!

## Installation

1. **Download** the APK from the [Releases](https://github.com/MillionthOdin16/jules-android-gui/releases) page
2. **Install** the APK on your Android device
3. **Launch** the app from your app drawer

## First Time Setup

### Step 1: Configure Jules CLI Path

On first launch, you'll need to configure the path to your Jules CLI:

1. Tap the **Settings** icon in the bottom navigation
2. Find the **CLI Path** field
3. Enter the path to your Jules executable:
   - Default: `jules` (if Jules is in your PATH)
   - Custom: `/path/to/jules` (full path to executable)
4. Tap **Save Settings**

### Step 2: Test the Setup

1. Navigate to the **Commands** screen
2. Enter a simple command like `--help` or `--version`
3. Tap **Execute**
4. Verify the output appears correctly

## Using the App

### Running Commands

1. **Navigate** to the Commands screen (📤 icon)
2. **Type** your Jules command in the input field
   - Example: `--help`
   - Example: `analyze project.js`
3. **Execute** by tapping the Execute button or pressing Enter
4. **View** the output in the scrollable area below

### Command Tips

- Don't include "jules" in your command (it's added automatically)
- Commands run in the background, so you can continue using the app
- Output is displayed as it's received from the CLI
- Previous commands and outputs remain visible above

### Clearing Output

- Tap the **Clear** button to:
  - Clear the input field
  - Or clear all output (long press)

### Quick Actions

From the **Home** screen, you can quickly access:
- **Commands**: Tap the Commands card
- **Settings**: Tap the Settings card

## Common Commands

Here are some common Jules commands to try:

| Command | Description |
|---------|-------------|
| `--help` | Display help information |
| `--version` | Show Jules version |
| `status` | Check current status |
| `info` | Display information |
| `list` | List available items |
| `analyze [file]` | Analyze a file |

*(Replace with actual Jules commands based on the CLI tool)*

## Settings

### Available Settings

**CLI Path**
- Default: `jules`
- Set a custom path if Jules is not in your system PATH
- Example: `/usr/local/bin/jules`

**Auto-scroll Output**
- Enabled by default
- Automatically scrolls to show the latest output
- Disable if you want to manually review output

**Save Command History**
- Enabled by default
- Persists command history between app sessions
- Disable to clear history on app restart

## Troubleshooting

### "Jules CLI not found" error

**Problem**: The app can't find the Jules CLI executable

**Solution**:
1. Verify Jules is installed on your device
2. Check the CLI path in Settings
3. Try the full path: `/usr/local/bin/jules`
4. Ensure you have permission to execute Jules

### Command hangs or doesn't complete

**Problem**: Command appears to run forever

**Solution**:
1. Some commands may take a long time
2. Check if Jules is waiting for input
3. Try a simpler command first (like `--help`)
4. Force close and restart the app if needed

### Output is garbled or incorrect

**Problem**: Output displays incorrectly

**Solution**:
1. Check that your CLI path is correct
2. Try running the command in a terminal to compare
3. Some CLI output may use special characters that don't display well

### Permission Denied

**Problem**: "Permission denied" error when running commands

**Solution**:
1. Ensure you have permission to execute Jules
2. Check file permissions: `chmod +x /path/to/jules`
3. You may need root access for certain commands

## Tips and Tricks

### Reusing Commands
- Previous commands and outputs stay visible
- Scroll up to see command history
- Copy output by long-pressing the text

### Keyboard Shortcuts
- Press **Enter** in the command field to execute
- Use your device's back button to navigate between screens

### Performance
- Clear output periodically for better performance
- Disable "Save Command History" if you don't need it
- Close unused apps to free up memory

### Best Practices
- Test commands with `--help` first
- Start with simple commands before complex ones
- Keep the CLI path setting backed up
- Check output carefully before proceeding with operations

## Getting Help

### In-App Help
- Visit the **About** screen for app information
- Check Settings for configuration options

### External Resources
- [Full Documentation](README.md)
- [Development Guide](DEVELOPMENT.md)
- [UI Design Guide](DESIGN.md)
- [GitHub Issues](https://github.com/MillionthOdin16/jules-android-gui/issues)

### Reporting Issues

If you encounter a problem:

1. Check this guide first
2. Search [existing issues](https://github.com/MillionthOdin16/jules-android-gui/issues)
3. Create a new issue with:
   - Steps to reproduce
   - Expected behavior
   - Actual behavior
   - Device and Android version
   - Screenshots if applicable

## Next Steps

Now that you're set up:

1. ✅ Explore the different screens
2. ✅ Try running various Jules commands
3. ✅ Customize settings to your preference
4. ✅ Provide feedback on GitHub

## Version History

### v1.0 (Current)
- Initial release
- Material Design 3 UI
- Command execution
- Settings configuration
- About screen

## Feedback

We'd love to hear from you!

- 🐛 Found a bug? [Report it](https://github.com/MillionthOdin16/jules-android-gui/issues)
- 💡 Have an idea? [Suggest it](https://github.com/MillionthOdin16/jules-android-gui/discussions)
- ⭐ Like the app? [Star the repo](https://github.com/MillionthOdin16/jules-android-gui)

---

**Happy Jules-ing! 🚀**
