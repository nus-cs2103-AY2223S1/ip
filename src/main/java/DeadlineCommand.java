public class DeadlineCommand extends Command {

    public DeadlineCommand(String content, TaskList tasks) {
        super(content, tasks);
    }

    @Override
    public boolean run() throws DukeException {
        String[] splitMessage = this.content.split(" /by ", 2);
        if (splitMessage.length < 2) {
            throw new DukeException("You forgot to add the deadline!");
        }
        Reply.printMessage(this.tasks.addTask(new Deadline(splitMessage[0], splitMessage[1])));
        return false;
    }
}