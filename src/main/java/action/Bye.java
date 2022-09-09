package action;

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
        try {
            FileWriter writer = new FileWriter(file.getPath());
            for (Task t : listOfActions) {
                writer.write(t.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Oops");
        }

        return "hehe";

        return "Goodbye hehe see u again";

    }

    @Override
    public String toString() {
        return "GoodBye and All the best";
    }
}
