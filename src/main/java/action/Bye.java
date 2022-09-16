package action;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import reminder.Reminder;

import task.Task;
import task.TaskList;
import ui.UI;


/**
 * Bye is a class that writes all the tasks from the TaskList to duke.txt.
 */
public class Bye {

    protected static UI ui = new UI();

    public static String bye(String[] str, TaskList taskList, File file) {

        assert str.length == 1 : "must be a single word";

        ArrayList<Task> listOfActions = taskList.getTaskList();
        Reminder reminder = new Reminder();
        try {
            FileWriter writer = new FileWriter(file.getPath());
            for (Task t : listOfActions) {
                writer.write(t.toString() + System.lineSeparator());
                reminder.addIfReminder(t);
            }
            writer.close();
        } catch (IOException e) {
            return e.getMessage();
        }
        reminder.writeAllReminder();
        return ui.goodByeMessage();
    }

    @Override
    public String toString() {
        return ui.goodByeMessage();
    }

}
