import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        boolean acceptingInput = true;
        System.out.println("Hello from Duke");
        System.out.println("What can I do for you?");
        ArrayList<Task> storage = new ArrayList<>();

        while (acceptingInput) {
            Scanner inputScanner = new Scanner(System.in);
            String[] inputStringArray = inputScanner.nextLine().split(" ");

            if (inputStringArray[0].equals("bye")){
                acceptingInput = false;
                System.out.println("Bye. Hope to see you again soon!");
            } else if (inputStringArray[0].equals("list")) {
                for (int i = 0; i < storage.size(); i++) {
                    int index = i+ 1;
                    System.out.println(index + ". " + storage.get(i));
                }
            } else if (inputStringArray[0].equals("mark")) {
                if (inputStringArray.length == 1) {
                    System.out.println("Please enter an argument (number) after mark!");
                } else if (!isNumeric(inputStringArray[1])) {
                    System.out.println("Please enter a valid number after mark");
                } else {
                    int index = Integer.parseInt(inputStringArray[1]) - 1;
                    if (index < 0 || index >= storage.size()) {
                        System.out.println("Please enter between 1 to the last element of the list");
                    } else if (storage.get(index).isMarked()) {
                        System.out.println("That task is already marked!");
                    } else {
                        storage.get(index).markAsDone();
                        System.out.println("Nice! I've marked this task as done");
                        System.out.println(storage.get(index));
                    }
                }
            } else if (inputStringArray[0].equals("unmark")){
                if (inputStringArray.length == 1) {
                    System.out.println("Please enter an argument (number) after unmark!");
                } else if (!isNumeric(inputStringArray[1])) {
                    System.out.println("Please enter a valid number after unmark");
                } else {
                    int index = Integer.parseInt(inputStringArray[1]) - 1;
                    if (index < 0 || index >= storage.size()) {
                        System.out.println("Please enter between 1 to the last element of the list");
                    } else if (!storage.get(index).isMarked()) {
                        System.out.println("That task is already unmarked!");
                    } else {
                        storage.get(index).unmark();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(storage.get(index));
                    }
                }
            } else {
                Task newTask = new Task(inputStringArray[0]);
                storage.add(newTask);
                System.out.println("added: " + inputStringArray[0]);
            }
        }

    }

    public static boolean isNumeric(String string) {
        int intValue;

        if(string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }
}
