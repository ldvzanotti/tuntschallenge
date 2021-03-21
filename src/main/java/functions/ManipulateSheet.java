package functions;

import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import pojo.Subject;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static authentication.ConnectionFactory.createSheetsService;
import static logging.Log.LOGGER;

public interface ManipulateSheet {

	//Method that reads data from sheet
	static void readSheet (Subject sheet) throws IOException, GeneralSecurityException {
		LOGGER.info("Reading data...");
		//Reads data
		ValueRange result = createSheetsService().spreadsheets().values().get(sheet.getSpreadSheetId(), sheet.getReadRange()).execute();
		//Sets data collected to subject's attributes
		sheet.setClasses(result.getValues().get(0));
		sheet.setStudentsData(result.getValues().subList(2, result.getValues().size()));
	}

	//Method that writes data on sheet
	static void updateSheet (Subject sheet) throws IOException, GeneralSecurityException {
		LOGGER.info("Updating sheet...");
		ValueRange data = new ValueRange().setValues(sheet.getDataToWrite());
		UpdateValuesResponse write = createSheetsService()
				.spreadsheets()
				.values()
				.update(sheet.getSpreadSheetId(), sheet.getUpdateRange(), data)
				.setValueInputOption("USER_ENTERED")
				.execute();
		LOGGER.info("Update completed. " + write.getUpdatedCells() + " cells updated.");
	}
}