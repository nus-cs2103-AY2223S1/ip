public class AddEventCommand extends Command { //Creating an Event Task and adding to the taskList
    String description;
    boolean isDone;
    String date;

    public AddEventCommand(String description, boolean isDone, String date) {
        this.description = description;
        this.isDone = isDone;
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        Task event = new Event(description, isDone, date);
        taskList.addTask(event);
        storage.saveData(taskList);
        System.out.println(event.added());
    }

}