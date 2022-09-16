package action;

import duke.DukeException;

import task.TaskList;
import ui.UI;

/**
 * A class that marks a Task done.
 */
public class Mark {

    private static UI ui = new UI();

    /**
     * Marks a Task done specified by user input.
     * @param str UserInput that contains which index of Task to mark done.
     * @param taskList The TaskList to mark the Task from.
     * @return The system reply for which Task has been marked.
     */
    public static String mark(String[] str, TaskList taskList) {
        try {
            taskList.getTaskList().get(Integer.parseInt(str[1]) - 1).mark();
            return "----------------------\n" + "Congrats on completing :)\n" +
                    taskList.getTaskList().get(Integer.parseInt(str[1]) - 1) + "\n----------------------\n";
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ui.indexOutOfBounds(Integer.parseInt(str[1]) - 1));
        }
    }
}
