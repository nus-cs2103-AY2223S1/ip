import java.util.ArrayList;

public abstract class Task {
    boolean finished = false;
    final String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void setFinished() {
        this.finished = true;
    }

    public void setUnfinished() {
        this.finished = false;
    }

    public String showStatus() {
        String completed = this.finished ? "[X] " : "[ ] ";
        return completed + this.taskName;
    }

    public abstract String showTaskListTextDescription();

    public static class ToDo extends Task {
        public ToDo(String taskName) {
            super(taskName);
        }

        @Override
        public String showStatus() {
            return "[T]" + super.showStatus();
        }

        @Override
        public String showTaskListTextDescription() {
            String finishedStatus = this.finished ? "finished" : "unfinished";
            return "[T], " + finishedStatus + ", " + this.taskName + "\n";
        }
    }

    public static void addTask(Task newTask, ArrayList<Task> taskList) {
        taskList.add(newTask);
        System.out.println("I gotchu fam. Your task has been added:");
        System.out.println("  " + newTask.showStatus());
        System.out.println("You have " + taskList.size() + " task(s). Stop procrastinating bro.");
    }

    private static void checkTaskNumber(int taskNumber, int taskListLength) throws IllegalArgumentException {
        if (taskNumber <= 0 || taskNumber > taskListLength) {
            throw new IllegalArgumentException("Wrong task number lah.");
        }
    }

    public static void deleteTask(int taskNumber, ArrayList<Task> taskList) throws IllegalArgumentException {
        int length = taskList.size();
        checkTaskNumber(taskNumber, length);

        Task removedTask = taskList.remove(taskNumber - 1);
        System.out.println("I hope you're not deleting this just to avoid work:");
        System.out.println("  " + removedTask.showStatus());
        System.out.println("You have " + (length - 1) + " task(s). Stop procrastinating bro.");
    }

    public static void mark(int taskNumber, ArrayList<Task> taskList) throws IllegalArgumentException {
        int length = taskList.size();
        checkTaskNumber(taskNumber, length);

        Task selectedTask = taskList.get(taskNumber - 1);
        selectedTask.setFinished();

        System.out.println("Nice one lah, finally doing your work.");
        System.out.println(selectedTask.showStatus());
    }

    public static void unmark(int taskNumber, ArrayList<Task> taskList) throws IllegalArgumentException {
        int length = taskList.size();
        checkTaskNumber(taskNumber, length);

        Task selectedTask = taskList.get(taskNumber - 1);
        selectedTask.setUnfinished();

        System.out.println("What happened bro.");
        System.out.println(selectedTask.showStatus());
    }
}
