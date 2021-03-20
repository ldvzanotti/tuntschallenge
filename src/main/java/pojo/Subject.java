package pojo;

import functions.ManipulateSheet;

import java.util.List;

import static logging.Log.LOGGER;

public class Subject implements ManipulateSheet {

	// Subject's spreadsheet id
	private String spreadSheetId;
	// Subject's spreadsheet reading range
	private String readRange;
	// Subject's Spreadsheet updating range
	private String updateRange;
	// Data collected from the subject's sheet
	private List<List<Object>> dataCollected;
	// Data to be written on the subject's sheet
	private List<List<Object>> dataToWrite;

	public Subject() {

	}

	public Subject (String spreadSheetId, String readRange, String updateRange) {
		LOGGER.info("Creating subject...");
		this.spreadSheetId = spreadSheetId;
		this.readRange = readRange;
		this.updateRange = updateRange;
	}

	public String getSpreadSheetId () {
		return spreadSheetId;
	}

	public String getReadRange () {
		return readRange;
	}

	public String getUpdateRange () {
		return updateRange;
	}

	public List<List<Object>> getDataCollected () {
		return dataCollected;
	}

	public void setDataCollected (List<List<Object>> dataCollected) {
		this.dataCollected = dataCollected;
	}

	public List<List<Object>> getDataToWrite () {
		return dataToWrite;
	}

	public void setDataToWrite (List<List<Object>> dataToWrite) {
		this.dataToWrite = dataToWrite;
	}
}
