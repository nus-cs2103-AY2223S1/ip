import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;

public class Duke {
    private static final String startUpMessage  = "Hello! I'm Duke\n"
                                                + "What can I do for you?";
    private static final String exitMessage = "Bye. Hope to see you again soon!";
    private static final String exitCmd = "bye";
    private static final String noTaskMessage   = "It appears you have no tasks right now,\n"
                                                + "would you like to add some?";
    private static final String addTaskMessage = "Got it. I've added this task:\n";
    private static final String taskMarkedMessage = "Nice! I've marked this task as done:\n";
    private static final String taskUnmarkedMessage = "OK, I've marked this task as not done yet:\n";
    private static final String alreadyMarkedMessage = "This task is already marked:\n";
    private static final String alreadyUnmarkedMessage = "This task is already unmarked:\n";
    private static final String deleteTaskMessage = "Noted. I've removed this task:\n";
    private static final String noSuchTaskMessage   = "It appears there is no such task, \n"
                                                    + "Please try again";
    private static final String invalidInputMessage = "The input is invalid, please try again.";
    private static final String noDescriptionMessage = "The description of the task cannot be empty.";
    private static final String noTimeGivenMessage  = "Please provide the relevant time for this type of task,\n"
                                                    + "by typing \"/\" followed by the time.";
    private static final String noIndexGivenMessage = "Please provide the index of he relevant task after the\n"
                                                    + "command.";
    private static final String dataFileErrorMessage    = "There appears to be an issue retrieving your previous records";
    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static final Map<String, Function<String[], Task>> taskMaker = Map.of(
            "todo"
            , (input) -> new TodoTask(input[1])
            , "deadline"
            , (input) -> new DeadlineTask(input[1], input[2])
            , "event"
            , (input) -> new EventTask(input[1], input[2])
            );
    private static final Map<String, Function<Integer, Integer>> taskOperations = Map.of(
            "mark"
            , (input) -> {
                boolean valid = tasks.get(input - 1).check();
                System.out.println(
                        (valid ? taskMarkedMessage : alreadyMarkedMessage)
                                + tasks.get(input - 1).toString());
                updateSave();
                return 0;
            }
            , "unmark"
            , (input) -> {
                boolean valid = tasks.get(input - 1).uncheck();
                System.out.println(
                        (valid ? taskUnmarkedMessage : alreadyUnmarkedMessage)
                                + tasks.get(input - 1).toString());
                updateSave();
                return 0;
            }
            , "delete"
            , (input) -> {
                Task removed = tasks.get(input - 1);
                tasks.remove(input - 1);
                System.out.println(deleteTaskMessage + removed.toString());
                System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
                updateSave();
                return 0;
            }
    );
    public static void main(String[] args) {
        tasks.addAll(retrieveData());
        System.out.println(startUpMessage);
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals(exitCmd)) {
            try {
                String[] inputSplit = inputSplit(userInput);
                if (userInput.equals("list")) {
                    System.out.println(printTasks());
                } else if (taskOperations.containsKey(inputSplit[0])) {
                    try {
                        if (inputSplit.length < 2) {
                            throw new DukeException(noIndexGivenMessage);
                        }
                        int taskNum = Integer.parseInt(inputSplit[1]);
                        if (taskNum > tasks.size() || taskNum <= 0) {
                            System.out.println(noSuchTaskMessage);
                        } else {
                            taskOperations.get(inputSplit[0]).apply(taskNum);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(invalidInputMessage);
                    }
                } else if (taskMaker.containsKey(inputSplit[0])) {
                    Task newTask = taskMaker.get(inputSplit[0]).apply(inputSplit);
                    tasks.add(newTask);
                    appendTaskToDisk(newTask);
                    System.out.println(addTaskMessage + newTask);
                    System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
                }  else {
                    throw new DukeException(invalidInputMessage);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            userInput = sc.nextLine();
        }
        System.out.println(exitMessage);
    }

    static String printTasks() {
        if (tasks.isEmpty()) {
            return noTaskMessage;
        } else {
            StringBuilder message = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                message.append(String.format("%d. %s"
                                + (i != tasks.size() - 1 ? "\n" : "")
                                , i + 1, tasks.get(i).toString()));
            }
            return message.toString();
        }
    }

    static ArrayList<Task> retrieveData() {
        String DATAPATH = "data/";
        String SAVE_FILE_NAME = "duke.txt";
        ArrayList<Task> savedTasks = new ArrayList<>();
        File file = new File(DATAPATH);
        File textFile = new File(DATAPATH + SAVE_FILE_NAME);
        if (!file.isDirectory() || !textFile.exists()) {
            try {
                file.mkdir();
                textFile.createNewFile();
            } catch (IOException e) {
                System.out.println(dataFileErrorMessage);
            }
        } else {
            try {
                Scanner sc = new Scanner(textFile);
                while (sc.hasNext()) {
                    String currentRecord = sc.nextLine();
                    String[] split = currentRecord.split("###");
                    try {
                        if (split.length != 3) {
                            throw new DukeException("Error");
                        }
                        Task loadTask = TaskMaker.createTask(split[0], split[1], split[2]);
                        savedTasks.add(loadTask);
                    } catch (DukeException e) {
                        continue;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println(dataFileErrorMessage);
            }
        }
        return savedTasks;
    }

    static void appendTaskToDisk(Task task) {
        try {
            FileWriter writer = new FileWriter("data/duke.txt", true);
            writer.write(task.saveFileFormat() + "\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    static void updateSave() {
        try {
            FileWriter writer = new FileWriter("data/duke.txt");
            StringBuilder toWrite = new StringBuilder();
            for (Task task : tasks) {
                toWrite.append(task.saveFileFormat() + "\n");
            }
            writer.write(toWrite.toString());
            writer.flush();
        } catch (IOException e) {
            System.out.println("An error has occured");
        }
    }

    static String[] inputSplit(String input) throws DukeException {
        if (input.contains("mark") || input.contains("unmark") || input.contains("delete")) {
            String[] result = input.split(" ");
            if (result.length == 2
                    && (result[0].equals("mark")
                        || result[0].equals("unmark")
                        || result[0].equals("delete"))) {
                return result;
            }
        }
        if (input.contains("todo")) {
            String[] result = new String[2];
            result[0] = input.substring(0,4);
            result[1] = input.substring(4);
            if (result[1].isBlank()) {
                throw new DukeException(noDescriptionMessage);
            } else if (result[0].equals("todo")) {
                return result;
            }
        }
        if (input.contains("deadline") || input.contains("event")) {
            String[] result = new String[3];
            if (input.lastIndexOf("/") == -1) {
                throw new DukeException(noTimeGivenMessage);
            }
            boolean isDeadline = input.substring(0,8).equals("deadline");
            result[0] = isDeadline ? input.substring(0,8) : input.substring(0,5);
            result[1] = input.substring(isDeadline ? 8 : 5, input.lastIndexOf('/'));
            result[2] = input.substring(input.lastIndexOf("/") + 1);
            if (result[1].isBlank()) {
                throw new DukeException(noDescriptionMessage);
            }
            return result;
        }
        return new String[]{input};
    }
}
