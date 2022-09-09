package action;


import duke.DukeException;
import task.TaskList;

public class Unmark {

    public static String unMark(String[] str, TaskList taskList) {
        try {
            taskList.getTaskList().get(Integer.parseInt(str[1]) - 1).unMark();
            return "----------------------\n" + "One more mission ;)\n" +
                    taskList.getTaskList().get(Integer.parseInt(str[1]) - 1) + "\n----------------------\n";
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" ");
        }
    }


}
