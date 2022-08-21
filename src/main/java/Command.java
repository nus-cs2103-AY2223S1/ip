public abstract class Command {
    private CommandType type;

    public Command(CommandType type) {
        this.type = type;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    protected static <T> int findElem(T[] arr, T elem) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }
}
