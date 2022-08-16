import java.util.Scanner;
import java.util.ArrayList;
public class Kirby {
    public static void main(String[] args) {
        ArrayList<Task> Tasks = new ArrayList<>();
        System.out.println("Kirby: Hai I'm Kirby (੭｡╹▿╹｡)੭ your friendly chat assistant!! \n" +
                "What amazing plans do you have today?");

        while (true){
            Scanner scanner = new Scanner(System.in);
            String inputString = scanner.nextLine();

            // bye
            if (inputString.equals("bye")) {
                System.out.println("I loved talking to you :> \n" +
                        "Hope to see you again!");
                break;
            }

            // list
            else if (inputString.equals("list")) {
                System.out.println("Here is your bag of fabulous tasks: ");
                for (int i = 0; i < Tasks.size(); i++) {
                    Task currTask = Tasks.get(i);
                    System.out.println(i + 1 + ": " + currTask.getStatusIcon() + " " + currTask.getDescription());
                }
            }

            //mark
            else if (inputString.split(" ")[0].equals("mark")) {
                int taskIndex = Integer.parseInt(inputString.split(" ")[1]);
                Tasks.get(taskIndex - 1).setCompleted();
                System.out.println("Awesome :D I've marked " + Tasks.get(taskIndex - 1).getStatusIcon() + " " + Tasks.get(taskIndex - 1).getDescription() + " completed!");
            }

            // unmark
            else if (inputString.split(" ")[0].equals("unmark")) {
                int taskIndex = Integer.parseInt(inputString.split(" ")[1]);
                Tasks.get(taskIndex - 1).setIncomplete();
                System.out.println("Okay, I've marked " + Tasks.get(taskIndex - 1).getStatusIcon() + " " + Tasks.get(taskIndex - 1).getDescription() + " pending!");
            }

            // add task
            else {
                Task t = new Task(inputString);
                Tasks.add(t);
                System.out.println("Added into your bag of fabulous tasks: " + inputString);
            }
        }
    }
}