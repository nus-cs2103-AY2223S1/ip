package handlers;

import models.Task;

import java.util.List;

public class MarkAsUndoneCommand implements DukeCommand {
    public String run (List<Task> taskList, String content) {
        try {
            int taskNum = Integer.parseInt(content);
            taskList.get(taskNum - 1).markAsUndone();
            return "This task is not done yet:\n" + taskList.get(taskNum - 1);
        } catch (NumberFormatException e) {
            return "Task index is not a number!";
        } catch (IndexOutOfBoundsException e) {
            return "Task index out of bounds!";
        }
    }
}