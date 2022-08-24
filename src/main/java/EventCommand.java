public class EventCommand extends Command {

    public EventCommand(String content, TaskList tasks) {
        super(content, tasks);
    }

    @Override
    public boolean run() throws DukeException {
        String[] splitMessage = this.content.split(" /at ", 2);
        if (splitMessage.length < 2) {
            throw new DukeException("You forgot to add the time!");
        }
        Reply.printMessage(this.tasks.addTask(new Event(splitMessage[0], splitMessage[1])));
        return false;
    }
}