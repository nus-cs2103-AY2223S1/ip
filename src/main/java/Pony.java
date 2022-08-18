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
                    System.out.println(sn + ". " + this.tasks.get(i).toString());
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
            System.out.println(target.toString());
            run();
        } else if (command.equals("unmark")) {
            System.out.println("Which task are you not done with?");
            int taskIndex = sc.nextInt();
            sc.nextLine();
            Task target = this.tasks.get(taskIndex - 1);
            target.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet: ");
            System.out.println(target.toString());
            run();
        } else if (command.equals("bye")) {
            System.out.println(this.exit);
        } else if (command.equals("todo")){
            System.out.println("What's the new Todo?");
            String description = sc.nextLine();
            Task newTask = new ToDo(description);
            this.tasks.add(newTask);
            System.out.println("Got it. I've added this task: " + newTask.toString());
            System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
            run();
        } else if (command.equals("deadline")){
            System.out.println("What's the new task?");
            String description = sc.nextLine();
            System.out.println("When is it due?");
            String time = sc.nextLine();
            Task newTask = new Deadline(description, time);
            this.tasks.add(newTask);
            System.out.println("Got it. I've added this task: " + newTask.toString());
            System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
            run();
        } else if (command.equals("event")){
            System.out.println("What's the new event?");
            String description = sc.nextLine();
            System.out.println("When is it?");
            String time = sc.nextLine();
            Task newTask = new Event(description, time);
            this.tasks.add(newTask);
            System.out.println("Got it. I've added this task: " + newTask.toString());
            System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
            run();
        } else {
            System.out.println("error");
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
