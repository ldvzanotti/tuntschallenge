package functions;

import pojo.Subject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static logging.Log.LOGGER;

public interface CalculateSituation {

	//Method that calculates average
	static int calculateAverage (List<List<Object>> data, int index) {
		//Gets p1, p2 and p3 grades from the list of data collected from the sheet
		//As the grades are in a list of a list of objects, converts objects to string and then parse to integer
		LOGGER.finer("Getting grades from data collected from the sheet...");
		float p1 = Float.parseFloat(String.valueOf(data.get(index).get(3)));
		float p2 = Float.parseFloat(String.valueOf(data.get(index).get(4)));
		float p3 = Float.parseFloat(String.valueOf(data.get(index).get(5)));
		LOGGER.finer("Calculating average...");
		//Returns average grade, rounded to nearest integer number
		return Math.round((p1 + p2 + p3) / 3);
	}

	//Method that calculates student situation
	static void checkSituation (Subject sheet) {
		LOGGER.info("Checking situation...");
		List<List<Object>> situation = new ArrayList<>();

		//Iterates on the list
		for (int i = 0; i < sheet.getStudentsData().size(); i++) {
			LOGGER.fine("Calculating situation for student #" + (i+1));
			LOGGER.fine("Checking numbers of absences for student #" + (i+1));
			int absences = Integer.parseInt(String.valueOf(sheet.getStudentsData().get(i).get(2)));
			// Checks if student failed by absence - more than 25% of the number of classes
			if (absences > sheet.getClasses() * 0.25) {
				situation.add(i, Arrays.asList("Reprovado por Falta", ""));
				// If student did not fail by absence
			} else {
				// Gets average for current index
				LOGGER.fine("Calculating average for student #" + (i+1));
				int m = calculateAverage(sheet.getStudentsData(), i);
				// If grade was above 7 and prints approval
				if (m >= 70) situation.add(i, Arrays.asList("Aprovado", ""));
					// If grade wasn't above 7 and is equal or greater than 5, prints final exam and needed grade
				else if (m >= 50) situation.add(i, Arrays.asList("Exame Final", (100 - m)));
					// If grade was bellow 5, prints failure by grade
				else situation.add(i, Arrays.asList("Reprovado por Nota", ""));
			}
			LOGGER.fine("Calculation completed for student #" + (i+1));
		}
		LOGGER.info("Calculations completed for the entire class.");

		//Returns data to be updated on sheet
		sheet.setDataToWrite(situation);
		LOGGER.info("Data ready to update sheet.");
	}
}
