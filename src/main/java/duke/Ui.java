package duke;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*
Deals with interactions with the user
*/
public class Ui {

    String line = " _______________________________________ \n";

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
    }



}


