package duke;

public class AddCommand extends Command {
    protected char type;
    protected String description;
    protected String detail;

    public AddCommand(char type, String description) {
        this.type = type;
        this.description = description;
    }

    public AddCommand(char type, String description, String detail) {
        this(type, description);
        this.detail = detail;
    }

    @Override
    public void run(Duke duke) {
        Task task;
        switch (type) {
            case 'T':
                task = new ToDo(description);
                break;
            case 'D':
                task = new Deadline(description, detail);
                break;
            case 'E':
                task = new Event(description, detail);
                break;
            default:
                task = new Task(""); // error
        }
        duke.addTask(task);
    }
}
