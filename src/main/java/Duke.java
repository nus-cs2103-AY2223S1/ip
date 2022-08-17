import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final ArrayList<Task> botArray = new ArrayList<Task>();

    public static void main(String[] args) {
        Greet();
        Scanner userInput = new Scanner(System.in);
        String userReply = userInput.nextLine().strip();
        while(!userReply.toLowerCase().equals("bye")) {
            if (userReply.equals("")) {
                System.out.println("##############################################");
                System.out.println("Please enter some valid text");
                System.out.println("##############################################");
                userReply = userInput.nextLine().strip();
            } else if (userReply.toLowerCase().equals("list")) {
                List();
                userReply = userInput.nextLine().strip();
            } else if (userReply.toLowerCase().startsWith("mark")) {
                try {
                    int number = Integer.parseInt(userReply.split(" ")[1]);
                    Mark(number);
                    userReply = userInput.nextLine().strip();
                } catch (NumberFormatException e) {
                    System.out.println("##############################################");
                    System.out.println("Please enter a valid number");
                    System.out.println("##############################################");
                    userReply = userInput.nextLine().strip();
                }
            } else if(userReply.toLowerCase().startsWith("unmark")) {
                try {
                    int number = Integer.parseInt(userReply.split(" ")[1]);
                    UnMark(number);
                    userReply = userInput.nextLine().strip();
                } catch (NumberFormatException e) {
                    System.out.println("##############################################");
                    System.out.println("Please enter a valid number");
                    System.out.println("##############################################");
                    userReply = userInput.nextLine().strip();
                }
            } else if (userReply.toLowerCase().startsWith("todo")) {
                String task = userReply.split(" ", 2)[1];
                ToDo(task);
                userReply = userInput.nextLine().strip();
            } else if (userReply.toLowerCase().startsWith("deadline")) {
                String[] taskWithDeadline = userReply.split(" ",2)[1]
                        .split(" /by ",2);
                String task = taskWithDeadline[0];
                String by = taskWithDeadline[1];
                Deadline(task, by);
                userReply = userInput.nextLine().strip();
            } else if (userReply.toLowerCase().startsWith("event")) {
                String[] taskWithPeriod = userReply.split(" ",2)[1]
                        .split(" /at ",2);
                String task = taskWithPeriod[0];
                String period = taskWithPeriod[1];
                Event(task, period);
                userReply = userInput.nextLine().strip();
            } else {
                userReply = userInput.nextLine().strip();
            }
        }
        GoodBye();
    }

    private static void Greet() {
        System.out.println("Hello, this is Siri! It is a pleasure to meet you!");
        System.out.println("How may I assist you?");
        System.out.println("##############################################");
    }

    public static void List() {
        System.out.println("##############################################");
        if (botArray.size() == 0) {
            System.out.println("\t\t\t" + "No items are in the list");
        }
        for (int i = 0; i < botArray.size(); i++) {
            int pos = i + 1;
            String itemDisplayed = String.format("\t\t\t%d. %s",pos, botArray.get(i));
            System.out.println(itemDisplayed);
        }
        System.out.println("##############################################");
    }

    public static void Mark(int num) {
        System.out.println("##############################################");
        System.out.println("Congratulations! This task has been marked as done!");
        Task currentTask = botArray.get(num - 1);
        currentTask.setCompleted(true);
        System.out.println("\t\t\t" + currentTask);
        System.out.println("##############################################");
    }

    public static void UnMark(int num) {
        System.out.println("##############################################");
        System.out.println("Congratulations! This task has been marked as done!");
        Task currentTask = botArray.get(num - 1);
        currentTask.setCompleted(false);
        System.out.println("\t\t\t" + currentTask);
        System.out.println("##############################################");
    }

    public static void ToDo(String task) {
        System.out.println("##############################################");
        System.out.println("Nice! This task has been successfully added!");
        ToDo toDo = new ToDo(task);
        System.out.println("\t\t\t" + toDo.toString());
        botArray.add(toDo);
        String numOfTasks = String.format("You currently have %d tasks in the list",botArray.size());
        System.out.println(numOfTasks);
        System.out.println("##############################################");
    }

    public static void Deadline(String task, String by) {
        System.out.println("##############################################");
        System.out.println("Nice! This task has been successfully added!");
        Deadline deadline = new Deadline(task, by);
        System.out.println("\t\t\t" + deadline.toString());
        botArray.add(deadline);
        String numOfTasks = String.format("You currently have %d tasks in the list",botArray.size());
        System.out.println(numOfTasks);
        System.out.println("##############################################");
    }

    public static void Event(String task, String at) {
        System.out.println("##############################################");
        System.out.println("Nice! This task has been successfully added!");
        Event event = new Event(task, at);
        System.out.println("\t\t\t" + event.toString());
        botArray.add(event);
        String numOfTasks = String.format("You currently have %d tasks in the list",botArray.size());
        System.out.println(numOfTasks);
        System.out.println("##############################################");
    }

    private static void GoodBye() {
        System.out.println("##############################################");
        System.out.println("So Long, farewell!");
        System.out.println("##############################################");
    }
}
