import java.util.Scanner;
import java.util.ArrayList;
public class Pony {

    String greet = "Hello! I'm Pony" + "\n" + "What can I do for you? ";
    String exit = "Bye. Hope to see you again soon!";
    Scanner sc = new Scanner(System.in);
    ArrayList<Task> tasks;

    public Pony() {
        this.tasks = new ArrayList<>();
    };

    public void initialise() {
        System.out.println(this.greet);
    }
    public void run() {
        String command = takeCommand();
        if (command.equals("list")) {
            if (this.tasks.size() == 0) {
                System.out.println("Nothing on the list!");
                run();
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < this.tasks.size(); i++) {
                    int sn = i + 1;
                    System.out.println(sn + ". " + this.tasks.get(i).viewTask());
                }
                run();
            }
        } else if (command.equals("mark")) {
            System.out.println("Which task are you done with?");
            int taskIndex = sc.nextInt();
            sc.nextLine();
            Task target = this.tasks.get(taskIndex - 1);
            target.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(target.viewTask());
            run();
        } else if (command.equals("unmark")) {
            System.out.println("Which task are you not done with?");
            int taskIndex = sc.nextInt();
            sc.nextLine();
            Task target = this.tasks.get(taskIndex - 1);
            target.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet: ");
            System.out.println(target.viewTask());
            run();
        } else if (command.equals("bye")) {
            System.out.println(this.exit);
        } else {
            Task newTask = new Task(command);
            this.tasks.add(newTask);
            System.out.println("added: " + newTask.description);
            run();
        }
    }

    public String takeCommand() {
        String command = this.sc.nextLine();
        return command;
    }

    public static void main(String[] args) {
        Pony myPony = new Pony();
        myPony.initialise();
        myPony.run();
    }
}
