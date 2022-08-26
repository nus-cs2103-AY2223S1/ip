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

public class Pixel {

    private static int count = 0;
    private final Scanner myScanner = new Scanner(System.in);  // Create a Scanner object
    private final ArrayList<Task> inputTasks = new ArrayList<>(100);
    private final String filePath;
    private final Storage storage;
    private final Parser parser;
    private final TaskList taskList;

    public Pixel(String filePath) {
        this.filePath = filePath;
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        this.taskList = new TaskList();
    }

    private void run() {
        String userInput = myScanner.nextLine();  // Read user input
        int indexOfSlash = userInput.indexOf("/"); // returns -1 if such a string doesn't exist
        parser.parse(userInput, indexOfSlash);
    }

    // Parser: deals with making sense of the user command
    private class Parser { // inner class

        private Parser() {}

        private void parse(String userInput, int indexOfSlash) {
            try {
                if (userInput.strip().equals("bye")) {
                    System.out.println(UserInterface.goodbyeMessage);
                    System.exit(0);

                } else if (userInput.strip().startsWith("todo ")) {
                    taskList.handleNewTask(userInput, indexOfSlash, "T");

                } else if (userInput.strip().startsWith("deadline ")) {
                    taskList.handleNewTask(userInput, indexOfSlash, "D");

                } else if (userInput.strip().startsWith("event ")) {
                    taskList.handleNewTask(userInput, indexOfSlash, "E");

                } else if (userInput.strip().startsWith("mark ")) {
                    // truncate the front part
                    String temp = userInput.substring(5);
                    // System.out.println(temp);
                    int indexToChange = Character.getNumericValue(temp.charAt(0));
                    // System.out.println(indexToChange);
                    if ((indexToChange > 0) && (indexToChange < 100)) {
                        inputTasks.get(indexToChange - 1).markAsDone();
                    }

                    storage.deleteFileContent();
                    for (Task task : inputTasks) {
                        storage.appendToFile(task);
                    }

                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println(inputTasks.get(indexToChange - 1));
                    // run();

                } else if (userInput.strip().startsWith("unmark ")) {
                    // truncate the front part
                    String temp = userInput.substring(7);
                    // System.out.println(temp);
                    int indexToChange = Character.getNumericValue(temp.charAt(0));
                    // System.out.println(indexToChange);
                    if ((indexToChange > 0) && (indexToChange < 100)) {
                        inputTasks.get(indexToChange - 1).markAsNotDone();
                    }

                    storage.deleteFileContent();
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
                    Task tempRecord;
                    // truncate the front part
                    String temp = userInput.substring(7);
                    // System.out.println(temp);
                    int indexToDelete = Character.getNumericValue(temp.charAt(0));
                    // System.out.println(indexToChange);
                    if ((indexToDelete > 0) && (indexToDelete < 100)) {
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

                        storage.deleteFileContent();
                        for (Task task : inputTasks) {
                            storage.appendToFile(task);
                        }

                        count -= 1;
                        System.out.println("Now you have " + count + " tasks in the list.");
                    }
                    // run();

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

    private class TaskList {

        private TaskList() {}

        private void handleNewTask(String userInput, int indexOfSlash, String type) throws IOException {
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
                    throw new IncorrectFormatException("Invalid format of input!"); // programme breaks

            }

            inputTasks.add(count, newTask);

            // Not so efficient method
            // first delete existing content in old file
            storage.deleteFileContent();

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
        System.out.println(UserInterface.introMessage);
        test.run();
    }
}

