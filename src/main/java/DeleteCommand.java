public class DeleteCommand extends Command {
    private TasksList tasksList;
    private Storage storage;
    private String[] words;

    public DeleteCommand(TasksList tasksList, Storage storage, String[] words) {
        this.tasksList = tasksList;
        this.storage = storage;
        this.words = words;
    }

    @Override
    public void execute() throws DukeException {
        tasksList.deleteTask(words, storage);
    }

    public static boolean isCommand(String s) {
        return s.equals("delete");
    }
}
