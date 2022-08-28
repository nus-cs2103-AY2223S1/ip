import java.time.LocalDateTime;

public class TimeTaskCommand extends Command{
    private LocalDateTime time;
    private final String keyword;
    public TimeTaskCommand(String input, String keyword, LocalDateTime time) {
        super(input);
        this.time = time;
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //create new task here
        Task newTask;
        if (keyword.equals("event")) {
            newTask = new Event(super.getInput(), time); 
        } else {
            newTask = new Deadline(super.getInput(), time); 
        }

        String message = tasks.addTask(newTask);
        ui.add(tasks.numOfTasks(), message);
        storage.save(newTask);
    }
}
