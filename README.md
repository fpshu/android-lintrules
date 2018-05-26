## Custom Android Lint Rules

This project contains the following rules:

### AndroidColorDetector

Built-in Android color resources like **android:color/transparent** should not be used. 
These can be overridden by other manufacturers. 
For example on some phones the white differ from 0xFFFFFF and the transparent replaced with white. 
Instead define them in colors.xml. 

### ShapeBackgroundDetector

Shape drawable xml resources must contain a `<solid>` tag, which is a background. The only exception when a gradient is defined.
The background color varies on different phones, so it can be black, transparent or white.

## Install 

Include jitpack repository in the buildscript:

```Groovy
buildscript {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

Add this library as a dependency with **lintChecks** configuration:

```Groovy
dependencies {
    lintChecks 'com.github.fpshu:android-lintrules:master-SNAPSHOT'
}
```
