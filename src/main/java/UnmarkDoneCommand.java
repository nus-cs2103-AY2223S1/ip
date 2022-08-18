public class UnmarkDoneCommand implements Command {

    private Task task;

    public UnmarkDoneCommand(Task task) {
        this.task = task;
    }

    public UnmarkDoneCommand(TaskList tasks, String input) throws DaveException {
        try {
            if (input.equals("")) {
                throw new DaveException("( ; ω ; ) Oh nyo!!! Please tell me which task to unmark!");
            }
            int index = Integer.parseInt(input);
            this.task = tasks.get(index - 1);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DaveException(String.format("( ; ω ; ) Oh nyo!!! Please give me a valid task to unmark between 1 and %d!", tasks.size()));
        } catch (DaveNoTasksException de) {
            throw de;
        }
    }

    @Override
    public String execute() {
        return this.task.unmarkdone();
    }

}
