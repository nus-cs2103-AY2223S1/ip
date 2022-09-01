public class TaskCommand extends Command {

    private String taskType;
    private String description;
    private Task task;

    public TaskCommand(String taskType, String description) {
        if (taskType == "todo") {
            this.taskType = taskType;
            this.description = description;
            task = new Todo(description);
        } else if (taskType == "deadline") {
            this.taskType = taskType;
            this.description = description;
            task = new Deadline(description);
        } else if (taskType == "event") {
            this.taskType = taskType;
            this.description = description;
            task = new Event(description);
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (description.equals("") || description.split(" ").length == 0) {
            ui.showError("You've given me an invalid task description!");
        } else {
            try {
                tasks.add(task);
                storage.add(taskType, description);
                String taskS = "tasks";
                if (tasks.listSize() == 1) {
                    taskS = "task";
                }
                ui.showMessage("I've added this task to the list:\n  " + task.getTask() +
                        "\nYou have a total of " + tasks.listSize() + " " + taskS + " in the list.");
            } catch (NullPointerException e) {
                ui.showError("You've given me an invalid task description!");
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
