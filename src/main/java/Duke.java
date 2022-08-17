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
            }
            else if (userReply.toLowerCase().equals("list")) {
                List();
                userReply = userInput.nextLine().strip();
            }
            else if (userReply.toLowerCase().startsWith("mark")) {
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
            }
            else if(userReply.toLowerCase().startsWith("unmark")) {
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
            }
            else {
                Add(userReply);
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


    private static void Add(String userReply) {
        System.out.println("##############################################");
        System.out.println("\t\t\t" + "added: " + userReply);
        System.out.println("##############################################");
        Task currentTask = new Task(userReply);
        botArray.add(currentTask);
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

    private static void GoodBye() {
        System.out.println("##############################################");
        System.out.println("So Long, farewell!");
        System.out.println("##############################################");
    }
}
