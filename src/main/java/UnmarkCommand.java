public class UnmarkCommand extends Command {

    public static final String COMMAND_NAME = "unmark";

    private final int unmarkIndex;

    UnmarkCommand(int unmarkIndex) {
        this.unmarkIndex = unmarkIndex;
    }

    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (tasks.getTask(this.unmarkIndex).getIsDone()) {
                tasks.getTask(this.unmarkIndex).setDone(false);
                ui.showReply(String.format("Alright, I've marked this task as not done:\n  %s", tasks.getTask(this.unmarkIndex)));
            } else {
                ui.showReply(String.format("Sorry, but it seems you haven't marked this task as done:\n  %s", tasks.getTask(this.unmarkIndex)));
            }
            storage.save(tasks);
        }  catch (IndexOutOfBoundsException e) {
            throw new DukeException("I do not have a task with that number in my list.", e);
        }
    }

    @Override
    public boolean isTerminator() {
        return false;
    }
}
