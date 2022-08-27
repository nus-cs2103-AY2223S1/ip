public class AddDeadlineCommand extends Command { //Creating a Deadline Task and adding to the taskList
    String description;
    boolean isDone;
    String date;

    public AddDeadlineCommand(String description, boolean isDone, String date) {
        this.description = description;
        this.isDone = isDone;
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        Task deadline = new Deadline(description, isDone, date);
        taskList.addTask(deadline);
        storage.saveData(taskList);
        System.out.println(deadline.added());
    }

}