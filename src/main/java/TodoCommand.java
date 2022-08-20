public class TodoCommand extends Command{
    private String description;
    
    public static String getFormat() {
        return "todo <String>";
    }
    
    public TodoCommand(String description) {
        this.description = description;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task todo = new Todo(this.description);
        tasks.add(todo);
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + todo);
        System.out.println("Now, you have " + tasks.size() + " tasks in the list");
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
