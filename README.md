# Android PDF Viewer Library Implementation

This repository demonstrates the implementation of the `libs.android.pdf.viewer` library in an Android project.

## Prerequisites

Ensure you have the following set up in your project:
- Android Studio
- Gradle

## Implementation Steps

Follow these steps to successfully implement the `libs.android.pdf.viewer` library in your project.

### Step 1: Add Maven Repository

If the library does not work out of the box, you may need to add a specific Maven repository to your project.

1. Open your `settings.gradle` file.
2. Add the following code inside the `dependencyResolutionManagement` block:

    ```groovy
    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            google()
            mavenCentral()
            maven { url "https://jcenter.bintray.com" }
        }
    }
    ```

### Step 2: Enable Jetifier

To ensure compatibility with older libraries, enable Jetifier in your project.

1. Open your `gradle.properties` file.
2. Add the following line at the end of the file:

    ```groovy
    android.nonTransitiveRClass=true
    android.enableJetifier=true
    ```

## Conclusion

After completing the steps above, your project should be correctly configured to implement the `libs.android.pdf.viewer` library. If you encounter any issues, ensure that your project configuration matches the code provided in this guide.
