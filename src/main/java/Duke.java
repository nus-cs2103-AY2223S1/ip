import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static private final String exitCommand = "bye";
    static private final String listCommand = "list";
    static private final String markCommand = "mark";
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
                // Creating a file object.
            }
            System.out.println("Welcome to Aladdin Services");
        } catch (Exception e) {
            System.out.println("Welcome to Aladdin Services");
        }

        Scanner scannerObj = new Scanner(System.in); // Create a Scanner object
        String userInput = "";

        List<Task> tasks = new ArrayList<>();
        Boolean flag = true;

        while (flag) {
            System.out.println("Enter Command: ");
            userInput = scannerObj.nextLine(); // Read user input

            String[] commandArgs = userInput.split(" ");
            switch (commandArgs[0]) {
                case exitCommand:
                    flag = false;
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
                    System.out.println("Here are the tasks in your list\n");
                    for (int idx = 0; idx < tasks.size(); idx++) {
                        System.out.println(idx + 1 + ": " + tasks.get(idx));
                    }
                    break;

                default:
                    tasks.add(new Task(userInput));
                    System.out.println("\n___________________________ \n");
                    System.out.println("added: " + userInput);
                    System.out.println("___________________________ \n");
                    break;
            }
        }

        System.out.println(exitMessage);
        scannerObj.close();
    }
}
