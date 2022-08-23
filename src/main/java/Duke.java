import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {

    public static void greet() {
        System.out.println("Greetings Human! I am BetaGo, your personal robot assistant!\nHow may I assist you today?\n");
    }
    public static void readCommands() {

        Scanner sc = new Scanner(System.in);
        String str= sc.nextLine();
        String[] inputs = str.split(" ", 2);
        TaskList storage = new TaskList();

        while (!str.equalsIgnoreCase("bye")) {
            if (str.equalsIgnoreCase("list")) {
                try {
                    storage.listItems();
                } catch (EmptyListException e) {
                    System.out.println("You currently have no tasks in your list!\n");
                }
            } else if (inputs[0].equalsIgnoreCase("mark") || inputs[0].equalsIgnoreCase("unmark")) {
                try {
                    storage.markUnmarkItems(str);
                } catch (InvalidCommandException e) {
                    System.out.println("Please indicate a valid task number!\n");
                }
            } else if (inputs[0].equalsIgnoreCase("todo")) {
                try {
                    storage.addTodo(str);
                } catch (InvalidCommandException e) {
                    System.out.println("Please indicate a valid description for your Todo task!\n");
                }
            } else if (inputs[0].equalsIgnoreCase("deadline")) {
                try {
                    storage.addDeadline(str);
                } catch (InvalidCommandException e) {
                    System.out.println("Please indicate a valid description and due date for your Deadline task!\n");
                }
            } else if (inputs[0].equalsIgnoreCase("event")) {
                try {
                    storage.addEvent(str);
                } catch (InvalidCommandException e) {
                    System.out.println("Please indicate a valid description and location for your Event task!\n");
                }
            }  else if (inputs[0].equalsIgnoreCase("delete")) {
                try {
                    storage.deleteItems(str);
                } catch (InvalidCommandException e) {
                    System.out.println("Please indicate a valid task number!\n");
                }
            }  else {
                System.out.println("Apologies Human. I do not understand that command.\n");
            }
            str = sc.nextLine();
            inputs = str.split(" ", 2);
        }
    }

    public static void goodbye() {
        System.out.println("Goodbye Human. Till next time.\n");
    }

    public static void main(String[] args) {
        Duke.greet();
        Duke.readCommands();
        Duke.goodbye();
    }
}
