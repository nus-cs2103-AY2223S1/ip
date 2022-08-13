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

    private static final String ADD_HEADER = "Got it. I've added this task:";
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void MarkTask(int taskIndex) {
        Task currentTask = taskList.get(taskIndex);
        currentTask.setDone();
        Ui.DisplayMessage(DONE_MESSAGE);
        Ui.IndentTaskDisplay(currentTask);
        Storage.Save(taskList);
    }

    public void UnmarkTask(int taskIndex) {
        Task currentTask = taskList.get(taskIndex);
        currentTask.removeDone();
        Ui.DisplayMessage(UNDONE_MESSAGE);
        Ui.IndentTaskDisplay(currentTask);
        Storage.Save(taskList);
    }

    public void List() {
        Ui.DisplayMessage(LIST_HEADER);
        Ui.DisplayOrderedList(taskList);
    }

    public void DeleteTask(int taskIndex) {
        Task deletedTask = taskList.get(taskIndex);
        taskList.remove(taskIndex);
        Ui.DisplayMessage(DELETE_HEADER);
        Ui.IndentTaskDisplay(deletedTask);
        Ui.DisplayTasksLeft(taskList.size());
        Storage.Save(taskList);
    }

    public void AddTask(Task t) {
        taskList.add(t);
        Storage.Save(taskList);
        Ui.DisplayMessage(ADD_HEADER);
        Ui.IndentTaskDisplay(t);
        Ui.DisplayTasksLeft(taskList.size());
    }




}
