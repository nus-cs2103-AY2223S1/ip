public class AddEventCommand extends Command {
    private String description;
    private String time;

    AddEventCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Event event;
        event = new Event(description, time);
        tasks.add(event);
        ui.printMessage("\tGot it. I've added this task:\n\t" +
                event.toString() +
                "\n\tNow you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks.getAllTasks());
    }
}
