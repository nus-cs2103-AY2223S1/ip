import java.io.File;
import java.util.ArrayList;

abstract public class Task {
    protected String description;
    protected boolean isDone;
    private static final ArrayList<Task> tasks = new ArrayList<Task>();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public static void delete(int taskNumber, FileOperations fo) {
        if (taskNumber < 0 || taskNumber >= Task.count()) {
            System.out.println("\tInvalid Task Number!");
        } else {
            Task deletedTask = tasks.remove(taskNumber);
            fo.writeAllTasksToFile();

            System.out.println("\tNoted. I have removed the following task:");
            System.out.println("\t\t" + deletedTask);
            System.out.println(Task.getCountInWords());
        }
    }

    public static String getCountInWords() {
        return String.format("\tNow you have %d task%s in the list",
                Task.count(), Task.count() > 1 ? "s" : "");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public static void add(Task task, FileOperations fo) {
        tasks.add(task);
        System.out.println(String.format("\tGotcha. I have added this task:"));

        fo.addTaskToFile(task);
        System.out.println("\t\t" + task); // exploiting polymorphism
        System.out.println(Task.getCountInWords());
    }

    public static void listTasks() {
        for (int i = 0; i < Task.count(); i++) {
            System.out.println(String.format("\t%d. %s", i + 1, tasks.get(i)));
        }
    }

    public static int count() {
        return tasks.size();
    }

    public static Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public static void markAsDone(int taskNumber, FileOperations fo) {
        if (taskNumber < 0 || taskNumber >= Task.count()) {
            System.out.println("\tInvalid Task Number!");
        } else {
            Task.get(taskNumber).markAsDone();
            fo.writeAllTasksToFile();
            System.out.println("\tAwesome! I have marked this task as done:");
            System.out.println("\t\t" + Task.get(taskNumber));
        }
    }

    public static void markAsNotDone(int taskNumber, FileOperations fo) {

        if (taskNumber < 0 || taskNumber >= Task.count()) {
            System.out.println("\tInvalid Task Number!");
        } else {
            Task.get(taskNumber).markAsNotDone();
            fo.writeAllTasksToFile();
            System.out.println("\tAwesome! I have unmarked this task to be not completed:");
            System.out.println("\t\t" + Task.get(taskNumber));
        }
    }

    /**
     * Copies all tasks into tasks list. Useful when loading all the tasks from file.
     */
    public static void addTasks(ArrayList<Task> tasks) {
        Task.tasks.addAll(tasks);
    }

    /**
     * Takes a given encodedTask and returns the corresponding Task object.
     *
     * @param encodedTask the text-representation of a task
     * @return
     */
    public static Task decode(String encodedTask) throws DukeException {
        // Skip Empty line
        if (encodedTask.trim().equals("")) {
            return null;
        }
        encodedTask = encodedTask.trim();
        String[] encodedDetails = encodedTask.split("\\|");
        String taskType = encodedDetails[0];

        switch (taskType) {
        case Todo.ENCODED_TASK_TYPE:
            if (encodedDetails.length == 2) {
                throw new DukeException("Error reading Todo from file in Task.decode");
            }
            if (encodedDetails[2].trim().equals("")) {
                throw new DukeException("Oops! You forgot to specify a description for your Todo");
            }
            Task todo = new Todo(encodedDetails[2]);
            if (encodedDetails[1].trim().equals("1")) {
                todo.markAsDone();
            }
            return todo;

        case Deadline.ENCODED_TASK_TYPE:

            if (encodedDetails.length == 3) {
                throw new DukeException("Error reading Deadline from file in Task.decode");
            }
            String deadlineDescription = encodedDetails[2].trim();
            String deadlineDeadline = encodedDetails[3].trim();
            if (deadlineDescription.length() == 0) {
                throw new DukeException("Oops! You forgot to indicate the description " +
                        "for your deadline");
            }
            if (deadlineDeadline.length() == 0) {
                throw new DukeException("Oops! You forgot to indicate the deadline");
            }
            Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDeadline);
            if (encodedDetails[1].trim().equals("1")) {
                deadlineTask.markAsDone();
            }
            return deadlineTask;

        case Event.ENCODED_TASK_TYPE:
            if (encodedDetails.length == 3) {
                throw new DukeException("Error reading Event from file in Task.decode");
            }
            String eventDescription = encodedDetails[2].trim();
            String eventTiming = encodedDetails[3].trim();
            if (eventDescription.length() == 0) {
                throw new DukeException("Oops! You forgot to indicate the description " +
                        "for your event");
            }
            if (eventTiming.length() == 0) {
                throw new DukeException("Oops! You forgot to indicate the event");
            }
            Event eventTask = new Event(eventDescription, eventTiming);
            if (encodedDetails[1].trim().equals("1")) {
                eventTask.markAsDone();
            }
            return eventTask;

        default:
            return null;
        }

    }

    abstract public String encode();
}
