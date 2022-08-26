package pixel;

import pixel.task.Deadline;
import pixel.task.Event;
import pixel.task.Task;
import pixel.task.ToDo;
import pixel.util.IncorrectFormatException;
import pixel.util.Storage;
import pixel.util.UserInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class of chatbot
 */
public class Pixel {

    public static int count = 0; // made public for testing
    private final Scanner myScanner = new Scanner(System.in);  // Create a Scanner object
    public static final ArrayList<Task> inputTasks = new ArrayList<>(100); // made public for testing

    // the following fields are made final to facilitate testing, should be private
    public final String filePath;
    public final Storage storage;
    public final Parser parser;
    public final TaskList taskList;

    public Pixel(String filePath) {
        this.filePath = filePath;
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        this.taskList = new TaskList();
    }

    private void run() {
        while (myScanner.hasNextLine()) {
            String userInput = myScanner.nextLine();  // Read user input
            parser.parse(userInput);
        }
    }

    // Parser: deals with making sense of the user command
    // Class is made public to facilitate testing, should be private
    public class Parser { // inner class

        public Parser() {}

        // Method is made public to facilitate testing, should be private
        public void parse (String userInput) {

            try {
                if (userInput.strip().equals("bye")) {
                    System.out.println(UserInterface.GOODBYE_MESSAGE);
                    System.exit(0);

                } else if (userInput.strip().startsWith("todo ")) {
                    taskList.handleNewTask(userInput, "T");

                } else if (userInput.strip().startsWith("deadline ")) {
                    taskList.handleNewTask(userInput, "D");

                } else if (userInput.strip().startsWith("event ")) {
                    taskList.handleNewTask(userInput, "E");

                } else if (userInput.strip().startsWith("mark ")) {
                    // truncate the front part
                    String temp = userInput.substring(5);
                    // System.out.println(temp);
                    int indexToChange = Character.getNumericValue(temp.charAt(0));
                    // System.out.println(indexToChange);
                    if ((indexToChange > 0) && (indexToChange < 100)) {
                        inputTasks.get(indexToChange - 1).markAsDone();
                    }

                    storage.resetFile();
                    for (Task task : inputTasks) {
                        storage.appendToFile(task);
                    }

                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println(inputTasks.get(indexToChange - 1));

                } else if (userInput.strip().startsWith("unmark ")) {
                    // truncate the front part
                    String temp = userInput.substring(7);
                    // System.out.println(temp);
                    int indexToChange = Character.getNumericValue(temp.charAt(0));
                    // System.out.println(indexToChange);
                    if ((indexToChange > 0) && (indexToChange < 100)) {
                        inputTasks.get(indexToChange - 1).markAsNotDone();
                    }

                    storage.resetFile();
                    for (Task task : inputTasks) {
                        storage.appendToFile(task);
                    }

                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(inputTasks.get(indexToChange - 1));
                    // run();

                } else if (userInput.strip().equals("list")) {
                    // System.out.println(inputMemory.length);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {
                        Task currentTask = inputTasks.get(i);
                        System.out.println((i + 1) + ". " + currentTask);
                    }
                    // run();

                } else if (userInput.strip().startsWith("delete ")) {
                    storage.deleteEntry(userInput);

                } else if (userInput.strip().startsWith("find ")) {
                    ArrayList<Task> results = storage.findEntry(userInput);
                    System.out.println("Here are the matching tasks in your list:");
                    for (int i = 0; i < results.size(); i++) {
                        Task currentTask = results.get(i);
                        System.out.println((i + 1) + ". " + currentTask);
                    }

                } else {
                    throw new IncorrectFormatException("Input should be a task or command!"); // programme breaks
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

            } catch (IOException e) {
                System.out.println(e);
                System.out.println("Caught IO exception! Please ensure that the file address provided is valid");

            } finally {
                // clean up
                // System.out.println("cleaning up. Process resumes. Please enter your new input");
                run();
            }
        }

    }

    // Made public to facilitate testing
    public class TaskList {

        public TaskList() {}

        public void handleNewTask(String userInput, String type) throws IOException {
            int indexOfSlash = userInput.indexOf("/"); // returns -1 if such a string doesn't exist
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
                    throw new IncorrectFormatException("Slash should be followed by \"by\" or \"at\"!"); // programme breaks
                }
            }

            switch (type) {
                case "T": { // todo
                    String description = userInput.substring(5, indexOfEndOfDescription);
                    newTask = new ToDo(description, due, commandWord); // Stores user input

                    break;
                }
                case "D": { // deadline
                    String description = userInput.substring(9, indexOfEndOfDescription);
                    newTask = new Deadline(description, due, commandWord); // Stores user input

                    break;
                }
                case "E": { // event
                    String description = userInput.substring(6, indexOfEndOfDescription);
                    newTask = new Event(description, due, commandWord); // Stores user input

                    break;
                }
                default:  //shouldn't reach here
                    throw new IncorrectFormatException("Incorrect format of input!"); // programme breaks

            }

            inputTasks.add(count, newTask);

            // Not so efficient method
            // first delete existing content in old file
            storage.resetFile();

            // run through all the files in the list and update pixel.txt accordingly
            for (Task task : inputTasks) {
                storage.appendToFile(task);
            }

            count += 1;
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.println("Now you have " + count + " tasks in the list.");
            // run();
        }
    }


    public static void main(String[] args) {
        Pixel test = new Pixel("C:/!Education/CS2103/gitFolderOne/data/pixel.txt");
        System.out.println(UserInterface.INTRO_MESSAGE);
        test.run();
    }
}

