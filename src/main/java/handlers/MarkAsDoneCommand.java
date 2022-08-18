package handlers;

import models.Task;

import java.util.List;

public class MarkAsDoneCommand implements DukeCommand {
    public String run (List<Task> taskList, String content) {
        try {
            int taskNum = Integer.parseInt(content);
            taskList.get(taskNum - 1).markAsDone(); // since taskList is 0-indexed
            return "Good job on completing the task:\n" + taskList.get(taskNum - 1);
        } catch (NumberFormatException e) {
            return "Task index is not a number!\n";
        } catch (IndexOutOfBoundsException e) {
            return "Task index out of bounds!\n";
        }
    }
}
