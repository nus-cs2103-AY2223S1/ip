import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static String getDesc(String rest) {
        int endIndex = rest.indexOf("/") - 1;
        return rest.substring(0, endIndex);
    }
    public static String getDate(String rest) {
        String dateFormatted = "";
        try {
            int i = rest.indexOf("/");
            if (i == -1) {
                return "noDate";
            }
            String dateString = rest.substring(i + 4);
            DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime date = LocalDateTime.parse(dateString, form);
            dateFormatted = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
            return dateFormatted;
        } catch (DateTimeParseException e) {
            DobbyChat.wrongDateFormat();
        }
        return "wrongDateFormat";
    }
    public static String getCmd(String task) {
        return task.split(" ")[0];
    }
    public static String getRest(String task) {
        return task.split(" ")[1];
    }

    //method to create new Command object
    public static Command parse(String cmd) {
        switch(cmd) {
        case "bye" :
        case "quit":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand();
        case "unmark":
            return new UnmarkCommand();
        case "delete":
            return new DeleteCommand();
        case "todo":
        case "event":
        case "deadline":
            return new TaskCommand();
        default:
            return new ErrorCommand();
        }
    }
}
