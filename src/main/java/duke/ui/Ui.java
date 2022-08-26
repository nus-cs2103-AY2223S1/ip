package duke.ui;

import duke.tasklist.TaskList;

public class Ui {

    private final static String WELCOME = "Hello! I'm Seaward,\n" +
            "your friendly neighbourhood chatbot.\n" +
            "Type something and I will reply!";
    private final static String BYE = "Seaward out!";
    private final static String EMPTY_TASK_MESSAGE = "You currently have no tasks. Add some!";
    private final static String MARKED_TASK_MESSAGE = "I have marked this task as done:\n";
    private final static String UNMARKED_TASK_MESSAGE = "I have marked this task as undone:\n";

    public String getWelcome() {
        return WELCOME;
    }

    public String getByeMessage() {
        return BYE;
    }

    public String getEmptyTaskMessage() {
        return EMPTY_TASK_MESSAGE;
    }

    public String getList(TaskList taskList) {
        String list = "";
        for (int i = 0; i < taskList.getNumOfTasks(); i++) {
            int index = i + 1;
            String taskDescription = taskList.readTask(i);
            if (index == taskList.getNumOfTasks()) {
                list = list + index + "." + taskDescription;
            } else {
                list = list + index + "." + taskDescription + "\n";
            }
        }
        return list;
    }

    public String getMarkedTaskMessage() {
        return MARKED_TASK_MESSAGE;
    }

    public String getUnmarkedTaskMessage() {
        return UNMARKED_TASK_MESSAGE;
    }

    public String getDeleteMessage(String description, int numOfTasks) {
        return "Noted. I have removed this task:\n" + description
                + "\n" + "Now you have "
                + numOfTasks + " task(s) in your list.";
    }

    public String getAddTaskMessage(String description, int numOfTasks) {
        return "Noted. I have added:\n" + description + "\n"
                + "Now you have "
                + numOfTasks + " task(s) in your list.";
    }
}
