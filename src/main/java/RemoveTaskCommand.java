public class RemoveTaskCommand implements Command {

    private TaskList tasks;
    private int index;

    public RemoveTaskCommand(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    public RemoveTaskCommand(TaskList tasks, String input) throws DaveException {
        try {
            if (input.equals("")) {
                throw new DaveException("( ; ω ; ) Oh nyo!!! Please tell me which task to remove!");
            }
            this.index = Integer.parseInt(input);
            this.tasks = tasks;
        } catch (NumberFormatException e) {
            throw new DaveException(String.format("( ; ω ; ) Oh nyo!!! Please tell me which task to mark between 1 and %d!", tasks.size()));
        }
    }

    @Override
    public String execute() throws DaveException {
        try{
            return this.tasks.remove(this.index);
        } catch (IndexOutOfBoundsException e) {
            throw new DaveException(String.format("( ; ω ; ) Oh nyo!!! Please tell me which task to mark between 1 and %d!", tasks.size()));
        }
    }

}
