public class AddCommand extends Command {
    private TasksList tasksList;
    private Storage storage;
    private String[] words;

    public AddCommand(TasksList tasksList, Storage storage, String[] words) {
        this.tasksList = tasksList;
        this.storage = storage;
        this.words = words;
    }

    @Override
    public void execute() throws DukeException {
        tasksList.addTask(words, storage);
    }

    public static boolean isCommand(String s) {
        return s.equals("todo") || s.equals("deadline") || s.equals("event");
    }
}
