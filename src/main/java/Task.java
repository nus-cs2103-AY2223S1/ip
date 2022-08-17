import java.util.List;

public class Task {
    private final String TASK;
    private Boolean completionStatus;
    private String completionIcon;
    Task(List<String> task) throws DekuExceptions {
        if (task.size() == 0) {
            throw new DekuExceptions( "The description of a task cannot be empty.");
        }
        this.TASK = this.parseTask(task);
        this.completionStatus = false;
        this.completionIcon = "[ ]";
    }

    Task(List<String> task, String taskName) throws DekuExceptions {
        if (task.size() == 0) {
            throw new DekuExceptions("The description of a " + taskName + " cannot be empty.");
        }
        this.TASK = this.parseTask(task);
        this.completionStatus = false;
        this.completionIcon = "[ ]";
    }

    private String parseTask(List<String> task) {
        String output = "";
        boolean time = false;
        while (!task.isEmpty()) {
            String top = task.remove(0);
            if (top.charAt(0) == '/') {
                time = true;
                output += "(" + top.substring(1) + ": ";
            } else {
                output += top + " ";
            }
        }
        output = output.substring(0, output.length()-1);
        return time ? (output + ")") : output;
    }

    void setCompletionStatus(boolean set) {
        completionStatus = set;
        if (completionStatus) {
            completionIcon = "[X]";
        } else {
            completionIcon = "[ ]";
        }
    }

    @Override
    public String toString() {
        return completionIcon + " - " + TASK;
    }
}
