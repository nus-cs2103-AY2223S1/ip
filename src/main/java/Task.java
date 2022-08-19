import java.util.ArrayList;

public abstract class Task {
    private boolean finished = false;
    private final String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void markAsFinished() {
        this.finished = true;
    }

    public void markAsUnfinished() {
        this.finished = false;
    }

    public String status() {
        String completed = this.finished ? "[X] " : "[ ] ";
        return completed + this.taskName;
    }

    public static class ToDo extends Task {
        public ToDo(String task) {
            super(task);
        }

        @Override
        public String status() {
            return "[T]" + super.status();
        }
    }

    public static void addTask(Task newTask, ArrayList<Task> taskList) {
        taskList.add(newTask);
        System.out.println("I gotchu fam. Your task has been added:");
        System.out.println("  " + newTask.status());
        System.out.println("You have " + taskList.size() + " task(s). Stop procrastinating bro.");
    }

    public static void deleteTask(int taskNumber, ArrayList<Task> taskList) throws IllegalArgumentException {
        int length = taskList.size();

        if (taskNumber <= 0 || taskNumber > length) {
            throw new IllegalArgumentException("Wrong task number lah.");
        }

        Task removedTask = taskList.remove(taskNumber - 1);
        System.out.println("I hope you're not deleting this just to avoid work:");
        System.out.println("  " + removedTask.status());
        System.out.println("You have " + (length - 1) + " task(s). Stop procrastinating bro.");
    }

    public static void mark(int taskNumber, ArrayList<Task> taskList) throws IllegalArgumentException {
        int length = taskList.size();

        if (taskNumber <= 0 || taskNumber > length) {
            throw new IllegalArgumentException("Wrong task number lah.");
        }

        Task selectedTask = taskList.get(taskNumber - 1);
        selectedTask.markAsFinished();

        System.out.println("Nice one lah, finally doing your work.");
        System.out.println(selectedTask.status());
    }

    public static void unmark(int taskNumber, ArrayList<Task> taskList) throws IllegalArgumentException {
        int length = taskList.size();

        if (taskNumber <= 0 || taskNumber > length) {
            throw new IllegalArgumentException("Wrong task number lah.");
        }

        Task selectedTask = taskList.get(taskNumber - 1);
        selectedTask.markAsUnfinished();

        System.out.println("What happened bro.");
        System.out.println(selectedTask.status());
    }
}
