import java.util.ArrayList;

public class Ui {

    private static String start = "Hello! I'm Duke\nWhat can I do for you?";
    private static String end = "Bye. Hope to see you again soon!";
    public static String breaker = "____________________________________________________________\n";

    private boolean isActive;

    public void start() {
        this.isActive = true;
        this.msg(start);
    }

    public void end() {
        this.isActive = false;
        this.msg(end);
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void msg(String s) {
        System.out.println(breaker + s + "\n" + breaker);
    }

    public void displayList(TaskList taskL) {
        String result = "";
        ArrayList<Task> l = taskL.getTasks();
        if (l.isEmpty()) {
            msg("");
            return;
        }
        for (int i = 0; i < l.size()-1; i++) {
            result += (i+1) + ". " + l.get(i) + "\n";
        }
        result += (l.size()) + ". " + l.get(l.size()-1);
        msg(result);
    }
}
