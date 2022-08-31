package ip;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    // Printables
    private static final String TAB = "    ";
    private static final String LINE = String
            .format("%s%s", TAB, "____________________________________________________________");
    private static final String WELCOME_MESSAGE = String
            .format("Hello! I'm Duke\n%s What can I do for you?", TAB);
    private static final String END_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String CREATE_MESSAGE = "Got it. I've added this task:";
    private static final String MARK_MESSAGE = "Nice! I've marked this task as done:";
    private static final String UNMARK_MESSAGE = "OK, I've marked this task as not done yet:";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String DELETE_MESSAGE = "Noted. I've removed this task:";
    private static final String STORAGE_FILE_PATH = "Duke.txt";

    // Commands
    private static final String END_COMMAND = "bye";
    private static final String PRINT_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    // Data structures
    private static final List<Task> taskList = new ArrayList<>();

    private static void loadFile() {
        try {
            // Create Duke.txt if it doesn't exist.
            File f = new File(STORAGE_FILE_PATH);
            if (!f.exists()) {
                boolean fileCreated = f.createNewFile();
                if (!fileCreated) {
                    throw new IOException();
                }
            } else {
                // Read from Duke.txt and handle it as input.
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    loadTask(sc.nextLine());
                }
                sc.close();
            }
        } catch (IOException e) {
            prettyPrint("Storage file could not be created!");
        }
    }

    private static void loadTask(String taskStr) {
        try {
            String[] taskArray = taskStr.split("\\|");
            String taskTypeStr = taskArray[0];
            String isDoneStr = taskArray[1];
            String description = taskArray[2];


            boolean isDone = isDoneStr.equals("X");

            Task newTask;
            switch (taskTypeStr) {
                case "T":
                    newTask = new ToDo(description, isDone);
                    break;
                case "D":
                    String by = taskArray[3];
                    newTask = new Deadline(description, by, isDone);
                    break;
                case "E":
                    String at = taskArray[3];
                    newTask = new Event(description, at, isDone);
                    break;
                default:
                    throw new DukeException(String.format("Could not load task: %s", description));
            }

            taskList.add(newTask);
        } catch (DukeException de){
            prettyPrint(de.toString());
        }

    }

    private static void storeToFile() {
        try {
            FileWriter fw = new FileWriter(STORAGE_FILE_PATH);
            fw.write("");

            for (Task t: taskList) {
                fw.append(t.getStorageString());
                if (!t.equals(taskList.get(taskList.size() - 1))) {
                    fw.append("\n");
                }
            }

            fw.close();
        } catch (IOException e) {
            prettyPrint("Failed to save all data!");
        }

    }

    private static void createTask(String str, TaskType type){
        try {
            str = str.strip();
            Task newTask;
            String description = "";
            String extraInfo = "";
            switch(type) {
                case TODO:
                    if (str.strip().equals("")) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    description = str;
                    newTask = new ToDo(str);
                    break;
                case DEADLINE:
                    if (str.strip().equals("")) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] strList = str.split(" /by ");
                    description = strList[0];
                    extraInfo = strList[1];
                    newTask = new Deadline(description, extraInfo);
                    break;
                case EVENT:
                    if (str.strip().equals("")) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    strList = str.split(" /at ");
                    description = strList[0];
                    extraInfo = strList[1];
                    newTask = new Event(description, extraInfo);
                    break;
                default:
                    throw new DukeException("Type of task is not recognised!");
            }
            taskList.add(newTask);
            String singulStr = "Now you have 1 task in the list";
            if (taskList.size() == 1) {
                prettyPrint(String.format("%s\n%s   %s\n%s %s",
                        CREATE_MESSAGE, TAB, newTask.toString(), TAB, singulStr));
            } else {
                prettyPrint(String.format("%s\n%s   %s\n%s Now you have %d tasks in the list.",
                        CREATE_MESSAGE, TAB, newTask.toString(), TAB, taskList.size()));
            }
        } catch (DukeException e) {
            prettyPrint(e.toString());
        }
    }

    /**
     * Marks a task at index as done
     */
    private static void markTask(String index) {
        index = index.strip();
        int i = Integer.parseInt(index);
        i--;
        Task task = taskList.get(i);
        task.markAsDone();
        prettyPrint(String.format("%s\n%s   %s", MARK_MESSAGE, TAB, task.toString()));
    }

    /**
     * Marks a task at index as not done
     */
    private static void unmarkTask(String index) {
        index = index.strip();
        int i = Integer.parseInt(index);
        i--;
        Task task = taskList.get(i);
        task.markAsUndone();
        prettyPrint(String.format("%s\n%s   %s", UNMARK_MESSAGE, TAB, task.toString()));
    }

    private static void deleteTask(String index) {
        index = index.strip();
        int i = Integer.parseInt(index);
        i--;
        Task task = taskList.remove(i);
        String singulStr = "Now you have 1 task in the list";
        if (taskList.size() == 1) {
            prettyPrint(String.format("%s\n%s   %s\n%s %s",
                    DELETE_MESSAGE, TAB, task.toString(), TAB, singulStr));
        } else {
            prettyPrint(String.format("%s\n%s   %s\n%s Now you have %d tasks in the list.",
                    DELETE_MESSAGE, TAB, task.toString(), TAB, taskList.size()));
        }
    }

    /**
     * Creates a new Collection that has each entry numbered in ascending order
     */
    private static void printAll() {
        List<String> printables = new ArrayList<>();
        printables.add(LIST_MESSAGE);
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            int index = i + 1;
            printables.add(String.format("%d.%s", index, task.toString()));
        }
        prettyPrint(printables);
    }

    private static void prettyPrint() {
        prettyPrint("");
    }

    private static void prettyPrint(String printable) {
        String out = String.format("%s\n%s %s\n%s", LINE, TAB, printable, LINE);
        System.out.println(out);
    }

    private static void prettyPrint(List<String> printables) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < printables.size(); i++) {
            String s = printables.get(i);
            if (i == 0) {
                sb.append(s);
            }
            else {
                sb.append(String.format("\n%s %s", TAB, s));
            }
        }
        String printable = sb.toString();
        prettyPrint(printable);
    }

    public static boolean handleInput(Scanner sc) throws DukeException {
        String inputCmd = sc.next();
        String inputRem = sc.nextLine();
        String storageStr = String.format("%s %s", inputCmd, inputRem);
        switch (inputCmd) {
        case (END_COMMAND):
            return false;
        case (PRINT_COMMAND):
            printAll();
            break;
        case (MARK_COMMAND):
            markTask(inputRem);
            break;
        case (UNMARK_COMMAND):
            unmarkTask(inputRem);
            break;
        case (TODO_COMMAND):
            createTask(inputRem, TaskType.TODO);
            break;
        case (DEADLINE_COMMAND):
            createTask(inputRem, TaskType.DEADLINE);
            break;
        case (EVENT_COMMAND):
            createTask(inputRem, TaskType.EVENT);
            break;
        case (DELETE_COMMAND):
            deleteTask(inputRem);
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }

    public static void main(String[] args) throws DukeException {
        loadFile();
        Scanner sc = new Scanner(System.in);
        prettyPrint(WELCOME_MESSAGE);
        boolean canNext = true;
        while (canNext) {
            try {
                canNext = handleInput(sc);
            } catch (DukeException e) {
                prettyPrint(e.toString());
            }
        }
        storeToFile();
        prettyPrint(END_MESSAGE);
    }
}
