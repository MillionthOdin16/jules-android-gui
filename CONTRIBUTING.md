# Contributing to Jules Android GUI

Thank you for your interest in contributing to Jules Android GUI! This document provides guidelines and instructions for contributing.

## Code of Conduct

### Our Pledge

We are committed to providing a welcoming and inspiring community for all. Please be respectful and constructive in your interactions.

### Our Standards

- Use welcoming and inclusive language
- Be respectful of differing viewpoints
- Gracefully accept constructive criticism
- Focus on what is best for the community
- Show empathy towards other community members

## How to Contribute

### Reporting Bugs

Before creating bug reports, please check existing issues to avoid duplicates.

When creating a bug report, include:
- Clear and descriptive title
- Steps to reproduce the issue
- Expected behavior
- Actual behavior
- Screenshots (if applicable)
- Device/emulator information
- Android version
- App version

**Bug Report Template:**
```markdown
**Description:**
A clear description of the bug

**Steps to Reproduce:**
1. Go to '...'
2. Click on '....'
3. See error

**Expected Behavior:**
What should happen

**Actual Behavior:**
What actually happens

**Environment:**
- Device: [e.g., Pixel 6]
- Android Version: [e.g., Android 13]
- App Version: [e.g., 1.0]

**Screenshots:**
If applicable, add screenshots
```

### Suggesting Enhancements

Enhancement suggestions are tracked as GitHub issues. When creating an enhancement suggestion, include:

- Clear and descriptive title
- Detailed description of the proposed feature
- Explanation of why this enhancement would be useful
- Possible implementation approach
- Mockups or examples (if applicable)

### Pull Requests

1. **Fork the repository** and create your branch from `main`:
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Make your changes**:
   - Follow the code style guidelines
   - Add or update tests as needed
   - Update documentation as needed

3. **Test your changes**:
   - Run the app and verify functionality
   - Run unit tests: `./gradlew test`
   - Run lint: `./gradlew lint`

4. **Commit your changes**:
   - Write clear commit messages
   - Use present tense ("Add feature" not "Added feature")
   - Reference issues and pull requests liberally

5. **Push to your fork**:
   ```bash
   git push origin feature/your-feature-name
   ```

6. **Create a Pull Request**:
   - Provide a clear description of the changes
   - Reference any related issues
   - Include screenshots for UI changes
   - Ensure CI checks pass

## Development Setup

See [DEVELOPMENT.md](DEVELOPMENT.md) for detailed setup instructions.

Quick start:
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run the app

## Code Style Guidelines

### Kotlin Style

Follow the [official Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html):

- Use 4 spaces for indentation
- Use camelCase for variables and functions
- Use PascalCase for classes
- Use UPPER_SNAKE_CASE for constants
- Maximum line length: 120 characters

### Android Conventions

- Use ViewBinding for view access
- Implement proper null safety
- Follow Material Design guidelines
- Use string resources for all user-facing text
- Handle configuration changes properly
- Use appropriate lifecycle methods

### Documentation

- Document public APIs with KDoc
- Include parameter descriptions
- Provide usage examples for complex functionality
- Update README.md when adding features

Example:
```kotlin
/**
 * Executes a Jules CLI command and returns its output
 * 
 * @param command The command to execute (without the 'jules' prefix)
 * @return The command output as a string
 * @throws Exception if command execution fails
 */
fun execute(command: String): String {
    // Implementation
}
```

## Testing Guidelines

### Unit Tests

- Write tests for utility classes and business logic
- Use JUnit 4 for test framework
- Mock dependencies when appropriate
- Aim for meaningful coverage, not just high percentages

### UI Tests

- Write UI tests for critical user flows
- Use Espresso for UI testing
- Test different screen sizes and orientations
- Include accessibility tests

## Commit Message Guidelines

### Format

```
<type>: <subject>

<body>

<footer>
```

### Types

- **feat**: A new feature
- **fix**: A bug fix
- **docs**: Documentation changes
- **style**: Code style changes (formatting, etc.)
- **refactor**: Code refactoring
- **test**: Adding or updating tests
- **chore**: Maintenance tasks

### Examples

```
feat: Add command history feature

Implement a scrollable command history view that shows
previously executed commands. Users can tap a command to
re-execute it.

Closes #123
```

```
fix: Fix crash when CLI path is invalid

Add proper error handling in CommandExecutor when the
configured CLI path doesn't exist or is not executable.

Fixes #456
```

## Branch Naming

Use descriptive branch names:
- `feature/add-history-view`
- `fix/crash-on-invalid-path`
- `docs/update-readme`
- `refactor/simplify-command-executor`

## Review Process

### What We Look For

- Code quality and style compliance
- Test coverage
- Documentation updates
- Backward compatibility
- Performance implications
- Security considerations

### Review Timeline

- We aim to review PRs within 3-5 business days
- Complex changes may take longer
- You may be asked to make changes
- Be responsive to feedback

## Adding Features

### Before You Start

1. Check if the feature is already being worked on
2. Create an issue to discuss the feature
3. Wait for maintainer approval
4. Plan your implementation

### Feature Checklist

- [ ] Feature code implemented
- [ ] Tests written and passing
- [ ] Documentation updated
- [ ] Screenshots provided (for UI changes)
- [ ] Backward compatible (or migration path provided)
- [ ] Performance impact considered
- [ ] Accessibility considered
- [ ] Lint checks passing

## Project Structure

When adding new features, follow the existing structure:

```
app/src/main/java/com/jules/android/gui/
├── MainActivity.kt          # Don't modify unless necessary
├── ui/                      # Add new fragments here
│   └── YourFragment.kt
├── data/                    # Add data models here
│   └── YourModel.kt
└── utils/                   # Add utility classes here
    └── YourUtil.kt
```

## Resources

- String resources: `app/src/main/res/values/strings.xml`
- Colors: `app/src/main/res/values/colors.xml`
- Dimensions: `app/src/main/res/values/dimens.xml`
- Layouts: `app/src/main/res/layout/`

## Questions?

- Open an issue for questions
- Join discussions in GitHub Discussions
- Check existing documentation

## License

By contributing, you agree that your contributions will be licensed under the same license as the project (MIT License).

## Recognition

Contributors will be recognized in:
- GitHub contributors list
- Release notes
- Project documentation (for significant contributions)

Thank you for contributing to Jules Android GUI! 🎉
