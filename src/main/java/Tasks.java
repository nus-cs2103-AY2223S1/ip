import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private String task;
    private boolean done;
    public static List<Tasks> taskList = new ArrayList<>();

    public Tasks(String task) {
        this.task = task;
        this.done = false;
    }

    @Override
    public String toString() {
        if (!done) {
            return "[ ] " + task;
        } else {
            return "[X] " + task;
        }
    }

    private void setDone() {
        this.done = true;
    }

    private void setUnDone() {
        this.done = false;
    }

    public static void printTaskList() {
        int i = 1;
        for (Tasks t : taskList) {
            System.out.println(i + "." + t.toString());
            i++;
        }
    }

    public static void mark(int number) {
        if (number > taskList.size() || number == 0) {
            System.out.println("There's no such task!");
            return;
        }
        taskList.get(number - 1).setDone();
        System.out.println("Ok! I've marked it as done.\n "
                + taskList.get(number - 1).toString());
    }

    public static void unMark(int number) {
        if (number > taskList.size() || number == 0) {
            System.out.println("There's no such task!");
            return;
        }
        taskList.get(number - 1).setUnDone();
        System.out.println("Ok! I've marked it as undone.\n "
                + taskList.get(number - 1).toString());
    }
}



