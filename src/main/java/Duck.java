import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duck {
    /**
     * main function for running the Duck bot
     * Works by parsing through the user input and splitting it at first by whitespace
     * This allows the bot to read the command and go down the appropriate case
     * It also allows the program to keep track of the arguments the user gives
     * It also handles possible invalid inputs given by the user
     * **/
    public static void main(String[] args) {
        System.out.println("Hello! Got any grapes?");
        Scanner scanner = new Scanner(System.in);
        String word = "";
        ArrayList<Todo> list = new ArrayList<>();
        while (!word.equals("bye")) {
            word = scanner.nextLine();
            if (word.toUpperCase().equals("BYE")) break;
            if (word.toUpperCase().equals("LIST")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i));
                }
            }
            else {
                try {
                    String[] arr = word.split(" ", 2);
                    String command = arr[0];
                    String arguments = arr[1];
                    try {
                    switch (command.toUpperCase()) {
                        case "TODO":
                            Todo newTodo = new Todo(arguments);
                            list.add(newTodo);
                            System.out.println("Added todo " + newTodo + " Quack!");
                            break;
                        case "DEADLINE":
                            String[] deadlineArgs = arguments.split("/by");
                            Deadline newDeadline = new Deadline(deadlineArgs[0], deadlineArgs[1]);
                            list.add(newDeadline);
                            System.out.println("Added new Deadline " + newDeadline + " Quack!");
                            break;
                        case "EVENT":
                            String[] eventArgs = arguments.split("/at");
                            Event newEvent = new Event(eventArgs[0], eventArgs[1]);
                            list.add(newEvent);
                            System.out.println("Added new Event " + newEvent + " Quack!");
                            break;
                        case "MARK":
                            Todo currentUnmarkedItem = list.get(Integer.parseInt(arguments) - 1);
                            currentUnmarkedItem.completeTask();
                            System.out.println("Quack, marked! " + currentUnmarkedItem);
                            break;
                        case "UNMARK":
                            Todo currentMarkedItem = list.get(Integer.parseInt(arguments) - 1);
                            currentMarkedItem.unCompleteTask();
                            System.out.println("Quack, unmarked! " + currentMarkedItem);
                            break;
                        case "DELETE":
                            Todo t = list.remove(Integer.parseInt(arguments) - 1);
                            System.out.println(t + "\n Is gone!! Quack!");
                            break;
                        default:
                            System.out.println("Quack!?! What does that even mean!?!?!");
                            break;
                    }
                    }
                    catch (NumberFormatException n) {
                        System.out.println("Invalid Arguments! Dummy!");
                    }
                    catch (IndexOutOfBoundsException i) {
                        System.out.println("Item does not exist!! Quack!");
                    }
                } catch (ArrayIndexOutOfBoundsException a) {
                    if (word.toUpperCase().contains("TODO") ||
                            word.toUpperCase().contains("DEADLINE") ||
                            word.toUpperCase().contains("EVENT"))
                    {
                        System.out.println("Quack!!!!! " + word.toUpperCase() + " Arguments are missing!");
                    } else {
                        System.out.println("Speak properly! Quack!");
                    }
                }
            }
        }
        System.out.println("Quack!");
    }
}
