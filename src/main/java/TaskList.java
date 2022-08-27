public class TaskList {
    Task[] taskList = new Task[100];
    // The first index that is empty
    private int firstEmptyIndex = 0;

    public void addTask(String input, Task.TaskType taskType) {
        Task task = taskType == Task.TaskType.ToDo
                ? new Todo(input)
                : taskType == Task.TaskType.Event
                ? new Event(input)
                : new Deadline(input);

        taskList[firstEmptyIndex] = task;

        String msg = "Got it. I've added this task:\n" + "  "
                + taskList[firstEmptyIndex].toString()
                + "\n" + "Now you have " + (firstEmptyIndex + 1) + " tasks in the list";
        System.out.println(msg);
        ++firstEmptyIndex;
    }

    public void markTaskAsDone(int taskNum) {
        if (taskNum >= firstEmptyIndex) {
            System.out.println("There is no task with index " + (taskNum + 1));
            return;
        }
        taskList[taskNum].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList[taskNum].toString());
    }

    public void markTaskAsUnDone(int taskNum) {
        if (taskNum >= firstEmptyIndex) {
            System.out.println("There is no task with index " + (taskNum + 1));
            return;
        }
        taskList[taskNum].markAsUnDone();
        System.out.println("I've marked this task as not done:");
        System.out.println(taskList[taskNum].toString());
    }

    public void printTasks() {
        if (firstEmptyIndex == 0) {
            System.out.println("There's nothing in the list.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < firstEmptyIndex; i++) {
            System.out.println((i + 1) + ". " + taskList[i].toString());
        }
    }
}
