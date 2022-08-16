import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static private final String exitCommand = "bye";
    static private final String listCommand = "list";
    static private final String markCommand = "mark";
    static private final String todoCommand = "todo";
    static private final String deadlineCommand = "deadline";
    static private final String deadlineSubCommand = " /by ";
    static private final String eventCommand = "event";
    static private final String eventSubCommand = " /at ";
    static private final String unmarkCommand = "unmark";
    static private final String exitMessage = "Goodbye and have a nice day!";

    static private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        File file = new File("src/main/assets/logo.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
        }

        System.out.println("Welcome to Aladdin Services");

        Scanner scannerObj = new Scanner(System.in); // Create a Scanner object
        String userInput = "";

        List<Task> tasks = new ArrayList<>();
        Boolean flag = true;

        while (flag) {
            System.out.println("Enter Command: ");
            userInput = scannerObj.nextLine(); // Read user input

            String[] commandArgs = userInput.split(" ");
            String[] commandArgsCopy = new String[commandArgs.length - 1];
            for (int i = 1; i < commandArgs.length; i++) {
                commandArgsCopy[i - 1] = commandArgs[i];
            }

            switch (commandArgs[0]) {
                case exitCommand:
                    flag = false;
                    break;

                case todoCommand:
                    String todoText = String.join(" ", commandArgsCopy);

                    if(commandArgsCopy.length > 0){
                        Task newTodo = new Todo(todoText);
                        tasks.add(newTodo);
                        System.out.println("\n___________________________ \n");
                        System.out.println("got it. I've added this task:");
                        System.out.println(newTodo);
                        System.out.println("___________________________ \n");
                    } else {
                        System.out.println("The description of the todo cannot be empty!");
                    }
                    break;

                case deadlineCommand:
                    String deadlineText = String.join(" ", commandArgsCopy);
                    if (deadlineText.contains(deadlineSubCommand)) {
                        String[] deadlineArgs = deadlineText.split(deadlineSubCommand);
                        String deadlineTitle = deadlineArgs[0];
                        String deadline = deadlineArgs[1];

                        Task newDeadline = new Deadline(deadlineTitle, deadline);
                        tasks.add(newDeadline);
                        System.out.println("\n___________________________ \n");
                        System.out.println("got it. I've added this task:");
                        System.out.println(newDeadline);
                        System.out.println("___________________________ \n");
                    } else {
                        System.out.println("Deadlines need a /by command");
                    }
                    break;

                case eventCommand:
                    String eventText = String.join(" ", commandArgsCopy);
                    if (eventText.contains(eventSubCommand)) {
                        String[] eventArgs = eventText.split(eventSubCommand);
                        String eventTitle = eventArgs[0];
                        String eventDateTime = eventArgs[1];

                        Task newEvent = new Event(eventTitle, eventDateTime);
                        tasks.add(newEvent);
                        System.out.println("\n___________________________ \n");
                        System.out.println("got it. I've added this task:");
                        System.out.println(newEvent);
                        System.out.println("___________________________ \n");
                    } else {
                        System.out.println("Events need a /at command");
                    }
                    break;

                case markCommand:
                    if (isNumeric(commandArgs[1])) {
                        int idx = Integer.parseInt(commandArgs[1]);
                        try {
                            Task curr = tasks.get(idx - 1);
                            curr.setComplete();
                            System.out.println("I've marked this task as done:");
                            System.out.println(curr);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Sorry this task does not exist");
                        }
                    } else {
                        System.out.println("Sorry the second argument is not a number");
                    }
                    break;

                case unmarkCommand:
                    if (isNumeric(commandArgs[1])) {
                        int idx = Integer.parseInt(commandArgs[1]);
                        try {
                            Task curr = tasks.get(idx - 1);
                            curr.setIncomplete();
                            System.out.println("I've marked this task as done:");
                            System.out.println(curr);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Sorry this task does not exist");
                        }
                    } else {
                        System.out.println("Sorry the second argument is not a number");
                    }
                    break;

                case listCommand:
                    System.out.println("\n___________________________ \n");
                    System.out.println("Here are the tasks in your list\n");
                    for (int idx = 0; idx < tasks.size(); idx++) {
                        System.out.println(idx + 1 + ": " + tasks.get(idx));
                    }
                    System.out.println("\n___________________________ \n");
                    break;

                default:
                    System.out.println("Sorry I don't understand that command");
                    break;
            }
        }

        System.out.println(exitMessage);
        scannerObj.close();
    }
}
