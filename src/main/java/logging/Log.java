package logging;

import java.io.IOException;
import java.util.logging.*;

public class Log {

	public final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void setupLogger() {

		LogManager.getLogManager().reset();
		LOGGER.setLevel(Level.ALL);

		ConsoleHandler ch = new ConsoleHandler();
		ch.setFormatter(new SimpleFormatter());
		ch.setLevel(Level.INFO);
		LOGGER.addHandler(ch);

		try {
			FileHandler fh = new FileHandler("Log.txt", true);
			fh.setFormatter(new SimpleFormatter());
			fh.setLevel(Level.FINE);
			LOGGER.addHandler(fh);
		} catch (
				IOException e) {
			LOGGER.log(Level.SEVERE, "Logger not working!", e);
		}
	}
}
