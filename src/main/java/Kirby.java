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

    public static void goodbye() {
        System.out.println("I loved talking to you ･ω･ \n" +
                "Hope to see you again!");
    }

    public static void showList(ArrayList<Task> Tasks) {
        System.out.println("Here is your bag of fabulous tasks: ");
        for (int i = 0; i < Tasks.size(); i++) {
            Task currTask = Tasks.get(i);
            System.out.println(i + 1 + ": " + currTask.toString());
        }
    }

    public static void mark(String inputString, ArrayList<Task> Tasks) {
        int taskIndex = Integer.parseInt(inputString.split(" ")[1]);
        Task currTask = Tasks.get(taskIndex - 1);
        currTask.setCompleted();
        System.out.println("Awesome :D I've marked " + currTask.toString() + " completed!");
    }

    public static void unmark(String inputString, ArrayList<Task> Tasks) {
        int taskIndex = Integer.parseInt(inputString.split(" ")[1]);
        Task currTask = Tasks.get(taskIndex - 1);
        currTask.setIncomplete();
        System.out.println("Okay, I've marked " + currTask.toString() + " pending!");
    }

    public static void toDo(String inputString, ArrayList<Task> Tasks) {
        // rest of the string
        String taskName = inputString.substring(inputString.indexOf(' ') + 1);
        Todo todo = new Todo(taskName);
        Tasks.add(todo);
        System.out.println("Added into your bag of fabulous tasks: " + todo.toString());
        printTaskCount();
    }

    public static void deadline(String inputString, ArrayList<Task> Tasks) {
        String taskName = inputString.substring(inputString.indexOf("deadline") + 9, inputString.indexOf("/by"));
        String by = inputString.substring(inputString.indexOf("/by") + 4);
        Deadline deadline = new Deadline(taskName, by);
        Tasks.add(deadline);
        System.out.println("Added into your bag of fabulous tasks: " + deadline.toString());
        printTaskCount();
    }

    public static void event(String inputString, ArrayList<Task> Tasks) {
        String taskName = inputString.substring(inputString.indexOf("event") + 6, inputString.indexOf("/at"));
        String at = inputString.substring(inputString.indexOf("/at") + 4);
        Event event = new Event(taskName, at);
        Tasks.add(event);
        System.out.println("Added into your bag of fabulous tasks: " + event.toString());
        printTaskCount();
    }

    public static void main(String[] args) {
        ArrayList<Task> Tasks = new ArrayList<>();
        System.out.println("Hai I'm Kirby (੭｡╹▿╹｡)੭ your friendly chat assistant!! \n" + "What amazing plans do you have today?");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String inputString = scanner.nextLine();

            // bye
            if (inputString.equals("bye")) {
                goodbye();
                break;
            }

            // list
            else if (inputString.equals("list")) {
                showList(Tasks);
            }

            //mark
            else if (inputString.split(" ")[0].equals("mark")) {
                mark(inputString, Tasks);
            }

            // unmark
            else if (inputString.split(" ")[0].equals("unmark")) {
                unmark(inputString, Tasks);
            }

            // add task
            else if (inputString.split(" ")[0].equals("todo")) {
                    toDo(inputString, Tasks);
            }

            // deadline
            else if (inputString.split(" ")[0].equals("deadline")) {
                deadline(inputString, Tasks);
            }

            // event
            else if (inputString.split(" ")[0].equals("event")) {
                event(inputString, Tasks);
            }
        }
    }
}