import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static Task toTask(String taskRecord) {
        if (taskRecord.startsWith("T")) {
            // the task is a todo
            String description = taskRecord.split("\\|", 3)[2].trim();
            int status = Integer.parseInt(taskRecord.split("\\|", 3)[1].trim());
            return new ToDo(description, checkStatus(status));
        }
        else if (taskRecord.startsWith("D")) {
            // the task is a deadline
            String description = taskRecord.split("\\|", 4)[2].trim();
            int status = Integer.parseInt(taskRecord.split("\\|", 4)[2].trim());
            String by = taskRecord.split("\\|", 4)[3].trim();
            return new Deadline(description, checkStatus(status), parseDate(by));
        }
        else {
            // the task is an event
            String description = taskRecord.split("\\|", 4)[2].trim();
            int status = Integer.parseInt(taskRecord.split("\\|", 4)[2].trim());
            String at = taskRecord.split("\\|", 4)[3].trim();
            return new Event(description, checkStatus(status), at);
        }
    }

    public static boolean checkStatus(int status) {
        // if status == 0, the task is not done yet
        // if status == 1, the task is done
        return status != 0;
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
