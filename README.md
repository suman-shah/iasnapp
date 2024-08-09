implementation libs.android.pdf.viewer 
if it does not work then
Step 1: maven { url "https://jcenter.bintray.com" } put this code inside setting.gradle file 
Final code look like this one 
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url "https://jcenter.bintray.com" }
    }
}
Step 2: android.enableJetifier=true put this line of code inside gradle.properties file
Final code look like this one (look at last line of code)
android.nonTransitiveRClass=true
android.enableJetifier=true
