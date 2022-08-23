package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;


    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done bob task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public abstract String saveStringFormat();

    abstract public String toString();
    public static Task loadStringToTask(String taskString) {
        //Note that | has a special meaning in regex, thus use //| (must take note of spacing as well
        String[] splitTaskString = taskString.split(" \\| ", 4);
        char type = splitTaskString[0].charAt(0);
        boolean isDone = splitTaskString[1].equals("1");
        String description = splitTaskString[2];
        String dateTime = splitTaskString.length == 4 ? splitTaskString[3] : "";

        switch (type) {
        case 'D':
            return new Deadline(description, isDone, dateTime);
        case 'E':
            return new Event(description, isDone, dateTime);
        default:
            return new ToDo(description, isDone);
        }
    }
}
