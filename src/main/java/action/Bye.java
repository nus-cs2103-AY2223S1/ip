package action;

import reminder.Reminder;
import task.Task;
import task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Bye {

    public static String bye(String[] str, TaskList taskList, File file) {

        assert str.length == 1 : "must be a single word";

        if (str.length != 1) {
            return "Error";
        }

        ArrayList<Task> listOfActions = taskList.getTaskList();
        Reminder reminder = new Reminder();
        try {
            FileWriter writer = new FileWriter(file.getPath());
            for (Task t : listOfActions) {
                System.out.println(t);
                writer.write(t.toString() + System.lineSeparator());
                System.out.println("here?");
                reminder.addIfReminder(t);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Oops");
        }
        reminder.writeAllReminder();
        return "Goodbye hehe see u again";
    }

    @Override
    public String toString() {
        return "GoodBye and All the best";
    }

}
