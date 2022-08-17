import java.util.ArrayList;

public class Ui {
    String line = "____________________________________________________________";

    public void greeting() {
        String greetings = "\nHello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(line + greetings + line);
    }

    public void echo(String text) {
        System.out.println(line + "\n" + text + "\n" + line);
    }

    public void addTask(Task task) {
        String taskAdded = "added: " + task.toString();
        System.out.println(line + "\n" + taskAdded + "\n" + line);
    }

    public void list(ArrayList<Task> taskList) {
        System.out.println(line);
        int count = 1;
        for (Task task : taskList) {
            String taskName = task.toString();
            System.out.println(count + ". " + taskName);
            count++;
        }

        System.out.println(line);
    }

    public void exit(){
        String exitLine = "Bye. Hope to see you again soon!";
        System.out.println(line + "\n" + exitLine + "\n" + line);
    }
}
