package duke;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*
Deals with interactions with the user
*/
public class Ui {

    public String showLoadingError() {
        return "Loading error: No existing tasks yet! New list created.";
    }

    public String showFileNotFoundError() {
        return "duke.Task file not found!";
    }

    public String showByeMessage() {
        return "Goodbaiiiii\n";
    }

    public String showList(TaskList list) {
        ArrayList<Task> tasks = list.getTaskList();
        if (tasks.isEmpty()) {
            return "nuuuu list empty! add an item first :-DD";
        } else {
            return makeList(tasks);
        }
    }

    private static String makeList(ArrayList<Task> ls) {
        String s = "";
        for (int i = 0; i < ls.size(); i++) {
            int index = i + 1;
            s += index + ". " + ls.get(i).toString() + "\n";
        }
        return s;
    }

    public String showSchedule(String date, ArrayList<Task> al) {
        ArrayList<Task> scheduleList = new ArrayList<>();
        for (int i = 0; i < al.size(); i++) {
            if (al.get(i).onDate(date)) {
                scheduleList.add(al.get(i));
            }
        }
        return makeList(scheduleList);
    }

    public String showFoundTasks(ArrayList<Task> ls, String keyword) {
        return " I have found these tasks that contain \"" + keyword + "\"\n" + makeList(ls);
    }

    public String showDoNotKnowMessage() {
        return "sorry I do not know what this means :((\n";
    }

    public String showTaskAddedMessage(Task task, int numberOfTasks) {
        return "okie! i've added: \n " + task +
                "\n now you have " + numberOfTasks + " task(s) in your list!";
    }

    public String showUnmarkMessage(boolean isSuccess, String taskName) {
        if (isSuccess) {
            return " owh ;< so you haven't done " + taskName + ". unmarked ~\n [ ] " + taskName + "\n";
        }
        return taskName + " not found :(\n";
    }
    public String showMarkMessage(boolean isSuccess, String taskName) {
        if (isSuccess) {
            return "okie! " + taskName + " is done ~\n [X] " + taskName + "\n";
        }
        return taskName + " not found :(\n";
    }

    public String showDeleteMessage(Task removedTask, int newTaskListSize) {
        return "okie! i've removed: \n " + removedTask +
                "\n now you have " + newTaskListSize + " task(s) in your list!";
    }

    /* String line = " _______________________________________ \n";

    public void sayHello() {
        System.out.println(line + " I'm Dukie\n" + " What can I do for you?\n" + line);
    }

    public void showLoadingError() {
        System.out.println("Loading error: No existing tasks yet! New list created.");
    }

    public void showFileNotFoundError() {
        System.out.println("duke.Task file not found!");
    }

    public void showByeMessage() {
        System.out.println(line + "Goodbaiiiii\n" + line);
    }

    public void showList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            System.out.println(line + "nuuuu list empty! add an item first :-DD\n" + line);
        } else {
            System.out.println(line + makeList(list) + line);
        }
    }

    private static String makeList(ArrayList<Task> ls) {
        String s = "";
        for (int i = 0; i < ls.size(); i++) {
            int index = i + 1;
            s += " " + index + "." + ls.get(i).toString() + "\n";
        }
        return s;
    }

    public void showMessage(String markMessage) {
        System.out.println(line + markMessage + line);
    }

    public void showSchedule(String date, ArrayList<Task> al) {
        ArrayList<Task> scheduleList = new ArrayList<>();
        for (int i = 0; i < al.size(); i++) {
            if (al.get(i).onDate(date)) {
                scheduleList.add(al.get(i));
            }
        }
        System.out.println(line + makeList(scheduleList) + line);
    }

    public void showFoundTasks(ArrayList<Task> ls, String keyword) {
        System.out.println(line + " I have found these tasks that contain \"" + keyword + "\"\n" + makeList(ls) + line);
    }

    public void showDoNotKnowMessage() {
        System.out.println(line + "sorry I do not know what this means :((\n" + line);
    } */
}