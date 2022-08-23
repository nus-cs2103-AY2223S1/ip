public class EventCommand extends Command {
    private Event event;

    public EventCommand(String description, String time) {
        super();
        this.event = new Event(description, time);
    }

    @Override
    public void execCommand(TaskList list, Save save) {
        list.addTask(this.event);
        System.out.println("Successfully added new task:\n" + this.event +
                "\nYou have " + list.getSize() + " task(s) in the list.");
        save.saveList(list.save());
    }
}
