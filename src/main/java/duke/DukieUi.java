package duke;

import java.util.ArrayList;

public class DukieUi {

    String line = " _______________________________________ \n";

    public String showLoadingError() {
        return "Loading error: No existing tasks yet! New list created.";
    }

    public String showFileNotFoundError() {
        return "duke.Task file not found!";
    }

    public String showByeMessage() {
        return "Goodbaiiiii\n";
    }

    public String showList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            return "nuuuu list empty! add an item first :-DD";
        } else {
            return makeList(list);
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

    /* public String showMessage(String markMessage) {
        System.out.println(line + markMessage + line);
    } */

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

}
