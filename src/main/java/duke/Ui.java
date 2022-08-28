package duke;

public class Ui {
    private static final String greetMessage = "Hello! I'm Duke \nWhat can I do for you?";
    private static final String byeMessage = "Bye. Hope to see you again soon!";
    private static final String addTask = "Got it. I've added this task: \n";
    private static final String deleteTask = "Noted. I've removed this task: \n";
    private static final String listMessage = "Here are the tasks in your list: \n";
    private static final String markText = "Nice! I've marked this task as done: \n";
    private static final String unmarkText = "OK, I've marked this task as not done yet: \n";
    private static final String matchTest = "Here are the matching tasks in your list: \n";
    private void wrapText(String content) {
        System.out.println("-".repeat(57));
        System.out.println(content);
        System.out.println("-".repeat(57));
    }

    public void showAddedTask(TaskList tasklist) {
        String content = tasklist.getLastTask();
        content = addTask + content;
        content += "\nNow you have " + tasklist.getSize() + " tasks in the list.";
        wrapText(content);
    }

    public void showDeletedTask(String content, TaskList tasklist) {
        content = deleteTask + content;
        content += "\nNow you have " + tasklist.getSize() + " tasks in the list.";
        wrapText(content);
    }

    public void showGreeting() {
        wrapText(greetMessage);
    }

    public void showBye() {
        wrapText(byeMessage);
    }

    public void showMark(TaskList tasklist, String taskNumber) {
        String markInText = markText;
        markInText += tasklist.markSpecificTask(taskNumber);
        wrapText(markInText);
    }

    public void showUnmark(TaskList tasklist, String taskNumber) {
        String unmarkInText = unmarkText;
        unmarkInText += tasklist.unmarkSpecificTask(taskNumber);
        wrapText(unmarkInText);
    }

    public void showList(TaskList tasklist) {
        String listInString = listMessage;
        listInString += tasklist.listOfTaskForDisplay();
        wrapText(listInString);
    }

    public void showMatch(TaskList tasklist, String substring) {
        String matchInString = matchTest;
        matchInString += tasklist.listOfMatchedTasks(substring);
        wrapText(matchInString);
    }

    public void showError(String errorMessage) {
        wrapText(errorMessage);
    }

}
