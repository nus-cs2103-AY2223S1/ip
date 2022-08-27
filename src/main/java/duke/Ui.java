package duke;

import duke.Task;
import duke.TaskList;

public class Ui {
    TaskList taskList;
    Ui(TaskList taskList) {
        this.taskList = taskList;

    }

    public String getHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  /\n"
                + "|____/ \\,_|_|\\_\\___|\n";
        return "Hello I'm\n" + logo + "What can I do for you?\n";
    }

    public String printAddedTask(String msg) {
        return "_______________________________________________________" +
                "\n" + "Nice, I have added this task to your list:\n " + msg + "\n" +
                "Great, now you have " + taskList.getSize() + " tasks in the list.\n" +
                "_______________________________________________________";
    }

    public String printRemovedTask(String msg) {
        return "_______________________________________________________" +
                "\n" + "OK, I have deleted this task from your list:\n " + msg + "\n" +
                "Great, now you have " + taskList.getSize()  + " tasks in the list.\n" +
                "_______________________________________________________";
    }

    public String printMarkDone(String msg) {
        return "_______________________________________________________" + "\n" +
                "Nice! I've marked this task as done:" +
                "\n" + msg + "\n" +
                "\n" + "_______________________________________________________";
    }

    public String printMarkUndone(String msg) {
        return "_______________________________________________________" + "\n" +
                "Wow! I've marked this task as not done yet:" +
                "\n" + msg + "\n" +
                "\n" + "_______________________________________________________";
    }
    public String goodbye() {
        return "_______________________________________________________" +
                "\n" + "Bye. Hope to see you again soon!";
    }

    public String printAllTasks() {
        String userInputs = "";
        for (int i = 0; i < taskList.getSize(); i++)
        {
            int index = i + 1;
            Task tempTask = taskList.getTask(i);
            userInputs += "\n" + index + "."
                    + tempTask.toString() + "\n";

        }
        return userInputs;
    }
}
