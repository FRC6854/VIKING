package viking.controllers;

public class CANDeviceID {
    private final int mDeviceNumber;
    private final String mBus;

    public CANDeviceID(int deviceNumber, String bus) {
        mDeviceNumber = deviceNumber;
        mBus = bus;
    }

    // Use the default bus name (empty string).
    public CANDeviceID(int deviceNumber) {
        this(deviceNumber, "");
    }

    public int getDeviceNumber() { return mDeviceNumber; }

    public String getBus() { return mBus; }

    public boolean equals(CANDeviceID other) {
        return other.mDeviceNumber == mDeviceNumber && other.mBus == mBus;
    }
}
