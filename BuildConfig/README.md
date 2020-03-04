# Build Config

Playground project for messing around with android build configurations.

## Articles
### Optimization
- https://developer.android.com/studio/build/shrink-code#optimization
- https://jakewharton.com/blog/ (Look at the R8 stuff)
### Build Variant/Flavor Setup
- https://developer.android.com/studio/build/build-variants

## Findings

### Function Shrinking
Mainly focused on if/else and uncalled functions shrinking. Turns out the Java Bytecode generation also does some shrinking.

- Java Bytecode shrinking
    - `if(false)` removed in bytecode
    - `if(conditional)` not removed in bytecode even if conditional resolves to false
        - specifically checked `if(!BuildConfig.DEBUG)` in DEBUG build and it still showed up
- R8 shrinking
    - uncalled methods removed
    - `if(conditional)` removed if it resolves to `if(false)`

### 3rd Party Dependency Shrinking
Added Timber library to see what would happen when it goes through code shrinking

- No calls to Timber
    - APK analysis shows Timber code not included in the build
- `Timber.plant()` only called
    - Code shrinking only includes necessary files to support `.plant()`
        - Other functions are removed (e.g. `.w()`, `.d()`, etc.)
- `Timber.plant()` and `Timber.d()` called
    - Code shrinking only includes necessary files to support `.plant()` and `.d()`
        - Other functions are removed (e.g. `.w()`, etc.)