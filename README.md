# Smart Campus RideSharing App - Ndejje University

**Smart Campus Ride** is a modern Android application designed to facilitate safe, affordable, and efficient transportation for the Ndejje University community. The system connects students for ride-sharing between campuses (Main, Kampala, Bombo, and Luwero) and provides specialized property delivery services.

---

## 🚀 Key Features

### 1. Secure Authentication
*   **OTP Verification:** Simulated 4-digit code verification for student emails.
*   **Role-Based Access:** Support for both **Students** (Riders) and **Drivers**.
*   **Security Audit:** Real-time tracking of login attempts visible in the notification panel.

### 2. Comprehensive Ride Management
*   **Available Rides:** Real-time list of offers and requests with pricing and seat availability.
*   **Property Delivery:** Specialized workflow for moving items with category selection (**Delicate, Hard, Documents, Other**).
*   **Ride History:** Detailed logs of past trips, including dates, routes, and completion status.

### 3. Live Tracking & Interaction
*   **Animated Movement:** A custom Canvas-based tracking engine that simulates ride progress along campus routes.
*   **In-App Calling:** Direct communication bridge between students and drivers.
*   **ETA Feedback:** Real-time estimation of driver arrival.

### 4. Modern User Experience
*   **Adaptive Dark Mode:** Full system-wide dark theme support to reduce eye strain during late-night campus travel.
*   **Institutional Branding:** Themed with Ndejje University colors (Orange & Navy Blue).
*   **Notifications & Insights:** Personalized dashboard showing total rides completed and security alerts.

---

## 🛠 Tech Stack

*   **Language:** Kotlin (100%)
*   **UI Framework:** Jetpack Compose (Declarative UI)
*   **Design System:** Material Design 3
*   **Navigation:** Jetpack Navigation Component
*   **Animation:** Compose Animation API & PathMeasure for route simulation.
*   **Build System:** Gradle Kotlin DSL with Version Catalogs.
*   **Compatibility:** Optimized for **Android 15 (SDK 35)** using **AGP 9.0**.

---

## 📂 Project Structure

```
app/src/main/java/.../
├── model/
│   └── User.kt              # Core data models (User, RideHistory, etc.)
├── ui/
│   ├── theme/               # Material 3 Color schemes and Dark Mode logic
│   └── screens/             # Modular screen implementations
│       ├── HomeScreen.kt    # Main dashboard & Ride listing
│       ├── LoginScreen.kt   # Auth & Car logo animations
│       ├── TrackingScreen.kt# Canvas-based movement simulation
│       ├── HistoryScreen.kt # Past activity logs
│       └── ProfileScreen.kt # Theme toggling & user settings
└── MainActivity.kt          # Typed navigation host & state management
```

---

## 🔧 Installation & Setup

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/your-repo/Smart-Campus-Ride.git
    ```
2.  **Open in Android Studio:**
    *   Requires **Android Studio Ladybug** or newer.
    *   Sync Gradle to download dependencies from the version catalog.
3.  **Build & Run:**
    *   Target SDK: 35 (Android 15).
    *   Minimum SDK: 24 (Android 7.0).

---

## 🎓 Academic Context
*   **Course:** Mobile Programming (2026)
*   **Institution:** Ndejje University, Faculty of Engineering & ICT
*   **Presentation Link:** [Watch on YouTube](https://youtu.be/cMJnnhqhuco)
*   **Group 9 Team Members:**
    *   **Lomeling Peter:** Lead Developer & UI/UX
    *   **Nakitto Sarah:** UI/UX & GitHub Manager
    *   **Mugwanya Henry:** Testing & Quality Assurance
    *   **Musinguzi Edger:** Research & Documentation

---

## 📄 License
This project is developed for academic purposes as part of the Ndejje University 2026 Final Exam.
 Smart Campus RideSharing App



 Team Members and their Roles
| Name            | Student ID  | Role                          |
|-----------------|-------------|-------------------------------|
| Sarah Nakitto   | 1008739405  | GitHub Master & UX Specialist |
| Henry Mugwanya  | 1008965430  | Testing & QA Engineer         |
| Peter Lomeling  | 1008234567  | Lead Developer                |
| Edgar Musingunzi| 1008478923  | Documentation & Research Lead |


 Project Identity
This project was developed as part of the Mobile Programming (BCS 2201 / BIT 2205) capstone.  
It addresses the Ndejje University Campuses Kampala /Luwero community challenges of Travelling with uneasy due to different drivers transporting them and also not known by them through a digital, user‑friendly solution.
