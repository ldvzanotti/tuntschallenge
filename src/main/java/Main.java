import logging.Log;
import pojo.Subject;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static functions.CalculateSituation.checkSituation;
import static functions.ManipulateSheet.readSheet;
import static functions.ManipulateSheet.updateSheet;
import static logging.Log.LOGGER;

public class Main {

	public static void main (String[] args) throws IOException, GeneralSecurityException {
		Log.setupLogger();
		//Instantiating a subject to gather data
		Subject softwareEngineering = new Subject(
				"1FZU5BDgnBJh9YC-p5d6dfG2sbnueKFC-R3tFL83z0Ws",
				"engenharia_de_software!A2:F",
				"engenharia_de_software!G4:H");

		readSheet(softwareEngineering);
		checkSituation(softwareEngineering);
		updateSheet(softwareEngineering);
		LOGGER.info("Work completed.");
	}
}
