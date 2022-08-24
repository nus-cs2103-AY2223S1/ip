import java.util.Objects;

public class Parser {

    public String parseCommand(String action) {
        if (Objects.equals(action.strip(), "bye")) {
            return "bye";
        } else if (Objects.equals(action.strip(), "list")) {
            return "list";
        } else if (action.length() >= 4 && Objects.equals(action.substring(0, 4), "mark")) {
            return "markTask";
        } else if (action.length() >= 6 && Objects.equals(action.substring(0, 6), "unmark")) {
            return "unMarkTask";
        } else if (action.length() >= 6 && Objects.equals(action.substring(0, 6), "delete")) {
            return "deleteTask";
        } else if (isValidTask(parseTaskType(action))) {
            return "addToList";
        } else {
            return "";
        }
    }

    private boolean isValidTask(String taskType) {
        return taskType.equals("todoTask") ||
                taskType.equals("eventTask") ||
                taskType.equals("deadlineTask");
    }

    public String parseTaskType(String action) {
        if (action.length() >= 4 && Objects.equals(action.substring(0, 4), "todo")) {
            return "todoTask";
        } else if (action.length() >= 5 && Objects.equals(action.substring(0, 5), "event")) {
            return "eventTask";
        } else if (action.length() >= 8 && Objects.equals(action.substring(0, 8), "deadline")) {
            return "deadlineTask";
        } else {
            return "";
        }
    }
}
