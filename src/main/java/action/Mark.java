package action;

import duke.DukeException;
import task.TaskList;

public class Mark {

    public static String mark(String[] str, TaskList taskList) {
        try {
            taskList.getTaskList().get(Integer.parseInt(str[1]) - 1).mark();
            return "----------------------\n" + "Congrats on completing :)\n" +
                    taskList.getTaskList().get(Integer.parseInt(str[1]) - 1) + "\n----------------------\n";
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("");
        }
    }
}
