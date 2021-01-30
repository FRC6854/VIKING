package viking.led;

import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import viking.OI;

public class LEDController extends SubsystemBase implements LEDMappings {
    private static LEDController instance;
    private static OI oi;

    public static enum LEDMode {
        LCF,
        LCB,
        UCF,
        UCB,
        BOTH_FWRD,
        BOTH_BKWD,
        NO_VISION,
        VISION,
        CLIMB_ACTIVE,
        WINCH_ACTIVE,
        LOW_VOLTAGE,
        DEFAULT
    };

    private LEDMode currentMode = LEDMode.DEFAULT;

    private LEDController () {
        oi = OI.getInstance();
    }

    public void setMode(LEDMode mode) {
        currentMode = mode;

        switch (mode) {
            case LCF:
                sendData(LCF);
                break;
            case LCB:
                sendData(LCB);
                break;
            case UCF:
                sendData(UCF);
                break;
            case UCB:
                sendData(UCB);
                break;
            case BOTH_FWRD:
                sendData(BOTH_BACKWARD);
                break;
            case BOTH_BKWD:
                sendData(BOTH_BACKWARD);
                break;
            case NO_VISION:
                sendData(NO_VISION);
                break;
            case VISION:
                sendData(VISION);
                break;
            case CLIMB_ACTIVE:
                sendData(CLIMB_ACTIVE);
                break;
            case WINCH_ACTIVE:
                sendData(WINCH_ACTIVE);
                break;
            case LOW_VOLTAGE:
                sendData(LOW_VOLTAGE);
                break;
            case DEFAULT:
                sendData(DEFAULT);
                break;
            default:
                sendData(DEFAULT);
                break;
        }
    }

    private void sendData(char data) {
        if (oi.getAlliance() == Alliance.Blue) {
            oi.ledDataSerialPort(Character.toUpperCase(data));
        }
        else {
            oi.ledDataSerialPort(data);
        }
    }

    public LEDMode getCurrentMode() {
        return currentMode;
    }

    public static LEDController getInstance () {
        if (instance == null){
            instance = new LEDController();
        }
        return instance;
    }
}