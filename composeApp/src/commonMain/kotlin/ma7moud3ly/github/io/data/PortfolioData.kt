package ma7moud3ly.github.io.data

import ma7moud3ly.github.io.model.Fact
import ma7moud3ly.github.io.model.Link
import ma7moud3ly.github.io.model.Portfolio
import ma7moud3ly.github.io.model.Profile
import ma7moud3ly.github.io.model.Project
import ma7moud3ly.github.io.model.Publication
import ma7moud3ly.github.io.model.StackGroup
import ma7moud3ly.github.io.model.Writing

private const val GITHUB = "https://github.com/Ma7moud3ly"
private const val SITE = "https://ma7moud3ly.github.io"
private const val PLAY = "https://play.google.com/store/apps/details?id="
private const val EMAIL = "engma7moud3ly@gmail.com"

/** The content of the portfolio. */
val portfolio = Portfolio(
    profile = Profile(
        name = "Mahmoud Aly",
        title = "Senior Software Engineer",
        tagline = "Android · Kotlin Multiplatform · Hardware & robotics",
        summary = "Software engineer with 8+ years of experience and an electrical & " +
                "communications engineering background. I build Kotlin apps for Android, desktop " +
                "and the web, many of them working with real hardware: EV chargers and smart " +
                "meters, smartphone robots, POS terminals and microcontrollers. I also publish " +
                "open-source Kotlin Multiplatform libraries on Maven Central.",
        facts = listOf(
            Fact("Location", "Cairo, Egypt"),
            Fact("Experience", "8+ years"),
            Fact("Education", "M.Sc. Electrical Engineering"),
            Fact("Languages", "Arabic, English, German"),
            Fact("Email", EMAIL)
        ),
        links = listOf(
            Link("GitHub", GITHUB),
            Link("LinkedIn", "https://www.linkedin.com/in/ma7moud3ly/")
        )
    ),
    projects = listOf(
        Project(
            name = "Powerly",
            description = "A production EV charging platform with real-time charger " +
                    "communication, payments and maps.",
            platforms = listOf("Android"),
            tags = listOf(
                "Kotlin", "Jetpack Compose", "Material 3", "Coroutines", "Multi-module",
                "Koin", "Ktor", "Room", "DataStore", "Paging 3", "WorkManager",
                "MQTT", "WebSocket (Pusher)", "Stripe", "Google Maps", "Firebase", "MockK"
            ),
            links = listOf(
                Link("Google Play", "${PLAY}com.esttp.powerly"),
                Link("GitHub", "https://github.com/powerly-ev/open-ev-charge-android-app")
            )
        ),
        Project(
            name = "Gasable",
            description = "A gas delivery marketplace with 1M+ downloads on Google Play.",
            platforms = listOf("Android"),
            tags = listOf(
                "Kotlin", "Java", "Jetpack Compose", "Multi-module", "Product flavors",
                "Google Maps", "Firebase", "Amazon PayFort", "GMS / HMS"
            ),
            links = listOf(Link("Google Play", "${PLAY}com.esttp.gasable"))
        ),
        Project(
            name = "Micro REPL",
            description = "A MicroPython IDE over USB serial, with ~5,000 users: a live REPL, " +
                    "a file explorer and a code editor.",
            platforms = listOf("Android", "Desktop", "Web"),
            tags = listOf(
                "Kotlin Multiplatform", "Compose Multiplatform", "Koin", "Navigation 3",
                "Coroutines", "Okio", "USB Serial", "Nemo Editor", "MicroPython"
            ),
            links = listOf(
                Link("Google Play", "${PLAY}micro.repl.ma7moud3ly"),
                Link("F-Droid", "https://f-droid.org/packages/micro.repl.ma7moud3ly/"),
                Link("Web", "$SITE/micro-repl"),
                Link("GitHub", "$GITHUB/micro-repl")
            )
        ),
        Project(
            name = "Smartphone Robot",
            description = "OIST's Android framework for smartphone robots, migrated to Kotlin " +
                    "with ROS 2 integration.",
            platforms = listOf("Android"),
            tags = listOf(
                "Kotlin", "Java", "Coroutines", "ROS 2 (rosbridge)", "USB Serial",
                "CameraX", "MediaPipe", "FlatBuffers", "Chaquopy", "Docker"
            ),
            links = listOf(Link("GitHub", "https://github.com/oist/smartphone-robot-android"))
        ),
        Project(
            name = "Nemo Editor",
            description = "A code editor component for Compose apps, with syntax highlighting, " +
                    "autocomplete, themes, find and replace, and tabs.",
            platforms = listOf("Android", "iOS", "Desktop", "Web"),
            tags = listOf("Library", "Kotlin Multiplatform", "Compose Multiplatform", "Maven Central"),
            links = listOf(
                Link("Maven Central", "https://central.sonatype.com/artifact/io.github.ma7moud3ly/nemo-editor"),
                Link("Klibs", "https://klibs.io/project/Ma7moud3ly/nemo-editor"),
                Link("Demo", "$SITE/nemo-editor"),
                Link("GitHub", "$GITHUB/nemo-editor")
            )
        ),
        Project(
            name = "Quran Planet",
            description = "Read the Quran and listen to dozens of reciters, live or offline, " +
                    "with a multi-reciter mode.",
            platforms = listOf("Android", "Desktop", "Web"),
            tags = listOf(
                "Kotlin Multiplatform", "Compose Multiplatform", "Koin", "Ktor",
                "Media3 ExoPlayer", "Coil", "Kotlinx Serialization", "Firebase"
            ),
            links = listOf(
                Link("Google Play", "${PLAY}com.ma7moud3ly.quran"),
                Link("Web", "https://quraan-planet.web.app/"),
                Link("GitHub", "$GITHUB/quran-planet")
            )
        ),
        Project(
            name = "TipFrame",
            description = "Turns a code snippet into a clean, shareable image, with themes, " +
                    "window frames and backgrounds.",
            platforms = listOf("Web"),
            tags = listOf("Kotlin/Wasm", "Compose Multiplatform", "Nemo Editor", "Multiplatform Settings"),
            links = listOf(
                Link("Web", "$SITE/tip-frame"),
                Link("GitHub", "$GITHUB/tip-frame")
            )
        ),
        Project(
            name = "YT-DLP Android",
            description = "An Android interface for yt-dlp, running the Python library inside " +
                    "the app.",
            platforms = listOf("Android"),
            tags = listOf("Kotlin", "Jetpack Compose", "Python", "Chaquopy", "Coil", "Firebase"),
            links = listOf(
                Link("Download", "$GITHUB/dlp-android/releases/latest"),
                Link("GitHub", "$GITHUB/dlp-android")
            )
        ),
        Project(
            name = "Podcast App",
            description = "Podcasts with dynamic content and several languages, built with " +
                    "MVVM, Paging 3 and clean architecture.",
            platforms = listOf("Android"),
            tags = listOf(
                "Kotlin", "Jetpack Compose", "Hilt", "Paging 3", "Navigation 3",
                "Kotlinx Serialization", "MVVM", "Clean Architecture"
            ),
            links = listOf(Link("GitHub", "$GITHUB/PodcastApp"))
        )
    ),
    publications = listOf(
        Publication(
            title = "Pay Attention to the Speech: COVID-19 Diagnosis Using Machine Learning and " +
                    "Crowdsourced Respiratory and Speech Recordings",
            venue = "Alexandria Engineering Journal (Elsevier, Q1)",
            year = "2022",
            role = "First author",
            url = "https://doi.org/10.1016/j.aej.2021.08.070"
        )
    ),
    writings = listOf(
        Writing(
            title = "Pink Python",
            kind = "Book",
            language = "Arabic",
            year = "2018",
            description = "Learn to program in Python in a fun and simple way.",
            links = listOf(
                Link("Download", "$GITHUB/pink-python/releases"),
                Link("GitHub", "$GITHUB/pink-python")
            )
        ),
        Writing(
            title = "Docker - A Practical Guide",
            kind = "Course",
            language = "Arabic & English",
            description = "Ten hands-on chapters, from the first container to production, " +
                    "applied to a Spring Boot and PostgreSQL app, with 60+ interview questions.",
            links = listOf(
                Link("Arabic", "$SITE/docker-practical-guide/"),
                Link("English", "$SITE/docker-practical-guide/en/"),
                Link("GitHub", "$GITHUB/docker-practical-guide")
            )
        )
    ),
    stack = listOf(
        StackGroup("Languages", listOf("Kotlin", "Java", "Python", "C++")),
        StackGroup(
            "Android",
            listOf(
                "Android SDK", "Jetpack Compose", "Coroutines", "Flow", "Room", "Lifecycle",
                "ViewModel", "Koin", "Hilt", "XML Views"
            )
        ),
        StackGroup("Multiplatform", listOf("Kotlin Multiplatform", "Compose Multiplatform")),
        StackGroup(
            "Architecture",
            listOf("Clean Architecture", "MVVM", "MVI", "Multi-module", "Design Patterns")
        ),
        StackGroup("Networking", listOf("Retrofit", "Ktor", "REST", "WebSocket", "Socket.IO", "MQTT")),
        StackGroup("Firebase", listOf("Auth", "FCM", "Crashlytics", "App Check")),
        StackGroup(
            "Hardware & Robotics",
            listOf(
                "ROS 2 (rosbridge)", "USB Serial / UART", "Bluetooth / BLE", "Wi-Fi", "MQTT",
                "Android POS (ZCS)", "RP2040 / MicroPython", "MediaPipe"
            )
        ),
        StackGroup("Testing", listOf("JUnit", "Mockito", "Espresso", "Robolectric", "Maestro")),
        StackGroup("CI/CD", listOf("GitHub Actions", "Fastlane", "Jenkins", "Docker")),
        StackGroup(
            "AI Coding",
            listOf("Claude Code", "Gemini in Android Studio", "AI PR review in GitHub Actions")
        )
    ),
    source = Link("Source", "$GITHUB/compose-dos-portfolio")
)
