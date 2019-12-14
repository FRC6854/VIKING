# VIKING
[![](https://jitpack.io/v/FRC6854/VIKING.svg)](https://jitpack.io/#FRC6854/VIKING)

Reusable Systems for Robots
A common library of useful classes and systems intended to be used for all Team 6854 robots
The JitPack is based off of FRC 1540s library ROOSTER and you can go check it out [here](https://github.com/flamingchickens1540/ROOSTER).

## What's Included in VIKING

### Controller
`viking.Controller`

Includes support for X-Box One Controller for driving.

### CSV File Manager
`viking.CSVFileManager`

Read paths from CSV files and also create logs in CSV format for debugging.

### Limelight
`viking.Limelight`

A wrapper for the Limelight using NetworkTables.

### OI
`viking.OI`

Arduino Serial connection wrapper for LEDs, Date/Time formatter, and DriverStation code.

### PID Controller
`viking.controllers.PIDController`

A custom PID Controller for closed-loop control on FRC robots. Probably will be deprecated in 2020 due to WPILib planning to add better PID Controller to their code.

### Swerve Wheel Drive
`viking.controllers.SwerveWheelDrive`

Modular speed controller for our swerve robot since we don't have enough of one motor controller.

### VikingSPX
`viking.controllers.VikingSPX`

Wrapper for VictorSPX from CTRE. Includes easy to use controls + ability to follow master controllers.

### VikingSRX
`viking.controllers.VikingSPX`

Wrapper for TalonSRX from CTRE. Includes easy to use controls + closed-loop control + motion profiling.

### LED Controller
`viking.led.LEDController`

Controls current LED mode.

### LED Mappings
`viking.led.LEDMappings`

LED codes for certain patterns on the Arduino side code e.g. `a` means Red Full Colour.

## Installation

Add the library by adding these lines in your `build.gradle` file:

```Gradle
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    // other dependencies
    compile 'com.github.FRC6854:VIKING:<latest-release-tag>'
}
```

Additionally, you should be using the latest version of GradleRIO with CTRE Phoenix vendor libraries installed.

We use [JitPack](https://jitpack.io) as a Gradle/Maven repository. This means that if you add the project using Gradle it will be automatically updated with the latest changes to the `master` branch, as well as source code and documentation .jar files.

If you want to see an example for how this is installed, check out the [2019 off-season robot code](https://github.com/FRC6854/2019OffSeasonRobot) and look in the `build.gradle` file.
