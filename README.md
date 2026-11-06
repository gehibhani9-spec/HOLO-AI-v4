# HOLO AI — Android foundation

This is the first source-project milestone for HOLO AI.

## Included
- Native Android app using Kotlin and Jetpack Compose.
- Six selectable visual persona themes: Ultron-inspired, Joker-inspired, Naruto-inspired, Kakashi-inspired, Hulk-inspired and ChatGPT-inspired.
- Basic console/chat UI placeholder.
- GitHub Actions workflow for building a debug APK.

## Not included yet
The first milestone does **not** yet connect OpenAI, Gemini, ElevenLabs, Spotify, Tor, camera vision, Termux, Kali Droid, accessibility automation or cybersecurity tools. Those require separate implementation, permissions, API credentials, compatibility checks and safety controls.

## Build
Open this project in Android Studio, or push it to a GitHub repository and run the `Build HOLO AI APK` workflow. The workflow uses Gradle on GitHub-hosted Linux.

For a local build with Gradle installed:
`gradle assembleDebug`

The APK output is:
`app/build/outputs/apk/debug/app-debug.apk`

## Security notes
- Do not hard-code API keys in source code or commit them to GitHub.
- Use Android's permission prompts for camera and other sensitive capabilities.
- Tor routing must be implemented with a properly configured proxy/VPN approach and fail-closed behavior; a UI toggle alone does not provide anonymity.
- UI automation and network testing must be limited to apps, devices and systems you are authorized to control.
# HOLO-AI-v4
