import java.awt.desktop.SystemEventListener;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Collections;

public class Duke {

    /**
     * Method to overwrite save files whenever task list changes.
     * @param filePath the file destination.
     * @param textToWrite text to add in.
     * @throws IOException
     */
    private static void writeToFile(String filePath, String textToWrite) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToWrite);
        fw.close();
    }

    /**
     * Method to append to save file whenever task list changes.
     * @param filePath
     * @param textToAppend
     * @throws IOException
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    private static void rebuildFile(String filePath) throws IOException {
        writeToFile(filePath, "");
        ListIterator<Task> iterate = taskArrayList.listIterator();
        while (iterate.hasNext()) {
            appendToFile(filePath, iterate.next().toSave() + System.lineSeparator());
        }
    }

    /**
     * Method to load save files when Duke is initialised.
     * @param filePath the file destination.
     * @return
     * @throws FileNotFoundException
     */
    private static void loadFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        int order = 1;
        while (s.hasNext()) {
            commandBuilder(s.nextLine(), order);
            order++;
        }
    }

    private static void commandBuilder(String saveLine, int order) {
        int divideIndex;
        String command;
        String commandType = saveLine.substring(0, 1);
        int isDone = Integer.parseInt(saveLine.substring(4, 5));
        String commandDesc = saveLine.substring(8);

        switch(commandType) {
            case "T":
                try {
                    ToDo task = new ToDo(" " + commandDesc);
                    if (isDone == 1) { task.markDone(); }
                    taskArrayList.add(task);
                } catch (EmptyDescException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "D":
                try {
                    divideIndex = commandDesc.lastIndexOf("|");
                    String commandBy = commandDesc.substring(divideIndex + 2);
                    commandDesc = " " + commandDesc.substring(0 , divideIndex - 1);
                    Deadline task = new Deadline(commandDesc, commandBy);
                    if (isDone == 1) { task.markDone(); }
                    taskArrayList.add(task);
                } catch (EmptyDescException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "E":
                try {
                    divideIndex = commandDesc.lastIndexOf("|");
                    String commandBy = commandDesc.substring(divideIndex + 2);
                    commandDesc = " " + commandDesc.substring(0 , divideIndex - 1);
                    Event task = new Event(commandDesc, commandBy);
                    if (isDone == 1) { task.markDone(); }
                    taskArrayList.add(task);
                } catch (EmptyDescException e) {
                    System.out.println(e.getMessage());
                }
                break;
        }
        if (isDone == 1) {
            String markCommand = "mark " + isDone;
            markTask(taskArrayList, markCommand);
        }
    }

    /**
     * Method to execute "list" command.
     * Prints out the tasks stored in the array list.
     * @param taskArrayList the reference task list.
     */
    private static void printTaskArray(ArrayList<Task> taskArrayList) {
        ListIterator<Task> iterate = taskArrayList.listIterator();
        System.out.println("Here are the tasks in your list:");
        int qty = 0;
        while (iterate.hasNext()) {
            qty++;
            System.out.println(qty + "." + iterate.next().toString());
        }
    }

    /**
     * Method to delete a task from the task list.
     * @param taskArrayList the task list.
     * @param act the command dictating which task to be deleted.
     * @return the updated taskArrayList.
     */
    private static ArrayList<Task> deleteTask(ArrayList<Task> taskArrayList, String act) {
        String numberOnly = act.replaceAll("[^0-9]", "");
        int index = Integer.parseInt(numberOnly);
        Task deleted = taskArrayList.get(index-1);
        taskArrayList.remove(index-1);
        System.out.println("Noted. I've removed this task:\n"
                + deleted.toString() + "\n"
                + "Now you have " + taskArrayList.size() + " tasks in the list");
        try {
            rebuildFile(path2);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage() + "");
        }
        return taskArrayList;
    }

    /**
     * Method to mark a task as done.
     * @param taskArrayList the task list.
     * @param act the command dictating which task to be marked.
     * @return the modified taskArrayList.
     */
    private static ArrayList<Task> markTask(ArrayList<Task> taskArrayList, String act) {
        String numberOnly = act.replaceAll("[^0-9]", "");
        int index = Integer.parseInt(numberOnly);
        taskArrayList.get(index-1).markDone();
        System.out.println("Alright, this task is marked as done:\n"
                + taskArrayList.get(index-1).toString());
        try {
            rebuildFile(path2);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage() + "");
        }
        return taskArrayList;
    }

    /**
     * Method to unmark a task as not done yet.
     * @param taskArrayList the task list.
     * @param act the command dictating which task to be unmarked.
     * @return the modified taskArrayList.
     */
    private static ArrayList<Task> unmarkTask(ArrayList<Task> taskArrayList, String act) {
        String numberOnly = act.replaceAll("[^0-9]", "");
        int index = Integer.parseInt(numberOnly);
        taskArrayList.get(index-1).unmarkDone();
        System.out.println("Alright, this task is marked as not done yet:\n"
                + taskArrayList.get(index-1).toString());
        try {
            rebuildFile(path2);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage() + "");
        }
        return taskArrayList;
    }

    /**
     * Method to add a ToDo task.
     * @param taskArrayList the task list.
     * @param act the command specifying the task to be added.
     * @return the modified taskArrayList.
     */
    private static ArrayList<Task> toDoTask(ArrayList<Task> taskArrayList, String act) {
        act = act.replace("todo", "");
        try {
            ToDo temp = new ToDo(act);
            taskArrayList.add(temp);
            System.out.println("Got it. I've added this task:\n"
                    + temp.toString() + "\n"
                    + "Now you have " + taskArrayList.size() + " tasks in the list.");
            appendToFile(path2, temp.toSave() + System.lineSeparator());
        } catch (EmptyDescException ede) {
            System.out.println(ede.getMessage());
        } catch (IOException ioe) {
            System.out.println("Something went wrong: " + ioe.getMessage() + "");
        }
        return taskArrayList;
    }

    /**
     * Method to add a Deadline task.
     * @param taskArrayList the task list.
     * @param act the command specifying the task to be added.
     * @return the modified taskArrayList.
     */
    private static ArrayList<Task> deadlineTask(ArrayList<Task> taskArrayList, String act) {
        act = act.replace("deadline", "");
        try {
            int slash = act.lastIndexOf("/");
            Deadline temp = new Deadline(act.substring(0, slash - 1), act.substring(slash + 4));
            taskArrayList.add(temp);
            System.out.println("Got it. I've added this task:\n"
                    + temp.toString() + "\n"
                    + "Now you have " + taskArrayList.size() + " tasks in the list.");
            appendToFile(path2, temp.toSave() + System.lineSeparator() + "");
        } catch (EmptyDescException ede) {
            System.out.println(ede.toString());
        } catch (IOException ioe) {
            System.out.println("Something went wrong: " + ioe.getMessage());
        }
        return taskArrayList;
    }

    /**
     * Method to add a Event task.
     * @param taskArrayList the task list.
     * @param act the command specifying the task to be added.
     * @return the modified taskArrayList.
     */
    private static ArrayList<Task> eventTask(ArrayList<Task> taskArrayList, String act) {
        act = act.replace("event", "");
        try {
            int slash = act.lastIndexOf("/");
            // use -1
            Event temp = new Event(act.substring(0, slash - 1), act.substring(slash + 4));
            taskArrayList.add(temp);
            System.out.println("Got it. I've added this task:\n"
                    + temp.toString() + "\n"
                    + "Now you have " + taskArrayList.size() + " tasks in the list.");
            appendToFile(path2, temp.toSave() + System.lineSeparator() + "");
        } catch (EmptyDescException ede) {
            System.out.println(ede.toString());
        } catch (IOException ioe) {
            System.out.println("Something went wrong: " + ioe.getMessage());
        }
        return taskArrayList;
    }

    static String path1 = "data";
    static String path2 = "data/duke.txt";
    static File data1 = new File(path1);
    static File data2 = new File(path2);
    static Scanner scanObj = new Scanner(System.in);
    static ArrayList<Task> taskArrayList = new ArrayList<>();

    public Duke() {
        data1.mkdirs();
        try {
            boolean result = data2.createNewFile();
            if (result) {
                System.out.println("file created.");
            } else {
                System.out.println("existing file detected.");
                loadFileContents(path2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Duke gustavoBot = new Duke();

        String intro = "Hello! My name is GustavoBot, but you can call me Gus\n"
                + "How may I help you today?";

        System.out.println(intro);

        while (true) {
            String act = scanObj.nextLine();                                        // read user input

            if (act.equals("list")) {
                printTaskArray(taskArrayList);
            } else if (act.contains("delete")) {
                taskArrayList = deleteTask(taskArrayList, act);
            } else if (act.contains("unmark")) {
                taskArrayList = unmarkTask(taskArrayList, act);
            } else if (act.contains("mark")) {
                taskArrayList = markTask(taskArrayList, act);
            } else if (act.contains("todo")) {
                taskArrayList = toDoTask(taskArrayList, act);
            } else if (act.contains("deadline")) {
                taskArrayList = deadlineTask(taskArrayList, act);
            } else if (act.contains("event")) {
                taskArrayList = eventTask(taskArrayList, act);
            } else if (act.equals("bye")) {
                System.out.println("Goodbye, hope to see you again soon!");
                break;
            } else {
                UnknownInputException uie = new UnknownInputException("?");
                System.out.println(uie.toString());
            }
        }
    }
}
