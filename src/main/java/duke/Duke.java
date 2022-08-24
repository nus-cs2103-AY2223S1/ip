package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import duke.domain.Task;
import duke.domain.Todo;
import duke.exceptions.IncorrectArgumentException;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidTaskSpecificationException;
import duke.exceptions.MissingArgumentException;
import duke.exceptions.TaskNotFoundException;
import duke.exceptions.UnknownCommandException;

/**
 * The type duke.Duke.
 */
public class Duke {

    private static final String exitCommand = "bye";
    private static final String listCommand = "list";
    private static final String markCommand = "mark";
    private static final String todoCommand = "todo";
    private static final String deadlineCommand = "deadline";
    private static final String deadlineSubCommand = " /by ";
    private static final String eventCommand = "event";
    private static final String eventSubCommand = " /at ";
    private static final String advancedListSubCommand1 = "/before ";
    private static final String advancedListSubCommand2 = "/after ";
    private static final String unmarkCommand = "unmark";
    private static final String deleteCommand = "delete";
    private static final String exitMessage = "Goodbye and have a nice day!";
    private static final String delimiter = "@@@";
    private static final String nullSymbol = "$_$";

    /**
     * Returns a boolean corresponding to whether the given string is numeric.
     *
     * @param strNum
     *            String to be tested
     * @return boolean representing whether the string is numeric
     */
    private static boolean isNumeric(String strNum) {
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
        } catch (FileNotFoundException ignored) {
            ;
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
     * Starts up the duke machine and load the duke.data from the hard disk.
     * The method also creates the folder and duke.data file if it does not exist.
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

            File dataFile = new File("src/main/java/duke.data/duke.data.txt");
            try {
                Scanner sc = new Scanner(dataFile);
                while (sc.hasNextLine()) {
                    String taskString = sc.nextLine();
                    if (!Objects.equals(taskString, "")) {
                        String[] taskArgs = taskString.split(delimiter);
                        initTask.add(
                                Task.of(
                                        taskArgs[0],
                                        taskArgs[1],
                                        taskArgs[2],
                                        taskArgs[3]));
                    }
                }
                sc.close();
                taskController.loadTasks(initTask);
                return taskController;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(
                        "This cannot happen has the duke.data file is ensured to be there");
            }
        } catch (Exception e) {
            System.out.println("UH OH");
            System.out.println(e.getMessage());
            return new TaskController();
        }
    }

    /**
     * Write Tasks to the Data File.
     */
    private static void writeDataToFile(List<String> tasks) {
        FileWriter writer;
        try {
            writer = new FileWriter("src/main/java/duke.data/duke.data.txt", false);
            for (String task : tasks) {
                try {
                    writer.write(task + "\n" + "");
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

    /**
     * @throws IOException
     * @throws SecurityException
     */
    private static void ensureDataFileExist()
            throws IOException, SecurityException {
        String dataDirPathString = "src/main/java/duke.data/";
        Path dataDirPath = Paths.get(dataDirPathString);
        boolean dataDirExists = Files.isDirectory(dataDirPath);

        String dataFilePathString = dataDirPathString + "duke.data.txt";
        Path dataFilePath = Paths.get(dataFilePathString);
        boolean dataFileExists = Files.exists(dataFilePath);
        if (!(dataDirExists && dataFileExists)) {
            createDataFile();
            return;
        }
        new File(dataFilePathString);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void createDataFile() throws IOException, SecurityException {
        File dataFile = new File("src/main/java/duke.data/duke.data.txt");
        dataFile.getParentFile().mkdirs();
        dataFile.createNewFile();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
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
            System.arraycopy(
                    commandArgs,
                    1,
                    commandArgsCopy,
                    0,
                    commandArgs.length - 1);

            try {
                switch (commandArgs[0]) {
                case exitCommand:
                    flag = false;
                    break;
                case todoCommand:
                    String todoText = String.join(" ", commandArgsCopy);

                    if (commandArgsCopy.length > 0) {
                        Task newTodo = Todo.of(todoText);
                        taskController.addTask(newTodo);
                        Duke.writeDataToFile(
                                taskController.exportTaskList());
                    } else {
                        throw new MissingArgumentException(
                                "The description of the todo cannot be empty!");
                    }
                    break;
                case deadlineCommand:
                    String deadlineText = String.join(" ", commandArgsCopy);
                    if (deadlineText.contains(deadlineSubCommand)) {
                        String[] deadlineArgs = deadlineText.split(
                                deadlineSubCommand);
                        String deadlineTitle = deadlineArgs[0];
                        String deadline = deadlineArgs[1];

                        Task newDeadline = Task.of(
                                "D",
                                "0",
                                deadlineTitle,
                                deadline);
                        taskController.addTask(newDeadline);
                        Duke.writeDataToFile(
                                taskController.exportTaskList());
                    } else {
                        throw new MissingArgumentException(
                                "Deadlines need a /by command");
                    }
                    break;
                case eventCommand:
                    String eventText = String.join(" ", commandArgsCopy);
                    if (eventText.contains(eventSubCommand)) {
                        String[] eventArgs = eventText.split(
                                eventSubCommand);
                        String eventTitle = eventArgs[0];
                        String eventDateTime = eventArgs[1];

                        Task newEvent = Task.of(
                                "E",
                                "0",
                                eventTitle,
                                eventDateTime);
                        taskController.addTask(newEvent);
                        Duke.writeDataToFile(
                                taskController.exportTaskList());
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
                    String advancedListText = String.join(
                            " ",
                            commandArgsCopy);
                    if (advancedListText.contains(advancedListSubCommand1)) {
                        String[] advancedListArgs = advancedListText.split(
                                advancedListSubCommand1);
                        String advancedListDateTime = advancedListArgs[1];
                        taskController.listTasks(
                                advancedListDateTime,
                                true);
                    } else if (advancedListText.contains(advancedListSubCommand2)) {
                        String[] advancedListArgs = advancedListText.split(
                                advancedListSubCommand2);
                        String advancedListDateTime = advancedListArgs[1];
                        taskController.listTasks(
                                advancedListDateTime,
                                false);
                    } else {
                        taskController.listTasks();
                    }
                    break;
                default:
                    throw new UnknownCommandException(
                            "Sorry I don't understand that command");
                }
            } catch (UnknownCommandException
                    | MissingArgumentException
                    | TaskNotFoundException
                    | IncorrectArgumentException
                    | InvalidDateTimeException
                    | InvalidTaskSpecificationException e) {
                System.out.println(e.getMessage());
            }
        }

        Duke.goodbye();

        scannerObj.close();
    }
}
