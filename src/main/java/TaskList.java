public class TaskList {
    Task[] taskList = new Task[100];
    // The first index that is empty
    private int currLastIndex = 0;

    public void addTask(String input, Task.TaskType taskType) {
        Task task = taskType == Task.TaskType.ToDo
                ? new Todo(input)
                : taskType == Task.TaskType.Event
                ? new Event(input)
                : new Deadline(input);

        taskList[currLastIndex] = task;

        String msg = "Got it. I've added this task:\n" + "  "
                + taskList[currLastIndex].toString()
                + "\n" + "Now you have " + (currLastIndex + 1) + " tasks in the list";
        System.out.println(msg);
        ++currLastIndex;
    }

    public void markTaskAsDone(int taskNum) {
        taskList[taskNum].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList[taskNum].toString());
    }

    public void markTaskAsUnDone(int taskNum) {
        taskList[taskNum].markAsUnDone();
        System.out.println("Nice! I've marked this task as not done:");
        System.out.println(taskList[taskNum].toString());
    }

    public void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < currLastIndex; i++) {
            System.out.println((i + 1) + ". " + taskList[i].toString());
        }
    }
}
