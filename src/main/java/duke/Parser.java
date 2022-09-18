package duke;

import java.time.LocalDateTime;

public class Parser {

    /**
     * Decodes a task from the given encoded string
     * @param line
     * @return the task
     */
    public static Task parseTask(String line) {
        String[] strings = line.split("\\|");
        switch (strings[0]) {
        case "T":
            return new Todo(strings[2], strings[1].equals("X"));
        case "D":
            return new Deadline(strings[2], LocalDateTime.parse(strings[3]), strings[1].equals("X"));
        case "E":
            return new Event(strings[2], LocalDateTime.parse(strings[3]), strings[1].equals("X"));
        default:
            return null;
        }
    }

    /**
     * Decodes a LocalDateTime from the given encoded string
     * @param s
     * @return LocalDateTime
     */
    public static LocalDateTime parseDateTime(String s) {
        String[] dtStrings = s.split(" ");
        String date = dtStrings[0];

        String[] dateArr = date.split("/");
        String time = dtStrings[1];

        LocalDateTime dt = LocalDateTime.of(
                Integer.parseInt(dateArr[2]),
                Integer.parseInt(dateArr[1]),
                Integer.parseInt(dateArr[0]),
                Integer.parseInt(time.substring(0, 2)),
                Integer.parseInt(time.substring(2, 4)),
                0);

        return dt;
    }
    
}
