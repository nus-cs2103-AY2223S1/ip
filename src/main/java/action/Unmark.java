package action;


import duke.DukeException;

import task.TaskList;
import ui.UI;


/**
 * A class that marks a Task done.
 */
public class Unmark {

    private static UI ui = new UI();

    /**
     * UnMarks a Task done specified by user input.
     * @param str UserInput that contains which index of Task to unMark.
     * @param taskList The TaskList to unMark the Task from.
     * @return The system reply for which Task has been unmarked.
     */
    public static String unMark(String[] str, TaskList taskList) throws DukeException {
        try {
            taskList.getTaskList().get(Integer.parseInt(str[1]) - 1).unMark();
            return "----------------------\n" + "One more mission ;)\n" +
                    taskList.getTaskList().get(Integer.parseInt(str[1]) - 1) + "\n----------------------\n";
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ui.indexOutOfBounds(Integer.parseInt(str[1]) - 1));
        }
    }


}
