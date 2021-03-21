package logging;

import java.io.IOException;
import java.util.logging.*;

public class Log {

	public final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void setupLogger() {
		//Resets log configuration
		LogManager.getLogManager().reset();
		LOGGER.setLevel(Level.ALL);
		//Console handler configuration
		ConsoleHandler ch = new ConsoleHandler();
		ch.setFormatter(new SimpleFormatter());
		ch.setLevel(Level.INFO);
		LOGGER.addHandler(ch);
		//File handler configuration
		try {
			FileHandler fh = new FileHandler("Log.txt", true);
			fh.setFormatter(new SimpleFormatter());
			fh.setLevel(Level.FINE);
			LOGGER.addHandler(fh);
		}
		//Throws exception if file handler is not working
		catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Logger not working!", e);
		}
	}
}
