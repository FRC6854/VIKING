package viking;

import edu.wpi.first.wpilibj.SerialPort;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to deal with Arduino serial communications, LED strip communication, and fetching current system time
 */
public class OI {
	private static OI instance = null;

	private SerialPort arduino;

	private static char lastCommandArduino = ' ';
	private boolean connected = false;

	public OI() {

		try {
			// Init the SerialPort on baud 9600
			arduino = new SerialPort(9600, SerialPort.Port.kUSB1);

			// Set connected to be false
			connected = true;
		} catch (Exception e) {
			System.out.print("Failed to connect to Arduino: ");
			System.out.println(e.toString());
		}
	}

	/**
	 * Writes LED data to Arduino over serial
	 * @param data
	 */
	public void ledDataSerialPort(char data) {
		if ((lastCommandArduino != data) && (connected == true)) {
			// Set the last command to the command about to be sent
			lastCommandArduino = data;

			// Write the number to the Serial Channel
			arduino.writeString(Character.toString(data));

			// Since the output buffer is 8 bytes and we usually only print 2 bytes, we must flush
			// the buffer to send the line The limitation of this is that we can only send up to
			// 99,999,999
			arduino.flush();

			// Read the current line of text in the Serial Channel
			arduino.readString();
		}
	}

	/**
	 * Method to fetch current system time and format it
	 * @param isFile
	 * @return
	 */
	public static String getCurrentSystemTimeDate(boolean isFile) {
		DateTimeFormatter formatter;

		if (isFile) { // If datetime is used in a filename, use underscore to seperate date and time.
			formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
		} else {
			formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm.ss");
		}

		return formatter.format(LocalDateTime.now());
	}

	/**
	 * Starts OI instance
	 * @return
	 */
	public static OI getInstance() {
		if (instance == null)
			instance = new OI();
		return instance;
	}
}
