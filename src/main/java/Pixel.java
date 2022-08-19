import javax.print.DocFlavor;
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

    private void handleNewTask(String userInput, int indexOfSlash, String type) {
        // If there's a "/by" or "/at" in the input string, then the info behind the "/by" or "/at" is the due
        // if there's no "/by" and "/at" string, then due should be empty
        String due = indexOfSlash == -1 ? "" : userInput.substring(indexOfSlash + 4);
        int indexOfEndOfDescription = indexOfSlash == -1 ? userInput.length() : indexOfSlash;
        Task newTask;
        String commandWord = "";

        if (indexOfSlash != -1) {
            if (userInput.substring(indexOfSlash + 1).startsWith("by")) {
                commandWord = "by";
            } else if (userInput.substring(indexOfSlash + 1).startsWith("at")) {
                commandWord = "at";
            } else {
                throw new IncorrectFormatException("Slash should he followed \"by\" or \"at\"! "); // programme breaks
            }
        }

        if (type.equals("T")) { // todo
            String description = userInput.substring(5, indexOfEndOfDescription);
            newTask = new ToDo(description, due, commandWord); // Stores user input

        } else if (type.equals("D")) { // deadline
            String description = userInput.substring(9, indexOfEndOfDescription);
            newTask = new Deadline(description, due, commandWord); // Stores user input

        } else if (type.equals("E")) { // event
            String description = userInput.substring(6, indexOfEndOfDescription);
            newTask = new Event(description, due, commandWord); // Stores user input

        } else { //shouldn't reach here
            throw new IncorrectFormatException("Invalid format of input!"); // programme breaks
        }

        inputTasks.add(count, newTask);
        count += 1;
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + count + " tasks in the list.");
        // run();
    }

    private void run() {
        String userInput = myScanner.nextLine();  // Read user input
        int indexOfSlash = userInput.indexOf("/"); // returns -1 if such a string doesn't exist

        try {
            if (userInput.trim().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");

            } else if (userInput.trim().startsWith("todo ")) {
                handleNewTask(userInput, indexOfSlash, "T");

            } else if (userInput.trim().startsWith("deadline ")) {
                handleNewTask(userInput, indexOfSlash, "D");

            } else if (userInput.trim().startsWith("event ")) {
                handleNewTask(userInput, indexOfSlash, "E");

            } else if (userInput.trim().startsWith("mark ")) {
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
                // run();

            } else if (userInput.trim().startsWith("unmark ")) {
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
                // run();

            } else if (userInput.trim().equals("list")) {
                // System.out.println(inputMemory.length);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    Task currentTask = inputTasks.get(i);
                    System.out.println((i + 1) + ". " + currentTask);
                }
                // run();

            } else if (userInput.trim().startsWith("delete ")) {
                Task tempRecord;
                // truncate the front part
                String temp = userInput.substring(7);
                // System.out.println(temp);
                int indexToDelete = Character.getNumericValue(temp.charAt(0));
                // System.out.println(indexToChange);
                if ((indexToDelete > 0) || (indexToDelete < 100)) {
                    tempRecord = inputTasks.get(indexToDelete - 1);
                    int originalInputListSize = inputTasks.size();

                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tempRecord);
                    System.out.println(originalInputListSize + " input task size");

                    // shift everything forward by 1, starting at the element to be removed (which is replaced by next element)
                    for (int i = (indexToDelete - 1); i < originalInputListSize; i++) {
                        // move everything up by 1
                        if (i == (originalInputListSize - 1)) {
                            // System.out.println(i + " remove");
                            inputTasks.remove(i);
                        } else {
                            // System.out.println(i + " replace");
                            inputTasks.set(i, inputTasks.get(i + 1));
                        }
                    }
                    count -= 1;
                    System.out.println("Now you have " + count + " tasks in the list.");
                }
                // run();

            } else {
                throw new IncorrectFormatException("Input should be a task or command!"); // programme breaks
//                inputTasks.add(count, new Task(userInput)); // Stores user input
//                System.out.println(userInput);  // Output user input
//                count += 1;
//                run();
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
            System.out.println("caught Index Out of Bounds Exception");

        } catch (StackOverflowError e) {
            System.out.println(e);
            System.out.println("caught Stack Overflow Error");

        } catch (NullPointerException e) {
            System.out.println(e);
            System.out.println("caught Null pointer exception");

        } catch (IncorrectFormatException e) {
            System.out.println(e);
            System.out.println("Incorrect format exception!");

        } finally {
            // clean up
            // System.out.println("cleaning up. Process resumes. Please enter your new input");
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

