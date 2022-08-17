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

    public void addTask(String task) {
        String taskAdded = "added: " + task;
        System.out.println(line + "\n" + taskAdded + "\n" + line);
    }

    public void list(String[] taskList) {
        System.out.println(line);
        int num = 0;
        while(taskList[num] != null){
            num++;
            System.out.println(num + ". " + taskList[num - 1]);
        }

        System.out.println(line);
    }

    public void exit(){
        String exitLine = "Bye. Hope to see you again soon!";
        System.out.println(line + "\n" + exitLine + "\n" + line);
    }
}
