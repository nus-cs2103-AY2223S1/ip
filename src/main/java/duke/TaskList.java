package duke;

import java.util.List;
import java.util.ArrayList;



public class TaskList {

    //To be given to UI to handle
    private static final String ENDING_MESSAGE = "That's all? Hope to see you again soon :)";
    private static final String LIST_HEADER = "Here are the tasks in your list:";
    private static final String DELETE_HEADER = "Noted. I've removed this task:";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:";

    private static final String UNDONE_MESSAGE = "OK, I've marked this task as not done yet:";

    public static final String FIND_HEADER = "Here are the matching tasks in your list:";

    private static final String ADD_HEADER = "Got it. I've added this task:";
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void markTask(int taskIndex) {
        Task currentTask = taskList.get(taskIndex);
        currentTask.setDone();
        Ui.displayMessage(DONE_MESSAGE);
        Ui.indentTaskDisplay(currentTask);
        Storage.save(taskList);
    }

    public void unmarkTask(int taskIndex) {
        Task currentTask = taskList.get(taskIndex);
        currentTask.removeDone();
        Ui.displayMessage(UNDONE_MESSAGE);
        Ui.indentTaskDisplay(currentTask);
        Storage.save(taskList);
    }

    public void list() {
        Ui.displayMessage(LIST_HEADER);
        Ui.displayOrderedList(taskList);
    }

    public void deleteTask(int taskIndex) {
        Task deletedTask = taskList.get(taskIndex);
        taskList.remove(taskIndex);
        Ui.displayMessage(DELETE_HEADER);
        Ui.indentTaskDisplay(deletedTask);
        Ui.displayTasksLeft(taskList.size());
        Storage.save(taskList);
    }

    public void addTask(Task t) {
        taskList.add(t);
        Storage.save(taskList);
        Ui.displayMessage(ADD_HEADER);
        Ui.indentTaskDisplay(t);
        Ui.displayTasksLeft(taskList.size());
    }
    public void findTask(String keyword) {
        List<Task> tasksContainingKeyword = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            if (t.getDescription().contains(keyword)) {
                tasksContainingKeyword.add(t);
            }
        }
        Ui.displayMessage(FIND_HEADER);
        Ui.displayOrderedList(tasksContainingKeyword);
    }


}
