# Movies
Android application following best practices: Kotlin, Coroutines, JetPack, Clean Architecture, Feature Modules, Tests, MVVM, DI, Compose...

Project is focusing on modular, scalable, maintainable, and testable architecture, leading
tech-stack and demonstrates the best development practices.

## Branches
This project has three branches of implementation:
- compose_hilt - default
- xml_koin - xml ui + koin(dependency injection)
- xml_hilt - xml ui + hilt(dependency injection)

## Application Scope

The `movies` displays information about popular films. The data is loaded from the
[TMDb](https://developers.themoviedb.org/4/getting-started/authorization).

The app has a few screens located in multiple feature modules:

- Popular list screen - displays list of movies
- Movie detail screen - display information about the selected movie
- Favourites screen - displays list of favourites movies
- Profile screen - display user first/last name

 <br/>
<p float="left">
  <img src="https://user-images.githubusercontent.com/25104323/235524885-e1c488b7-d066-45eb-82d5-11e1ade667b2.jpg" width="186" />
  <img src="https://user-images.githubusercontent.com/25104323/235524960-cef67f77-d2c5-4c9c-b530-fa0a0afbc585.jpg" width="186" />
  <img src="https://user-images.githubusercontent.com/25104323/235525001-9e0a8495-e70e-428a-925e-5ccd5b092a56.jpg" width="186" />
  <img src="https://user-images.githubusercontent.com/25104323/235525066-cd3aec36-b6dc-4b62-9cee-f101bbe4d852.jpg" width="186" />
</p>

## Tech-Stack

This project takes advantage of best practices, and many popular libraries and tools in the Android ecosystem. Most of
the libraries are in the stable version unless there is a good reason to use non-stable dependency.

* Tech-stack
  * [100% Kotlin](https://kotlinlang.org/)
    + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations
    + [Kotlin Flow](https://kotlinlang.org/docs/flow.html) - data flow across all app layers, including view
  * [Retrofit](https://square.github.io/retrofit/) - networking
  * [Moshi](https://github.com/square/moshi) - parse [JSON](https://www.json.org/json-en.html)
  * [Jetpack](https://developer.android.com/jetpack)
    * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - in-app navigation
    * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when
      lifecycle state changes
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related
      data in a lifecycle-aware way
    * [Room](https://developer.android.com/jetpack/androidx/releases/room) - store offline cache
    * [Compose](https://developer.android.com/jetpack/compose/documentation) - modern toolkit for building native Android UI
  * [Koin](https://insert-koin.io/) - dependency injection (dependency retrieval)
  * [Coil](https://github.com/coil-kt/coil) - image loading library
* Modern Architecture
  * [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
  * Single activity architecture
    using [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)
  * MVVM + MVI (presentation layer)
  * [Android Architecture components](https://developer.android.com/topic/libraries/architecture)
    ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    , [Kotlin Flow](https://kotlinlang.org/docs/flow.html)
    , [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))
  * [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions
* Testing
  * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing)
  * [UI Tests](https://en.wikipedia.org/wiki/Graphical_user_interface_testing) ([Espresso](https://developer.android.com/training/testing/espresso)) - test user interface (WiP)
  * [Mockk](https://mockk.io/) - mocking framework
* Gradle
  * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - define build scripts

### API keys

You need to supply API / client keys for the service the app uses.

- [TMDb](https://developers.themoviedb.org)

Once you obtain the key, you can set them in your `~/local.properties`:

```
# Get this from TMDb
API_KEY=<insert>
```
