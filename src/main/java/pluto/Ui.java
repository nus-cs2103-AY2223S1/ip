package pluto;

import pluto.task.Task;


/**
 * Display appropriate messages to the user.
 */
public class Ui {
    /** Name of chat bot */
    private static final String CHATBOT = "Pluto";
    /**
     * Returns welcome message.
     */
    public static String showWelcome() {
        String introduction = String.format("Hello I am %s.\nWhat can I do for you?\n", CHATBOT);
        introduction += "Enter 'help' to know about my powers.";
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
     * Returns message for rescheduling a task.
     * @param t Task rescheduled.
     * @return Reschedule message.
     */
    public String rescheduleUi(Task t) {
        StringBuilder rescheduleMessage = new StringBuilder();
        rescheduleMessage.append("Noted. I've rescheduled this task:\n");
        rescheduleMessage.append(String.format("\t%s\n", t.toString()));
        return rescheduleMessage.toString();
    }

    /**
     * Returns exit message.
     * @return Exit message.
     */
    public String exitUi() {
        return Ui.exitMessage();
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
     * Returns message for printing all commands.
     * @return Help message.
     */
    public String helpUi() {
        StringBuilder helpMessage = new StringBuilder();
        helpMessage.append("Add a todo task     \n\t todo <task name>\n");
        helpMessage.append("Add a event task    \n\t event <task name> /at <date>\n");
        helpMessage.append("Add a deadline task \n\t deadline <task name> /by <date>\n");
        helpMessage.append("Delete a task       \n\t delete <task number>\n");
        helpMessage.append("Find a keyword      \n\t find <keyword(s)>\n");
        helpMessage.append("List all tasks      \n\t list\n");
        helpMessage.append("Mark as done        \n\t mark <task number>\n");
        helpMessage.append("Unmark              \n\t unmark <task number>\n");
        helpMessage.append("Reschedule a task   \n\t reschedule <task number> <date>\n");
        helpMessage.append("Show day schedule   \n\t show <dd-MM-yyyy HHmm>\n");
        helpMessage.append("Exit                \n\t bye\n");
        return helpMessage.toString();
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

    /**
     * Returns exit message.
     */
    public static String exitMessage() {
        return "See You Later!";
    }
}
