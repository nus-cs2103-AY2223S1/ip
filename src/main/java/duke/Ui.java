package duke;

public class Ui {
    private static final String GREET_MESSAGE = "Hello! I'm Duke \nWhat can I do for you?";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task: \n";
    private static final String DELETE_TASK_MESSAGE = "Noted. I've removed this task: \n";
    private static final String LIST_MESSAGE = "Here are the tasks in your list: \n";
    private static final String MARK_TEXT_MESSAGE = "Nice! I've marked this task as done: \n";
    private static final String UNMARK_TEXT_MESSAGE = "OK, I've marked this task as not done yet: \n";
    private static final String MATCH_TEXT_MESSAGE = "Here are the matching tasks in your list: \n";

    public String showAddedTask(TaskList tasklist) {
        String content = tasklist.getLastTask();
        content = ADD_TASK_MESSAGE + content;
        content += "\nNow you have " + tasklist.getSize() + " tasks in the list.";
        return content;
    }

    public String showDeletedTask(String content, TaskList tasklist) {
        content = DELETE_TASK_MESSAGE + content;
        content += "\nNow you have " + tasklist.getSize() + " tasks in the list.";
        return content;
    }

    public String showGreeting() {
        return GREET_MESSAGE;
    }

    public String showBye() {
        return BYE_MESSAGE;
    }

    public String showMark(TaskList tasklist, String taskNumber) {
        String markInText = MARK_TEXT_MESSAGE;
        markInText += tasklist.markSpecificTask(taskNumber);
        return markInText;
    }

    public String showUnmark(TaskList tasklist, String taskNumber) {
        String unmarkInText = UNMARK_TEXT_MESSAGE;
        unmarkInText += tasklist.unmarkSpecificTask(taskNumber);
        return unmarkInText;
    }

    public String showList(TaskList tasklist) {
        String listInString = LIST_MESSAGE;
        listInString += tasklist.listOfTaskForDisplay();
        return listInString;
    }

    public String showMatch(TaskList tasklist, String substring) {
        String matchInString = MATCH_TEXT_MESSAGE;
        matchInString += tasklist.listOfMatchedTasks(substring);
        return matchInString;
    }

    public String showError(String errorMessage) {
        return errorMessage;
    }

}
