# VIKING
[![](https://jitpack.io/v/FRC6854/VIKING.svg)](https://jitpack.io/#FRC6854/VIKING) [![Build Status](https://dev.azure.com/VikingRobotics/VIKING/_apis/build/status/FRC6854.VIKING?branchName=master)](https://dev.azure.com/VikingRobotics/VIKING/_build/latest?definitionId=1&branchName=master)

Reusable Systems for Robots
A common library of useful classes and systems intended to be used for all Team 6854 robots
The JitPack is based off of FRC 1540s library ROOSTER and you can go check it out [here](https://github.com/flamingchickens1540/ROOSTER).

## VIKING library content

### Controller
`viking.Controller`

Includes support for XBox One Controller with pre-made inverts for proper values.

### CSV File Manager (WIP)
`viking.CSVFileManager`

Read paths from CSV files and also create logs in CSV format for debugging.

### Limelight
`viking.vision.Limelight`

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
`viking.controllers.ctre.VikingSPX`

Wrapper for VictorSPX from CTRE. Includes easy to use controls + ability to follow master controllers.

### VikingSRX
`viking.controllers.ctre.VikingSPX`

Wrapper for TalonSRX from CTRE. Includes easy to use controls + closed-loop control + motion profiling.

### VikingMAX
`viking.controllers.rev.VikingMAX`

Wrapper for CANSparkMAX from REV Robotics. Includes easy to use controls + closed-loop control + smart motion + follower controllers.

### ColorSensor
`viking.controllers.rev.ColorSensor`

Wrapper for ColorSensorV3 from REV Robotics. Comes with matcher for detecting colours from the sensor output.

### Math
`viking.math`

Full of math functions for swerve and other things.

### MotionProfileUtil
`viking.motion.MotionProfileUtil`

Motion profile utility.

### Logging
`viking.Logging`

Logging library, log to stdio or file with timestamp.

## Installation

Add the library by adding these lines in your `build.gradle` file:

```Gradle
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.FRC6854:VIKING:<latest-release-tag>'
}
```

Make sure you have the following `vendordeps` added to your project.
- Phoenix
- REVColorSensorV3
- REVRobotics

We use [JitPack](https://jitpack.io) as a Gradle/Maven repository. This means that if you add the project using Gradle it will be automatically updated with the latest changes to the `master` branch, as well as source code and documentation .jar files.

If you want to see an example for how this is installed, check out the [2021 Infinite Recharge Robot code](https://github.com/FRC6854/2021InfiniteRechargeOfficial) and look in the `build.gradle` file.
