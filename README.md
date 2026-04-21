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

## Assignment 4 Update
- Manifest Configuration reuse 
- Selected attribute- android:launchMode="singleTask" on MainActivity
- Effect: If MainActivity is already running, Android will reuse it instead of creating a second copy. This helps prevent duplicate MainActivity screens in the back stack.
## Assignment 5 Update
- A new 5th button in Main Activity: View Image Activity
- A new ThirdActivity
- Camera functionality using an intent
- Display of the captured image inside ThirdActivity
- Runtime camera permission handling for real-device support
### Tested Environment
- **Device:** Samsung Galaxy S22 5G
- **Android Version:** Android 13

## Assignment 6 Update
- Added an automated UI test using **UI Automator**
- The test launches the app, clicks **Start Activity Explicitly**, and verifies content in `SecondActivity`
- Verified at least one listed mobile software engineering challenge is displayed

### Verified Challenge Examples
- Device Fragmentation
- Battery Optimization
- App Lifecycle Complexity
- Security and Privacy
- Rapid SDK Updates

## Assignment 7 Update
This version extends the app with:

- A custom dangerous permission named `com.example.cs712assignment2.MSE712`
- Protection of the exported `SecondActivity` using the custom permission
- Runtime permission request in `MainActivity` before opening `SecondActivity`

###  How to Run
Clone the repository:
```bash
git clone https://github.com/ummehabibaprity/CS712AndroidApp.git
