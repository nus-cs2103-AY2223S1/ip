package action;

import task.TaskList;

import duke.DukeException;

public class Delete {

    public static String delete(String[] str, TaskList taskList) {
        assert taskList.getTaskList().size() > 0 : "TaskList should not be empty";
        try {
            int pos = Integer.parseInt(str[1]) - 1;
            return taskList.delete(pos);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("");
        }
    }

}
