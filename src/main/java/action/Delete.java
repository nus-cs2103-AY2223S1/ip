package action;

import task.TaskList;

import duke.DukeException;

public class Delete {

    public static String delete(String[] str, TaskList taskList) {
        try {
            int pos = Integer.parseInt(str[1]) - 1;
            return taskList.delete(pos);
        } catch (
                IndexOutOfBoundsException e) {
            throw new DukeException("");
        }
    }

}
