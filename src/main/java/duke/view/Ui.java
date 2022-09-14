package duke.view;

import duke.DukeException;
import duke.backend.TaskList;

public class Ui {
    private static final String GREET_MESSAGE = "Hello Imposter! Welcome onboard the spaceship. I'm CrewMate! \nWhat can I help you with?";
    private static final String BYE_MESSAGE = "Alright, you can leave the spaceship now. Cya!";
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this mission: \n";
    private static final String DELETE_TASK_MESSAGE = "Noted. I've cancelled this mission: \n";
    private static final String LIST_MESSAGE = "Here are the missions in your list: \n";
    private static final String MARK_TEXT_MESSAGE = "Great Execution! I've marked this mission as done: \n";
    private static final String UNMARK_TEXT_MESSAGE = "OK, I've marked this mission as incomplete: \n";
    private static final String MATCH_TEXT_MESSAGE = "Here're the matching missions in your list: \n";
    private static final String REMINDER_MESSAGE = "Here're your missions for the upcoming week: \n";

    private String getTaskListSizeMessage(TaskList tasklist) {
        return "\nNow you have " + tasklist.getSize() + " missions in the list.";
    }

    public String showAddedTask(TaskList tasklist) {
        String content = tasklist.getLastTask();
        content = ADD_TASK_MESSAGE + content;
        content += getTaskListSizeMessage(tasklist);
        return content;
    }

    public String showDeletedTask(String content, TaskList tasklist) {
        content = DELETE_TASK_MESSAGE + content;
        content += getTaskListSizeMessage(tasklist);
        return content;
    }

    public String showGreeting() {
        return GREET_MESSAGE;
    }

    public String showBye() {
        return BYE_MESSAGE;
    }

    public String showMark(TaskList tasklist, String taskNumber) throws DukeException{
        String markInText = MARK_TEXT_MESSAGE;
        markInText += tasklist.markSpecificTask(taskNumber);
        return markInText;
    }

    public String showUnmark(TaskList tasklist, String taskNumber) throws DukeException {
        String unmarkInText = UNMARK_TEXT_MESSAGE;
        unmarkInText += tasklist.unmarkSpecificTask(taskNumber);
        return unmarkInText;
    }

    public String showList(TaskList tasklist) {
        String listInString = LIST_MESSAGE;
        listInString += tasklist.listOfTaskForDisplay();
        return listInString;
    }

    public String showReminder(TaskList tasklist) {
        String reminderInString = REMINDER_MESSAGE;
        reminderInString += tasklist.listOfTasksForReminder();
        return reminderInString;
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
