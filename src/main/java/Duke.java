import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class Duke {
    private static final String EXIT_COMMAND = "bye";
    private static final String LINE = "\n========================================================";
    private static final String WELCOME_MESSAGE = "Hello, my name is Duke!\nHow can I help you today?";
    private static final String EXIT_MESSAGE = LINE + "\nGoodbye! Looking forward to see you again soon!" + LINE;
    private static final String FILE_NAME = "dukeList.txt";

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + LINE + "\n" + WELCOME_MESSAGE);

        // ArrayList to store user inputs
        List<Task> listOfInputs = new ArrayList<>();
        // Array to parse the input content
        String[] inputArray;
        // Create a file to output/get list of tasks
        Path dataFile = Paths.get(FILE_NAME);
        // Manage File as a Document
        Document taskList = new Document(dataFile);
        if (Files.exists(dataFile)) {
            listOfInputs = taskList.readTasks();
            System.out.println("Note: All previously stored tasks have been loaded from " + dataFile + LINE);
        } else {
            Files.createFile(dataFile);
            System.out.println("Note: All tasks created will be stored in a file named " + dataFile + LINE);
        }

        // Echo user input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equalsIgnoreCase(EXIT_COMMAND)) { // Stops loop if command is 'bye'
            try {
                inputArray = input.split(" ");

                switch (inputArray[0].toUpperCase()) {
                case "LIST":
                    if (listOfInputs.size() == 0) { // List is empty
                        System.out.println(LINE + "\nYour list is empty! Why not add a task to it first?" + LINE);
                        input = sc.nextLine();
                        continue;
                    } else {
                        System.out.println(LINE + "\nHere are the tasks in your list:");
                        for (int i = 0; i < listOfInputs.size(); i++) {
                            Task curTask = listOfInputs.get(i);
                            System.out.println((i + 1) + "." + curTask.toString());
                        }
                        System.out.println(LINE);
                    }
                    input = sc.nextLine();
                    continue;
                case "MARK":
                    if (inputArray.length < 2) {
                        throw new ArrayIndexOutOfBoundsException("You did not state a task number!" + LINE);
                    }
                    int taskNum = Integer.parseInt(inputArray[1]) - 1;
                    Task taskChosen = listOfInputs.get(taskNum);
                    taskChosen.markAsDone();
                    taskList.refreshList(listOfInputs);
                    System.out.println(LINE + "\nOkay, I have marked this task as done: \n"
                            + taskChosen + LINE);
                    input = sc.nextLine();
                    continue;
                case "UNMARK":
                    if (inputArray.length < 2) {
                        throw new ArrayIndexOutOfBoundsException("You did not state a task number!" + LINE);
                    }
                    taskNum = Integer.parseInt(inputArray[1]) - 1;
                    taskChosen = listOfInputs.get(taskNum);
                    taskChosen.markAsUndone();
                    taskList.refreshList(listOfInputs);
                    System.out.println(LINE + "\nOkay, I have marked this task as not done: \n"
                            + taskChosen + LINE);
                    input = sc.nextLine();
                    continue;
                case "TODO":
                    if (inputArray.length < 2) {
                        throw new DukeException("The description of a todo cannot be empty" + LINE);
                    }
                    String[] taskDesc = Arrays.copyOfRange(inputArray, 1, inputArray.length);
                    Todo newToDo = new Todo(String.join(" ", taskDesc));
                    listOfInputs.add(newToDo);
                    taskList.refreshList(listOfInputs);
                    System.out.println(LINE + "\nGot it! I have added this task to your list:\n  " + newToDo
                            + "\nNow you have " + listOfInputs.size() + " tasks in the list." + LINE);
                    input = sc.nextLine();
                    continue;
                case "DEADLINE":
                    taskDesc = Arrays.copyOfRange(inputArray, 1, inputArray.length);
                    taskDesc = String.join(" ", taskDesc).split("/by");
                    Deadline newDeadline = new Deadline(taskDesc[0], taskDesc[1]);
                    listOfInputs.add(newDeadline);
                    taskList.refreshList(listOfInputs);
                    System.out.println(LINE + "\nGot it! I have added this task to your list:\n  " + newDeadline
                            + "\nNow you have " + listOfInputs.size() + " tasks in the list." + LINE);
                    input = sc.nextLine();
                    continue;
                case "EVENT":
                    taskDesc = Arrays.copyOfRange(inputArray, 1, inputArray.length);
                    taskDesc = String.join(" ", taskDesc).split("/at");
                    Event newEvent = new Event(taskDesc[0], taskDesc[1]);
                    listOfInputs.add(newEvent);
                    taskList.refreshList(listOfInputs);
                    System.out.println(LINE + "\nGot it! I have added this task to your list:\n  " + newEvent
                            + "\nNow you have " + listOfInputs.size() + " tasks in the list." + LINE);
                    input = sc.nextLine();
                    continue;
                case "DELETE":
                    Task removedTask = listOfInputs.get(Integer.parseInt(inputArray[1]) - 1);
                    listOfInputs.remove(Integer.parseInt(inputArray[1]) - 1);
                    taskList.refreshList(listOfInputs);
                    System.out.println(LINE + "\nOkay, I have removed this task from the list:\n  " + removedTask
                            + "\nNow you have " + listOfInputs.size() + " tasks in the list." + LINE);
                    input = sc.nextLine();
                    continue;
                }
                throw new DukeException("I'm sorry, but I don't know what that means" + LINE);
            } catch (DukeException err) {
                System.out.println(LINE + "\n:( OOPS! " + err.getMessage());
                input = sc.nextLine();
            } catch (ArrayIndexOutOfBoundsException err) {
                System.out.println(LINE + "\nSlow down! " + err.getMessage());
                input = sc.nextLine();
            }
        }

        System.out.println(EXIT_MESSAGE);
    }
}
