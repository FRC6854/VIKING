package viking.led;

import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Subsystem;
import viking.OI;

public class LEDController extends Subsystem implements LEDMappings {
    private static LEDController instance;
    private static OI oi;

    public static enum LEDMode {
        TELEOP,
        AUTO,
        VISION,
        DEFAULT,
        ERROR
    };

    private LEDMode currentMode = LEDMode.DEFAULT;

    public LEDController () {
        oi = OI.getInstance();
    }

    public void setMode(LEDMode mode) {
        currentMode = mode;

        switch (mode) {
            case TELEOP:
                setTeleop();
                break;
            case AUTO:
                setAuto();
                break;
            case VISION:
                setVision();
                break;
            case DEFAULT:
                setDefault();
                break;
            case ERROR:
                setError();
                break;
            default:
                setDefault();
                break;
        }
    }

    private void setTeleop() {
        if(oi.getAlliance() == Alliance.Blue) {
            oi.ledDataSerialPort(Character.toUpperCase(TELEOP));
        }
        else if(oi.getAlliance() == Alliance.Red) {
            oi.ledDataSerialPort(TELEOP);
        }
        else {
            oi.ledDataSerialPort(TELEOP);
        }
    }

    private void setAuto() {
        if(oi.getAlliance() == Alliance.Blue) {
            oi.ledDataSerialPort(Character.toUpperCase(AUTO));
        }
        else if(oi.getAlliance() == Alliance.Red) {
            oi.ledDataSerialPort(AUTO);
        }
        else {
            oi.ledDataSerialPort(AUTO);
        }
    }

    private void setVision() {
        if(oi.getAlliance() == Alliance.Blue) {
            oi.ledDataSerialPort(Character.toUpperCase(VISION));
        }
        else if(oi.getAlliance() == Alliance.Red) {
            oi.ledDataSerialPort(VISION);
        }
        else {
            oi.ledDataSerialPort(VISION);
        }
    }

    private void setDefault() {
        if(oi.getAlliance() == Alliance.Blue) {
            oi.ledDataSerialPort(Character.toUpperCase(DEFAULT));
        }
        else if(oi.getAlliance() == Alliance.Red) {
            oi.ledDataSerialPort(DEFAULT);
        }
        else {
            oi.ledDataSerialPort(DEFAULT);
        }
    }

    private void setError() {
        oi.ledDataSerialPort(ERROR);
    }

    public static LEDController getInstance () {
        if (instance == null)
          instance = new LEDController();
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
        
    }
}
