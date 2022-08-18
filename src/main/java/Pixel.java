import java.util.ArrayList;
import java.util.Scanner;

public class Pixel {

    private static int count = 0;
    private final Scanner myScanner = new Scanner(System.in);  // Create a Scanner object
    private final ArrayList<Task> inputTasks = new ArrayList<>(100);

    // char[] numbers = {"s", "s"};

//    private int getListIndex(String userInput) {
//        char[] inputCharArray = userInput.toCharArray();
//        for (char character: inputCharArray) {
//            if (numArray.contains()) {
//                return Character.getNumericValue(character);
//            }
//        };
//        return -1;
//    }

    private void run() {
        String userInput = myScanner.nextLine();  // Read user input
        int indexOfSlash = userInput.indexOf("/");

        try {
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");

            } else if (userInput.startsWith("todo ")) {
                String due = userInput.substring(indexOfSlash == -1 ? 5 : (indexOfSlash + 1));
                Task newToDo = new ToDo(userInput.substring(5, indexOfSlash == -1 ? userInput.length() : indexOfSlash), due); // Stores user input
                inputTasks.add(count, newToDo);
                count += 1;
                System.out.println("Got it. I've added this task:");
                System.out.println(newToDo);
                System.out.println("Now you have " + count + " tasks in the list.");
                run();

            } else if (userInput.startsWith("deadline ")) {
                String due = userInput.substring(indexOfSlash == -1 ? 9 : (indexOfSlash + 1));
                Task newDeadline = new Deadline(userInput.substring(9, indexOfSlash == -1 ? userInput.length() : indexOfSlash), due); // Stores user input
                inputTasks.add(count, newDeadline);
                count += 1;
                System.out.println("Got it. I've added this task:");
                System.out.println(newDeadline);
                System.out.println("Now you have " + count + " tasks in the list.");
                run();

            } else if (userInput.startsWith("event ")) {
                String due = userInput.substring(indexOfSlash == -1 ? 6 : (indexOfSlash + 1));
                Task newEvent = new Event(userInput.substring(6, indexOfSlash == -1 ? userInput.length() : indexOfSlash), due); // Stores user input
                inputTasks.add(count, newEvent);
                count += 1;
                System.out.println("Got it. I've added this task:");
                System.out.println(newEvent);
                System.out.println("Now you have " + count + " tasks in the list.");
                run();

            } else if (userInput.startsWith("mark ")) {
                // truncate the front part
                String temp = userInput.substring(5);
                // System.out.println(temp);
                int indexToChange = Character.getNumericValue(temp.charAt(0));
                // System.out.println(indexToChange);
                if ((indexToChange > 0) || (indexToChange < 100)) {
                    inputTasks.get(indexToChange - 1).markAsDone();
                }
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println(inputTasks.get(indexToChange - 1));
                run();

            } else if (userInput.startsWith("unmark ")) {
                // truncate the front part
                String temp = userInput.substring(7);
                // System.out.println(temp);
                int indexToChange = Character.getNumericValue(temp.charAt(0));
                // System.out.println(indexToChange);
                if ((indexToChange > 0) || (indexToChange < 100)) {
                    inputTasks.get(indexToChange - 1).markAsNotDone();
                }
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(inputTasks.get(indexToChange - 1));
                run();

            } else if (userInput.equals("list")) {
                // System.out.println(inputMemory.length);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    Task currentTask = inputTasks.get(i);
                    System.out.println((i + 1) + ". " + currentTask);
                }
                run();

            } else if (userInput.startsWith("delete ")) {
                Task tempRecord;
                // truncate the front part
                String temp = userInput.substring(7);
                // System.out.println(temp);
                int indexToDelete = Character.getNumericValue(temp.charAt(0));
                // System.out.println(indexToChange);
                if ((indexToDelete > 0) || (indexToDelete < 100)) {
                    tempRecord = inputTasks.get(indexToDelete - 1);
                    inputTasks.remove(indexToDelete - 1);
                    System.out.println("rgdgdfgfdg");
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tempRecord);
                    for (int i = (indexToDelete - 1); i < inputTasks.size(); i++) {
                        // move everything up by 1
                        if (i == inputTasks.size() - 1) {
                            inputTasks.remove(i);
                        } else {
                            inputTasks.set(i, inputTasks.get(i + 1));
                        }
                    }
                    count -= 1;
                    System.out.println("Now you have " + count + " tasks in the list.");
                }
                run();

            } else {
                inputTasks.add(count, new Task(userInput)); // Stores user input
                System.out.println(userInput);  // Output user input
                count += 1;
                run();
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
            System.out.println("caught IOOBE");

        } catch (StackOverflowError e) {
            System.out.println(e);
            System.out.println("caught Stack Overflow Error");

        } finally {
            // clean up
            System.out.println("cleaning up");
            run();
        }

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        // System.out.println("Hello from\n");
        System.out.println("Hello! I'm Pixel \r\nWhat can I do for you?");
        Pixel test = new Pixel();
        test.run();
    }
}

