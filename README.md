This is a Kotlin Multiplatform project targeting Android, iOS, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

**Desktop: **
<img width="1105" alt="Desktop-1" src="https://github.com/user-attachments/assets/b9329c60-dad8-4a92-a28b-60e6110ed22b" />
<img width="1106" alt="Desktop-2" src="https://github.com/user-attachments/assets/ec92e240-74b4-4db8-a863-e03a5fdc9f7e" />

**Mobile: **

<img width="361" alt="IOS-1" src="https://github.com/user-attachments/assets/79231253-120a-4077-8714-c4cecd7247ed" />
<img width="354" alt="IOS-2" src="https://github.com/user-attachments/assets/81c72019-1937-40e1-8777-fc6462c3586b" />


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
