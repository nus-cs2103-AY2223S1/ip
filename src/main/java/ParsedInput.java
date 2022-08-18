public class ParsedInput {
    private String command = "";
    private String task = "";
    private String during = "";
    private String time = "";
    private int listIndex;

    public ParsedInput(String command, String task, String during, String time) {
        this.command = command;
        this.task = task;
        this.during = during;
        this.time = time;
    }

    public ParsedInput(String command, String task) {
        this.command = command;
        this.task = task;
    }

    public ParsedInput(String command, int listIndex) {
        this.command = command;
        this.listIndex = listIndex;
    }

    public String getCommand() {
        return command;
    }

    public String getTaskName() {
        return task;
    }

    public String getDuring() {
        return during;
    }

    public String getTime() {
        return time;
    }

    public int getListIndex() {
        return listIndex;
    }
}
