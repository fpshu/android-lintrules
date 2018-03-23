# Custom Lint rules
This repository contains custom Android lint rules to use in projects.

## Rules

### Android built-in color detector
Detect usage of built in colors like `android:color/white`. These resource are overwritten by manufacturers.

### Shape background detector
All drawable shapes should contain a background by providing `<solid>` tag. The default background on some phones are black instead of transparent.

## Usage
* Push changes to https://github.com/fpshu/android-lintrules
* Add this to gradle:

```
lintChecks 'com.github.fpshu:android-lintrules:master-SNAPSHOT'
```