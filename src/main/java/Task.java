import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Task {
    private final List<String> taskArray;
    private final String TASK;
    private final String ICON;
    private Boolean completionStatus;
    private String completionIcon;
    private final InputParser PARSER = new InputParser();

    Task(List<String> task, String taskName, String icon) throws DekuExceptions {
        if (task.size() == 0) {
            throw new DekuExceptions("The description of a " + taskName + " cannot be empty.");
        }

        this.TASK = PARSER.parseTask(task);
        this.taskArray = task;
        this.ICON = icon;
        this.completionStatus = false;
        this.completionIcon = "[ ]";
    }

    private String getTask() {
        String output = "";
        for (int i = 0; i < taskArray.size(); i++) {
            String current = taskArray.get(i);
            if (current.charAt(0) == '/') {
                break;
            }
            output += current + " ";
        }
        return (output.equals("")) ? output : output.substring(0, output.length()-1);
    }

    LocalDate getDate() {
        return PARSER.getDate();
    }

    LocalTime getTime() {
        return PARSER.getTime();
    }

    private String getSpecial() {
        String output = "";
        for (int i = 0; i < taskArray.size(); i++) {
            String current = taskArray.get(i);
            if (current.charAt(0) == '/') {
                output = current;
                break;
            }
        }
        return output;
    }

    void setCompletionStatus(boolean set) {
        completionStatus = set;
        if (completionStatus) {
            completionIcon = "[X]";
        } else {
            completionIcon = "[ ]";
        }
    }

    public String saveFormat() {
        String completionParse = (completionIcon.equals("[X]")) ? "1" : "0";
        return ICON + "|" + completionParse + "|" + getTask() + "|" + getSpecial() + "|" + getDate() + "|" + getTime();
    }

    @Override
    public String toString() {
        return "[" + ICON + "]" + completionIcon + " - " + TASK;
    }
}
