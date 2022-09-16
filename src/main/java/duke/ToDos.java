package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    public ToDos(String testTest, int x) {
        super(testTest);
    }

    public String getToDoDescirption() {return description;}

    public static String getItem() {
        return "[T]";
    }


}
