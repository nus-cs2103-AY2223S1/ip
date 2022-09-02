public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
//            System.out.println("enters execute AddCommand, task: " + task);
            tasks.addTask(task);
//            String printTask = "";
//            for (int i = 0; i < tasks.getNumOfTasks(); i++) {
//                printTask = printTask + tasks.getTask(i) + "\n";
//            }
//            System.out.println("added tasks (in execute), tasks: " + printTask);
            String addedTask = task.toString();
            int totalTasks = tasks.getNumOfTasks();
            storage.savesFile(tasks);
            ui.showAddedTask(addedTask, totalTasks);
        } catch (SallyException e) {
            System.out.println("Oops! File Not Found.");
        }
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
