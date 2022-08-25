import java.time.LocalDate;

public class AddCommand extends Command {
    private String type;
    private String description;
    
    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description; 
    }

    public void execute(TaskList tasks) throws DukeException {
        switch(type) {
            case "todo":
                Task tTask = new ToDoTask(description.split(" ")[0]);
                tasks.addTask(tTask);
                break;
            case "deadline":
                String by = description.split(" /by ")[1];
                LocalDate date = LocalDate.parse(by);
                String info = description.split(" /by ")[0];
                Task dTask = new DeadlineTask(info, date);
                tasks.addTask(dTask);
                break;
            case "event":
                String at = description.split(" /at ")[1];
                String about = description.split(" /at ")[0];
                Task eTask = new EventTask(about, at);
                tasks.addTask(eTask);
                break;
        }
    }
}
