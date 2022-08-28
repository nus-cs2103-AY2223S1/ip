package duke;

public class Ui {
    private static final String GREET_MESSAGE = "Hello! I'm Duke \nWhat can I do for you?";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task: \n";
    private static final String DELETE_TASK_MESSAGE = "Noted. I've removed this task: \n";
    private static final String LIST_MESSAGE = "Here are the tasks in your list: \n";
    private static final String MARK_TEXT_MESSAGE = "Nice! I've marked this task as done: \n";
    private static final String UNMARK_TEXT_MESSAGE = "OK, I've marked this task as not done yet: \n";

    private void wrapText(String content) {
        System.out.println("-".repeat(57));
        System.out.println(content);
        System.out.println("-".repeat(57));
    }

    public void showAddedTask(TaskList tasklist) {
        String content = tasklist.getLastTask();
        content = ADD_TASK_MESSAGE + content;
        content += "\nNow you have " + tasklist.getSize() + " tasks in the list.";
        wrapText(content);
    }

    public void showDeletedTask(String content, TaskList tasklist) {
        content = DELETE_TASK_MESSAGE + content;
        content += "\nNow you have " + tasklist.getSize() + " tasks in the list.";
        wrapText(content);
    }

    public void showGreeting() {
        wrapText(GREET_MESSAGE);
    }

    public void showBye() {
        wrapText(BYE_MESSAGE);
    }

    public void showMark(TaskList tasklist, String taskNumber) {
        String markInText = MARK_TEXT_MESSAGE;
        markInText += tasklist.markSpecificTask(taskNumber);
        wrapText(markInText);
    }

    public void showUnmark(TaskList tasklist, String taskNumber) {
        String unmarkInText = UNMARK_TEXT_MESSAGE;
        unmarkInText += tasklist.unmarkSpecificTask(taskNumber);
        wrapText(unmarkInText);
    }

    public void showList(TaskList tasklist) {
        String listInString = LIST_MESSAGE;
        listInString += tasklist.listOfTaskForDisplay();
        wrapText(listInString);
    }

    public void showError(String errorMessage) {
        wrapText(errorMessage);
    }

}
