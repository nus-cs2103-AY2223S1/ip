public class TodoCommand extends Command{
    private String taskDetails;

    TodoCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    @Override
    void execute(Storage storage, UI ui, TaskList taskList) {
        Task task = new ToDo(taskDetails);
        taskList.add(task);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
        storage.save(taskList.list());
    }
}
