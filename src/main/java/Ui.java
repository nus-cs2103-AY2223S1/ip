import java.util.ArrayList;

public class Ui {
    protected String line = "____________________________________________________________";

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
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Task task : taskList) {
            String taskName = task.toString();
            String statusIcon = task.getStatusIcon();
            System.out.println(count + ".[" + statusIcon + "] " + taskName);
            count++;
        }

        System.out.println(line);
    }

    public void marked(Task task){
        String taskName = task.toString();
        String statusIcon = task.getStatusIcon();

        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + statusIcon + "] " + taskName);
        System.out.println(line);
    }

    public void exit(){
        String exitLine = "Bye. Hope to see you again soon!";
        System.out.println(line + "\n" + exitLine + "\n" + line);
    }

}
