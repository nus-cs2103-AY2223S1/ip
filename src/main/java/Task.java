import java.util.List;

public class Task {
    private final List<String> TASK;
    private final String ICON;
    private Boolean completionStatus;
    private String completionIcon;
    Task(List<String> task) throws DekuExceptions {
        if (task.size() == 0) {
            throw new DekuExceptions( "The description of a task cannot be empty.");
        }
        this.TASK = task;
        this.ICON = "";
        this.completionStatus = false;
        this.completionIcon = "[ ]";
    }

    Task(List<String> task, String taskName, String icon) throws DekuExceptions {
        if (task.size() == 0) {
            throw new DekuExceptions("The description of a " + taskName + " cannot be empty.");
        }
        this.TASK = task;
        this.ICON = icon;
        this.completionStatus = false;
        this.completionIcon = "[ ]";
    }

    private String parseTask() {
        String output = getTask();
        String special = getSpecial();
        if (special.equals("")) {
            return output;
        }
        return output + " (" + getSpecial() + ": " + getTime() + ")";
    }

    private String getSpecial() {
        String output = "";
        for (int i = 0; i < TASK.size(); i++) {
            String current = TASK.get(i);
            if (current.charAt(0) == '/') {
                return current.substring(1);
            }
        }
        return "";
    }

    private String getTime() {
        String output = "";
        boolean time = false;
        for (int i = 0; i < TASK.size(); i++) {
            String current = TASK.get(i);
            if (current.charAt(0) == '/') {
                time = true;
                continue;
            }
            if (time) {
                output += current + " ";
            }
        }
        return (output.equals("")) ? output : output.substring(0, output.length()-1);
    }

    private String getTask() {
        String output = "";
        for (int i = 0; i < TASK.size(); i++) {
            String current = TASK.get(i);
            if (current.charAt(0) == '/') {
                break;
            }
            output += current + " ";
        }
        return output.substring(0, output.length()-1);
    }

    void setCompletionStatus(boolean set) {
        completionStatus = set;
        if (completionStatus) {
            completionIcon = "[X]";
        } else {
            completionIcon = "[ ]";
        }
    }

    public String parseSaveFormat() {
        String completionParse = (completionIcon.equals("[X]")) ? "1" : "0";
        return ICON + "|" + completionParse + "|" + getTask() + "|" + getTime();
    }

    @Override
    public String toString() {
        return "[" + ICON + "]" + completionIcon + " - " + parseTask();
    }
}
