import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import events.Deadline;
import events.Event;
import events.Task;
import events.Todo;
import exceptions.IncorrectArgumentException;
import exceptions.MissingArgumentException;
import exceptions.TaskNotFoundException;
import exceptions.UnknownCommandException;

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
    static private final String deleteCommand = "delete";
    static private final String exitMessage = "Goodbye and have a nice day!";
    static private final String delimiter = "@@@";
    static private final String nullSymbol = "$_$";

    /**
     * Returns a boolean corresponding to whether the given string is numeric.
     *
     * @param strNum String to be tested
     * @return boolean representing whether the string is numeric
     */
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

    /**
     * Outputs the greeting message.
     */
    private static void greet() {
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
    }

    /**
     * Outputs the goodbye message.
     */
    private static void goodbye() {
        System.out.println(exitMessage);
    }

    /**
     * Starts up the duke machine and load the data from the hard disk.
     * The method also creates the folder and data file if it does not exist.
     */
    private static void startUp(TaskController taskController) {

    }

    /**
     * Read Tasks from the Data File.
     */
    private static TaskController initializeTaskControllerFromDataFile() {
        try {
            Duke.ensureDataFileExist();
            TaskController taskController = new TaskController();
            List<Task> initTask = new ArrayList<>();

            File dataFile = new File("src/main/java/data/data.txt");
            try {
                Scanner sc = new Scanner(dataFile);
                while (sc.hasNextLine()) {
                    String taskString = sc.nextLine();
                    if (taskString != "") {
                        String[] taskArgs = taskString.split(delimiter);
                        initTask.add(Task.of(taskArgs[0], taskArgs[1], taskArgs[2], taskArgs[3]));
                    }
                }
                sc.close();
                taskController.loadTasks(initTask);
                return taskController;
            } catch (Exception e) {
                throw new RuntimeException(
                        "This cannot happen has the data file is ensured to be there");
            }
        } catch (Exception e) {
            return new TaskController();
        }
    }

    /**
     * Write Tasks to the Data File.
     */
    private static void writeDataToFile(List<String> tasks) {
        FileWriter writer;
        try {
            writer = new FileWriter("src/main/java/data/data.txt");
            for (int i = 0; i < tasks.size(); i++) {
                try {
                    writer.write(tasks.get(i) + "\n" + "");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            try {
                writer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static File ensureDataFileExist() throws IOException, SecurityException {
        String dataDirPathString = "src/main/java/data/";
        Path dataDirPath = Paths.get(dataDirPathString);
        boolean dataDirExists = Files.isDirectory(dataDirPath);

        String dataFilePathString = dataDirPathString + "data.txt";
        Path dataFilePath = Paths.get(dataFilePathString);
        boolean dataFileExists = Files.exists(dataFilePath);
        if (!(dataDirExists && dataFileExists)) {
            return createDataFile();
        }
        return new File(dataFilePathString);
    }

    private static File createDataFile() throws IOException, SecurityException {
        File dataFile = new File("src/main/java/data/data.txt");
        dataFile.getParentFile().mkdirs();
        dataFile.createNewFile();
        return dataFile;
    }

    public static void main(String[] args) {
        // Set up
        TaskController taskController = Duke.initializeTaskControllerFromDataFile();

        Duke.greet();

        Scanner scannerObj = new Scanner(System.in); // Create a Scanner object
        String userInput;

        boolean flag = true;

        while (flag) {
            System.out.println("Enter Command: ");
            userInput = scannerObj.nextLine(); // Read user input

            String[] commandArgs = userInput.split(" ");
            String[] commandArgsCopy = new String[commandArgs.length - 1];
            System.arraycopy(commandArgs, 1, commandArgsCopy, 0, commandArgs.length - 1);

            try {
                switch (commandArgs[0]) {
                    case exitCommand:
                        flag = false;
                        break;

                    case todoCommand:
                        String todoText = String.join(" ", commandArgsCopy);

                        if (commandArgsCopy.length > 0) {
                            Task newTodo = new Todo(todoText);
                            taskController.addTask(newTodo);
                            Duke.writeDataToFile(taskController.exportTaskList());
                        } else {
                            throw new MissingArgumentException(
                                    "The description of the todo cannot be empty!");
                        }
                        break;

                    case deadlineCommand:
                        String deadlineText = String.join(" ", commandArgsCopy);
                        if (deadlineText.contains(deadlineSubCommand)) {
                            String[] deadlineArgs = deadlineText.split(deadlineSubCommand);
                            String deadlineTitle = deadlineArgs[0];
                            String deadline = deadlineArgs[1];

                            Task newDeadline = new Deadline(deadlineTitle, deadline);
                            taskController.addTask(newDeadline);
                            Duke.writeDataToFile(taskController.exportTaskList());
                        } else {
                            throw new MissingArgumentException(
                                    "Deadlines need a /by command");
                        }
                        break;

                    case eventCommand:
                        String eventText = String.join(" ", commandArgsCopy);
                        if (eventText.contains(eventSubCommand)) {
                            String[] eventArgs = eventText.split(eventSubCommand);
                            String eventTitle = eventArgs[0];
                            String eventDateTime = eventArgs[1];

                            Task newEvent = new Event(eventTitle, eventDateTime);
                            taskController.addTask(newEvent);
                            Duke.writeDataToFile(taskController.exportTaskList());
                        } else {
                            throw new MissingArgumentException(
                                    "Events need a /at command");
                        }
                        break;

                    case markCommand:
                        if (isNumeric(commandArgs[1])) {
                            int idx = Integer.parseInt(commandArgs[1]);
                            taskController.markTask(idx - 1);
                        } else {
                            throw new IncorrectArgumentException(
                                    "Sorry the second argument is not a number");
                        }
                        break;

                    case unmarkCommand:
                        if (isNumeric(commandArgs[1])) {
                            int idx = Integer.parseInt(commandArgs[1]);
                            taskController.unmarkTask(idx - 1);
                        } else {
                            throw new IncorrectArgumentException(
                                    "Sorry the second argument is not a number");
                        }
                        break;

                    case deleteCommand:
                        if (isNumeric(commandArgs[1])) {
                            int idx = Integer.parseInt(commandArgs[1]);
                            taskController.deleteTask(idx - 1);
                        } else {
                            throw new IncorrectArgumentException(
                                    "Sorry the second argument is not a number");
                        }
                        break;

                    case listCommand:
                        taskController.listTasks();
                        break;

                    default:
                        throw new UnknownCommandException(
                                "Sorry I don't understand that command");
                }
            } catch (UnknownCommandException | MissingArgumentException | TaskNotFoundException
                    | IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        Duke.goodbye();

        scannerObj.close();
    }
}
