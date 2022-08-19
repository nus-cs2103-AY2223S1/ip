package duke.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Utils {

	public static LocalDate parseDate(String inputDate) {
		return LocalDate.parse(inputDate);
	}

	public static String convertLocalDate(LocalDate date) {
		return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
	}
}
