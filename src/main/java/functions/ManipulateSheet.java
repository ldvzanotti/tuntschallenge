package functions;

import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import pojo.Subject;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static authentication.ConnectionFactory.createSheetsService;
import static logging.Log.LOGGER;

public interface ManipulateSheet extends CalculateSituation {

	//Method that reads data from sheet
	public static void readSheet(Subject sheet) throws IOException, GeneralSecurityException {
		LOGGER.info("Reading data...");
		ValueRange result = createSheetsService().spreadsheets().values().get(sheet.getSpreadSheetId(), sheet.getReadRange()).execute();
		sheet.setDataCollected(result.getValues());
	}

	//Method that writes data on sheet
	public static void updateSheet(Subject sheet) throws IOException, GeneralSecurityException {
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