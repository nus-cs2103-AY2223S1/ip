public class MarkCommand extends Command {

    public static final String COMMAND_NAME = "mark";
    
    private final int markIndex;
    
    MarkCommand(int markIndex) {
        this.markIndex = markIndex;
    }

    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (tasks.getTask(this.markIndex).getIsDone()) {
                ui.showReply(String.format("Sorry, but it seems you have marked this task as done:\n  %s", tasks.getTask(markIndex)));
            } else {
                tasks.getTask(this.markIndex).setDone(true);
                ui.showReply(String.format("Noice! I've marked this task as done:\n  %s", tasks.getTask(markIndex)));
            }
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I do not have a task with that number in my list.", e);
        }
    }

    @Override
    public boolean isTerminator() {
        return false;
    }
}
