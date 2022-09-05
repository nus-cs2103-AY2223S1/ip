package action;


import duke.DukeException;
import task.TaskList;

public class Unmark {

    public static String unMark(String[] str, TaskList taskList) {
        try {
            taskList.getTaskList().get(Integer.parseInt(str[1]) - 1).unMark();
            String out = "----------------------\n" + "One more mission ;)\n" +
                    taskList.getTaskList().get(Integer.parseInt(str[1]) - 1) + "\n----------------------\n";
            return out;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" ");
        }
    }


}
