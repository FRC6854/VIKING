package viking.logging;

import edu.wpi.first.wpilibj.Timer;

public class Logging {
	/**
	 * Log string with timestamp
	 * @param str String to log
	 */
	public static void log(String str) {
		System.out.printf("[%.5f] %s\n", Timer.getFPGATimestamp(), str);
	}

	/**
	 * Log current function call
	 */
	public static void trace() {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		System.out.printf("[%.5f] TRACE %s:%d %s.%s\n", Timer.getFPGATimestamp(),
						  stacktrace[2].getFileName(), stacktrace[2].getLineNumber(),
						  stacktrace[2].getClassName(), stacktrace[2].getMethodName());
	}

	/**
	 * Log current function call as unimplemented
	 */
	public static void unimp() {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		System.out.printf("[%.5f] UNIMPLEMENTED %s.%s\n", Timer.getFPGATimestamp(),
						  stacktrace[2].getClassName(), stacktrace[2].getMethodName());
	}
}
