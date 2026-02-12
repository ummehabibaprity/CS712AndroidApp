# BasicAndroidAPP – CS712 Assignment 2

##  App Description

This Android app was developed as part of CS712 Assignment 2. It demonstrates the use of **explicit** and **implicit intents** to navigate between two activities.

### Main Activity
- Displays  **Full Name** and **Student ID**
- Includes two buttons:
    - **Start Activity Explicitly** – launches the second activity using an explicit intent
    - **Start Activity Implicitly** – launches the second activity using an implicit intent 

### Second Activity
- Shows **five mobile software engineering challenges**
- Has a button to return to the **Main Activity**

##  Features
- Built using Java and Android Studio
- Uses both explicit and implicit intents
- Simple and clear UI navigation
- Tested on both emulator and real device
- Open-source and hosted on GitHub

##  Testing Environment
- **Device:** Pixel 6 (Emulator)
- **Android Version:** Android 16 (API Level 36.0)
- **IDE:** Android Studio Otter 3 (2025.2.3)
- **OS:** Windows 11
- **Language** Java
- **Build System** Gradle (KTS-based)

## Assignment 3 Update

This version extends the app with:

- Foreground Service with notification
- Dynamic Broadcast Receiver
- Custom broadcast action handling

### Features
- Start Service → Shows notification "Foreground service has started"
- Send Broadcast → Displays toast "Broadcast received!"
- Broadcast receiver registered dynamically in MainActivity
- Receiver unregistered in onStop()

### Tested Environment
Android Version: Android 13
& Device: Samsung Galaxy S22 5G

##  How to Run
Clone the repository:
```bash
git clone https://github.com/ummehabibaprity/CS712AndroidApp.git
