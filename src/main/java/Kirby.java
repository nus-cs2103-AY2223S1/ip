import java.util.Scanner;
import java.util.ArrayList;
public class Kirby {
    public static int taskCount = 0;
    public static void printTaskCount() {
        taskCount += 1;
        if (taskCount > 1) {
            System.out.println("Now you have " + taskCount + " tasks in the bag!");
        } else {
            System.out.println("Now you have " + taskCount + " task in the bag!");
        }
    }
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
                    System.out.println(i + 1 + ": " + currTask.toString());
                }
            }

            //mark
            else if (inputString.split(" ")[0].equals("mark")) {
                int taskIndex = Integer.parseInt(inputString.split(" ")[1]);
                Task currTask = Tasks.get(taskIndex - 1);
                currTask.setCompleted();
                System.out.println("Awesome :D I've marked " + currTask.toString() + " completed!");
            }

            // unmark
            else if (inputString.split(" ")[0].equals("unmark")) {
                int taskIndex = Integer.parseInt(inputString.split(" ")[1]);
                Task currTask = Tasks.get(taskIndex - 1);
                currTask.setIncomplete();
                System.out.println("Okay, I've marked " + currTask.toString() + " pending!");
            }

            // add task
            else {
                // todo
                if (inputString.split(" ")[0].equals("todo")) {
                    // rest of the string
                    String taskName = inputString.substring(inputString.indexOf(' ') + 1);
                    Todo todo = new Todo(taskName);
                    Tasks.add(todo);
                    System.out.println("Added into your bag of fabulous tasks: " + todo.toString());
                    printTaskCount();
                }

                // deadline
                if (inputString.split(" ")[0].equals("deadline")) {
                    String taskName = inputString.substring(inputString.indexOf("deadline") + 9, inputString.indexOf("/by"));
                    String by = inputString.substring(inputString.indexOf("/by") + 4);
                    Deadline deadline = new Deadline(taskName, by);
                    Tasks.add(deadline);
                    System.out.println("Added into your bag of fabulous tasks: " + deadline.toString());
                    printTaskCount();
                }

                // event
                if (inputString.split(" ")[0].equals("event")) {
                    String taskName = inputString.substring(inputString.indexOf("event") + 6, inputString.indexOf("/at"));
                    String at = inputString.substring(inputString.indexOf("/at") + 4);
                    Event event = new Event(taskName, at);
                    Tasks.add(event);
                    System.out.println("Added into your bag of fabulous tasks: " + event.toString());
                    printTaskCount();
                }

            }
        }
    }
}