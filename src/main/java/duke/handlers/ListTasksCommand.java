package duke.handlers;

import duke.models.TaskList;

public class ListTasksCommand implements DukeCommand {

    public String run (TaskList taskList, String content) {
        if (taskList.size() == 0) {
             return "No tasks added yet!\n";
        }

        StringBuilder fullList = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            fullList.append(i + 1).append(". ").append(taskList.get(i));
        }
        return fullList.toString();
    }
}
