package pojo;

import java.util.List;

import static logging.Log.LOGGER;

public class Subject {

	// Subject's spreadsheet id
	private final String spreadSheetId;
	// Subject's spreadsheet reading range
	private final String readRange;
	// Subject's Spreadsheet updating range
	private final String updateRange;
	// Number of classes collected from subject's sheet
	private Integer classes;
	// Students' data collected from subject's sheet
	private List<List<Object>> studentsData;
	// Data to be written on the subject's sheet
	private List<List<Object>> dataToWrite;

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

	public Integer getClasses () {
		return classes;
	}

	public void setClasses (List<Object> classes) {
		// Converts List<Object> to a string, removes everything but numbers from it, parses to integer and returns the value
		this.classes = Integer.parseInt(String.valueOf(classes).replaceAll("[^\\d]", ""));
	}

	public List<List<Object>> getStudentsData () {
		return studentsData;
	}

	public void setStudentsData (List<List<Object>> studentsData) {
		this.studentsData = studentsData;
	}

	public List<List<Object>> getDataToWrite () {
		return dataToWrite;
	}

	public void setDataToWrite (List<List<Object>> dataToWrite) {
		this.dataToWrite = dataToWrite;
	}
}
