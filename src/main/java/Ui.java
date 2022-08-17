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

    public void addTask(Task task, int amountOfTasks) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + amountOfTasks + " tasks in the list.");
        System.out.println(line);

    }

    public void list(ArrayList<Task> taskList) {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Task task : taskList) {
            String taskName = task.toString();
            System.out.println(count + "." + taskName);
            count++;
        }

        System.out.println(line);
    }

    public void marked(Task task){
        String taskName = task.toString();

        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskName);
        System.out.println(line);
    }

    public void unmarked(Task task){
        String taskName = task.toString();

        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskName);
        System.out.println(line);
    }

    public void exit(){
        String exitLine = "Bye. Hope to see you again soon!";
        System.out.println(line + "\n" + exitLine + "\n" + line);
    }

    public void commandDoesNotExist(){
        System.out.println(line);
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(line);
    }

    public void delete(Task task, int amountOfTasks) {
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + amountOfTasks + " tasks in the list.");
        System.out.println(line);
    }
    public void errorMessage(DukeException e) {
        System.out.println(line);
        System.out.println(e.toString());
        System.out.println(line);
    }
}
