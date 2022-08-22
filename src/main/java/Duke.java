import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {

    private static final String FILEPATH = "./data";
    private static final String FILENAME = "./data/saved-tasks.txt";

    private enum Command {BYE, LIST, MARK, UNMARK, TODO, EVENT, DEADLINE, DELETE}

    private static final String LOGO = " ____        _        \n"
                                       + "|  _ \\ _   _| | _____ \n"
                                       + "| | | | | | | |/ / _ \\\n"
                                       + "| |_| | |_| |   <  __/\n"
                                       + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE = "```````````````````````````````````````````````````````````````````";
    private static final String WELCOME_GREET = "Hello there! I am\n" + LOGO
                + "\nyour personal task tracking assistant!\nWhat can I do for you today?\n";
    private static final String EXIT_GREET = "Bye. Hope to see you again soon!\n";
    private static final LinkedList<Task> TASK_LIST = new LinkedList<>();

    //Data Handling
    private static String printListData() {
        String listData = "";
        for (int i = 0; i < TASK_LIST.size(); i++) {
            int index = i + 1;
            listData += TASK_LIST.get(i).toSave();
        }
        return listData;
    }
    private static void setSaveLocation() {
        try {
            File filePath = new File(FILEPATH);
            if (!filePath.exists()) {
                filePath.mkdir();
            }
            File fileName = new File(FILENAME);
            if (!fileName.exists()) {
                fileName.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void saveData(String str) {
        try {
            FileWriter writer = new FileWriter(new File(FILENAME));
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void retrieveData() {
        File savedData = new File(FILENAME);
        try {
            Scanner scanner = new Scanner(savedData);
            while (scanner.hasNextLine()) {
                String[] splitInput = scanner.nextLine().split(",");
                int len = splitInput.length;
                if (len == 0) {
                    break;
                }
                String taskType = splitInput[0];
                String taskDescription = splitInput[2];
                boolean isDone = splitInput[1].equals("1");
                switch (taskType) {
                case "T":
                    Task todo = new Todo(taskDescription);
                    todo.setStatus(isDone);
                    TASK_LIST.add(todo);
                    break;
                case "D":
                    Task deadline = new Deadline(taskDescription, splitInput[3]);
                    deadline.setStatus(isDone);
                    TASK_LIST.add(deadline);
                    break;
                case "E":
                    Task event = new Event(taskDescription, splitInput[3]);
                    event.setStatus(isDone);
                    TASK_LIST.add(event);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Duke Formatting
    private static void welcomeGreet() {
        System.out.println(WELCOME_GREET + LINE);
    }
    private static void exitGreet() {
       System.out.println(EXIT_GREET + LINE);
    }
    private static void printLine() {
        System.out.println(LINE);
    }

    //General Helpers
    private static boolean isInteger(String str) {
        return str.chars().allMatch(Character::isDigit);
    }
    private static int timeFinder(String[] splitInput) {
        for (int j = 0; j < splitInput.length; j++) {
            if (splitInput[j].equals("/by") || splitInput[j].equals("/at")) {
                return j;
            }
        }
        throw new DukeException("Duke: OOPS!!! The task is missing a time/date property.");
    }
    private static String taskDescriptionFinder(String[] splitInput, int end) {
        String taskDescription = String.join(" ", Arrays.copyOfRange(splitInput, 1, end));
        if (taskDescription.equals("")) {
            throw new DukeException("Duke: OOPS!!! The task description cannot be empty.");
        }
        return taskDescription;
    }
    //

    // List Helpers
    private static void printList() {
        System.out.println("My List Of Tasks :D");
        for (int i = 0; i < TASK_LIST.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + TASK_LIST.get(i).toString());
        }
        printLine();
    }
    private static void taskTracker(int index, Command cmd) {
        if (index >= TASK_LIST.size() || index < 0) {
            throw new DukeException("Duke: Looks like your task list currently does not have a task at this index.");
        }
        Task task = TASK_LIST.get(index);
        switch (cmd) {
        case MARK:
            task.markAsDone();
            break;
        case UNMARK:
            task.markAsNotDone();
            break;
        case DELETE:
            TASK_LIST.remove(index);
            System.out.println("Noted. I've removed this task:\n "+ task.toString()
                    + "\nNow you have " + TASK_LIST.size() + " tasks in the list");
        }
        printLine();
        saveData(printListData());
    }
    private static void taskAdder(String[] splitInput, Command taskType) {
        int len = splitInput.length;
        Task task = null;

        switch (taskType) {
        case TODO:
            task = new Todo(taskDescriptionFinder(splitInput, len));
            break;
        case DEADLINE:
            int byIndex = timeFinder(splitInput);
            String deadlineName = taskDescriptionFinder(splitInput, byIndex);
            String by = String.join(" ", Arrays.copyOfRange(splitInput, byIndex + 1, len));
            if (by.equals("")) {
                throw new DukeException("Duke: OOPS!!! The date/time of the task cannot be empty.");
            }
            task = new Deadline(deadlineName, by);
            break;
        case EVENT:
            int atIndex = timeFinder(splitInput);
            String eventName = taskDescriptionFinder(splitInput, atIndex);
            String at = String.join(" ", Arrays.copyOfRange(splitInput, atIndex + 1, len));
            if (at.equals("")) {
                throw new DukeException("Duke: OOPS!!! The date/time of the task cannot be empty.");
            }
            task = new Event(eventName, at);
            break;
        }
        TASK_LIST.add(task);
        System.out.println("Got it. I've added this task:\n "
                            + task.toString()
                            + "\nNow you have " + TASK_LIST.size() + " tasks in the list.");
        printLine();
        saveData(printListData());
    }
    //

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        setSaveLocation();
        retrieveData();
        welcomeGreet();

        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            String[] splitInput = userInput.split(" ");
            int len = splitInput.length;

            try {
                if (userInput.equals("bye")) {
                    exitGreet();
                    scanner.close();
                    break;
                } else if (userInput.equals("list")) {
                    printList();
                } else if (len == 2 && splitInput[0].equals("mark") && isInteger(splitInput[1])) {
                    taskTracker(parseInt(splitInput[1]) - 1, Command.MARK);
                } else if (len == 2 && splitInput[0].equals("unmark") && isInteger(splitInput[1])) {
                    taskTracker(parseInt(splitInput[1]) - 1, Command.UNMARK);
                } else if (splitInput[0].equals("mark") || splitInput[0].equals("unmark")) {
                    throw new DukeException("Duke: To check off tasks, indicate the index of the " +
                            "task correctly using an integer!");
                } else if (len == 2 && splitInput[0].equals("delete") && isInteger(splitInput[1])) {
                    taskTracker(parseInt(splitInput[1]) - 1, Command.DELETE);
                } else if (splitInput[0].equals("delete")) {
                    throw new DukeException("Duke: To delete tasks, indicate the index of the task " +
                            "correctly using an integer!");
                } else if (splitInput[0].equals("todo")) {
                    taskAdder(splitInput, Command.TODO);
                } else if (splitInput[0].equals("deadline")) {
                    taskAdder(splitInput, Command.DEADLINE);
                } else if (splitInput[0].equals("event")) {
                    taskAdder(splitInput, Command.EVENT);
                } else {
                    throw new DukeException("Duke: OH NO!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException error) {
                System.out.println(error.getMessage());
                printLine();
            }
        }
    }
}
