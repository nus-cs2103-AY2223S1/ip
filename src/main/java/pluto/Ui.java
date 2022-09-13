package pluto;

import pluto.task.Task;


/**
 * Display appropriate messages to the user.
 */
public class Ui {
    /** Name of chat bot */
    private static final String CHATBOT = "Pluto";
    /**
     * Displays welcome message.
     */
    public static String showWelcome() {
        String introduction = String.format("Hello I am %s.\nWhat can I do for you?", CHATBOT);
        return introduction;
    }

    /**
     * Returns message for adding a task.
     * @param t Task added.
     * @return Add message.
     */
    public String addUi(Task t) {
        StringBuilder addMessage = new StringBuilder();
        addMessage.append("Got it. I've added this task:\n");
        addMessage.append("\t" + t.toString() + "\n");
        return addMessage.toString();
    }
    /**
     * Returns message for deleting a task.
     * @param t Task deleted.
     * @return Delete message.
     */
    public String deleteUi(Task t) {
        StringBuilder deleteMessage = new StringBuilder();
        deleteMessage.append("Noted. I've removed this task:\n");
        deleteMessage.append(String.format("\t%s\n", t.toString()));
        return deleteMessage.toString();
    }

    /**
     * Returns exit message.
     * @return Exit message.
     */
    public String exitUi() {
        return "See You Later!";
    }

    /**
     * Returns message for finding tasks which have a keyword.
     * @param tasks Filtered tasks.
     * @return Find message.
     */
    public String findUi(TaskList tasks) {
        if (tasks.nTasks() == 0) {
            return "No tasks found.";
        } else {
            return "Here are the matching tasks in your list:\n" + tasks;
        }
    }

    /**
     * Returns message for printing all tasks.
     * @param tasks Tasklist.
     * @return List message.
     */
    public String listUi(TaskList tasks) {
        if (tasks.nTasks() == 0) {
            return "No tasks added yet.";
        } else {
            return "Here are the tasks in your list:\n" + tasks;
        }
    }

    /**
     * Returns message for showing tasks on a particular date.
     * @param tasks Tasklist.
     * @return Show message.
     */
    public String showUi(TaskList tasks) {
        if (tasks.nTasks() == 0) {
            return "No tasks found on this date.";
        } else {
            return "Here are the tasks on this date:\n" + tasks;
        }
    }

    /**
     * Returns message for updating the status of a task.
     * @param task Task whose status is updated.
     * @param isDone Status of the task after updating.
     * @return Update status message.
     */
    public String updateStatusUi(Task task, boolean isDone) {
        StringBuilder markMessage = new StringBuilder();
        if (isDone) {
            markMessage.append("Nice! I've marked this task as done:\n");
        } else {
            markMessage.append("OK, I've marked this task as not done yet:\n");
        }
        markMessage.append("\t" + task.toString());
        return markMessage.toString();
    }

    /**
     * Returns message for total number of tasks in the task list.
     * @param tasks Tasklist.
     * @return Number of tasks message.
     */
    public String numTasks(TaskList tasks) {
        String checkTaskCount = (tasks.nTasks() == 1 ? "task" : "tasks");
        return String.format("Now you have %d %s in the list.", tasks.nTasks(), checkTaskCount);
    }
    /**
     * Returns error messages.
     * @param emessage Error message.
     */
    public String showError(String emessage) {
        return emessage;
    }
}
