import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/** TODOs
 * update file for mark/unmark
 * update file for delete
 */

public class Duke {
    protected static boolean terminate = false;
    protected static ArrayList<Task> taskList = new ArrayList<Task>();
    // protected static HashMap<LocalDate, ArrayList<Task>> dateToTasks = new HashMap<>();

    public static void main(String[] args) {
        // Load file from hard disk
        File hardDiskTasks = new File("data/duke.txt");
        File tempTasks = new File("data/temp.txt");
        try {
           hardDiskTasks.createNewFile();
           tempTasks.createNewFile();
        } catch (IOException | SecurityException e) {
            System.out.println("     " + e.getMessage());
        }

        // Add disk info to taskList
        Duke.loadTasksFromDisk(hardDiskTasks);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.greet();

        // Listening to inputs from user
        Scanner in = new Scanner(System.in);
        while (!terminate) {
            try {
                String userInput = in.nextLine();
                // retrieve first word
                int indexOfFirstSpace = userInput.indexOf(" ");
                int indexOfFirstSlash = userInput.indexOf("/");
                String firstWord = userInput;
                String taskDescription = "";
                String time = "";

                // int to store info for numbered operations
                char charOfInt = '\0';
                if (indexOfFirstSpace != - 1) {
                    firstWord = userInput.substring(0, indexOfFirstSpace);
                    taskDescription = userInput.substring(indexOfFirstSpace + 1);

                    // retrieve int for numbered operations
                    charOfInt = userInput.charAt(userInput.length() - 1);
                }

                // retrieve task details
                if (indexOfFirstSlash != -1) {
                    taskDescription = userInput.substring(indexOfFirstSpace + 1, indexOfFirstSlash - 1);
                    time = userInput.substring(indexOfFirstSlash + 1);
                }

                // convert ASCII character of integer to int
                int taskNumber =  charOfInt - '0';

                // choose instruction to execute
                switch (firstWord) {
                case "bye":
                    Duke.exit();
                    break;
                case "unmark":
                    Duke.unmarkTask(taskNumber, taskDescription);
                    break;
                case "mark":
                    Duke.markTask(taskNumber, taskDescription);
                    break;
                case "list":
                    Duke.displayList();
                    break;
                case "deadline":
                    Duke.addDeadline(taskDescription, time);
                    break;
                case "todo":
                    Duke.addTodo(taskDescription);
                    break;
                case "event":
                    Duke.addEvent(taskDescription, time);
                    break;
                case "delete":
                    Duke.deleteTask(taskNumber, taskDescription);
                    break;
                case "":
                    throw new DukeException("OOPS!!! Please enter an instruction");
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                Duke.lineFormat();
                System.out.println("     " + e.getMessage());
                Duke.lineFormat();
            }
        }
        in.close();;
    }

    public static void loadTasksFromDisk(File file) {
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String memo = s.nextLine();

                // first letter to identify task
                String task = memo.substring(0, 1);

                // Retrieve task status
                int indexOfFirstBreak = memo.indexOf("|");
                String status = memo.substring(indexOfFirstBreak + 2, indexOfFirstBreak + 3);
                boolean statusIsDone = status.equals("1");

                // skip "| x | " to get task description
                String descriptionAndTime = memo.substring(indexOfFirstBreak + 6);
                int indexOfThirdBreak = descriptionAndTime.indexOf("|");
                String description = descriptionAndTime;
                String time = "";

                // if time exists, retrieve time and update task description
                if (indexOfThirdBreak != -1) {
                    description = descriptionAndTime.substring(0, indexOfThirdBreak - 1);
                    time = descriptionAndTime.substring(indexOfThirdBreak + 2);
                }

                // update list
                Duke.addTaskFromDisk(task, description, time, statusIsDone);
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("     " + e.getMessage());
        }

    }

    public static void addTaskFromDisk(String task, String description, String time, boolean isDone) {
        Task newTask = null;
        LocalDate date = null;
        if (time != "") {
            date = LocalDate.parse(time);
        }
        switch (task) {
        case "T":
            newTask = new ToDo(description);
            break;
        case "D":
            newTask = new Deadline(description, date);
            break;
        case "E":
            newTask = new Event(description, date);
            break;
        default:
            throw new DukeException("OOPS!!! The Disk memory is invalid");
        }
        newTask.setTaskStatus(isDone);
        taskList.add(newTask);
    }

    public static void lineFormat() {
        System.out.println("    ____________________________________________________________");
    }

    public static void greet() {
        Duke.lineFormat();
        System.out.println("     Hello! I'm Duke\n" +
                "     What can I do for you?\n");
        Duke.lineFormat();
    }

//    public static void addTask(Task task, LocalDate date) {
//        taskList.add(task);
//        if (!dateToTasks.containsKey(date)) {
//            dateToTasks.put(date, new ArrayList<>());
//        }
//        dateToTasks.get(date).add(task);
//        Duke.lineFormat();
//        System.out.println("     Got it. I've added this task:\n" +
//                "       " + task.toString() + "\n" +
//                "     Now you have " + taskList.size() + " tasks in the list.");
//        Duke.lineFormat();
//    }

    public static void appendToFile(String filePath, String textToAdd) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println("     " + e.getMessage());
        }
    }

    public static void addTask(Task task) {
        taskList.add(task);
        appendToFile("data/duke.txt", task.taskMemo() + System.lineSeparator());
        Duke.lineFormat();
        System.out.println("     Got it. I've added this task:\n" +
                "       " + task.toString() + "\n" +
                "     Now you have " + taskList.size() + " tasks in the list.");
        Duke.lineFormat();
    }

    public static void deleteTaskFromDisk(int taskNumber) {
        File inputFile = new File("data/duke.txt");
        File tempFile = new File("data/temp.txt");
        try {
            Scanner s = new Scanner(inputFile);
            while (s.hasNext()) {
                String currentLine = s.nextLine();
                if (taskNumber != 1) {
                    appendToFile("data/temp.txt", currentLine + System.lineSeparator());
                }
                taskNumber -= 1;
            }
            s.close();
            boolean successful = tempFile.renameTo(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("     " + e.getMessage());
        }
    }

    public static void deleteTask(int taskNumber, String description) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The task number for delete cannot be empty.");
        }
        if (taskNumber > taskList.size()) {
            throw new DukeException("OOPS!!! Please enter a valid task number");
        }
        deleteTaskFromDisk(taskNumber);
        Task removedTask = taskList.remove(taskNumber - 1);
        System.out.println("     Noted. I've removed this task:\n" +
                "       " + removedTask.toString() + "\n" +
                "     Now you have " + taskList.size() + " tasks in the list.");
        Duke.lineFormat();
    }

    public static void addTodo(String description) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        Task task = new ToDo(description);
        Duke.addTask(task);
    }

    public static void addEvent(String description, String at) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        }
        try {
            int indexOfDate = at.indexOf(" ") + 1;
            String date = at.substring(indexOfDate);
            LocalDate d1 = LocalDate.parse(date);
            Task task = new Event(description, d1);
            Duke.addTask(task);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            System.out.println("     OOPS!!! Please enter a valid date format (/at yyyy-mm-dd)");
        }
    }

    public static void addDeadline(String description, String by) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        try {
            int indexOfDate = by.indexOf(" ") + 1;
            String date = by.substring(indexOfDate);
            LocalDate d1 = LocalDate.parse(date);
            Task task = new Deadline(description, d1);
            Duke.addTask(task);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            System.out.println("     OOPS!!! Please enter a valid date format (/by yyyy-mm-dd)");
        }
    }

    public static void displayList() {
        Duke.lineFormat();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task currentTask = taskList.get(i - 1);
            String taskDescription = currentTask.toString();
            System.out.println("     " +
                    String.valueOf(i) + "." +
                    taskDescription);
        }
        Duke.lineFormat();
    }

    public static void markTask(int taskNumber, String description) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The task number for mark cannot be empty.");
        }
        if (taskNumber > taskList.size()) {
            throw new DukeException("OOPS!!! Please enter a valid task number");
        }
        Task currentTask = taskList.get(taskNumber - 1);
        currentTask.setTaskStatus(true);
        Duke.setTaskStatusOnDisk(taskNumber, true);
        String taskDescription = currentTask.toString();
        Duke.lineFormat();
        System.out.println("     Nice! I've marked this task as done:\n" +
                "       " + taskDescription);
        Duke.lineFormat();
    }

    public static void unmarkTask(int taskNumber, String description) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The task number for unmark cannot be empty.");
        }
        if (taskNumber > taskList.size()) {
            throw new DukeException("OOPS!!! Please enter a valid task number");
        }
        Task currentTask = taskList.get(taskNumber - 1);
        currentTask.setTaskStatus(false);
        Duke.setTaskStatusOnDisk(taskNumber, false);
        String taskDescription = currentTask.toString();
        Duke.lineFormat();
        System.out.println("     OK, I've marked this task as not done yet:\n" +
                "       " + taskDescription);
        Duke.lineFormat();
    }

//    public static void displayTasksOnDate(String time) {
//        try {
//            int indexOfDate = time.indexOf(" ") + 1;
//            String date = time.substring(indexOfDate);
//            LocalDate d1 = LocalDate.parse(date);
//
//        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
//            System.out.println("     OOPS!!! Please enter a valid date format (/by yyyy-mm-dd)");
//        }
//    }
    public static void setTaskStatusOnDisk (int taskNumber, boolean isDone) {
        File inputFile = new File("data/duke.txt");
        File tempFile = new File("data/temp.txt");
        try {
            Scanner s = new Scanner(inputFile);
            while (s.hasNext()) {
                String currentLine = s.nextLine();
                if (taskNumber == 1) {
                    // before status "x | description"
                    int indexOfFirstBreak = currentLine.indexOf("|");
                    String beforeStatus = currentLine.substring(0, indexOfFirstBreak + 2);

                    // after " X | x"
                    String afterStatus = currentLine.substring(indexOfFirstBreak + 3);
                    String status = isDone ? "1" : "0";
                    currentLine = beforeStatus + status + afterStatus;
                }
                appendToFile("data/temp.txt", currentLine + System.lineSeparator());
                taskNumber -= 1;
            }
            s.close();
            boolean successful = tempFile.renameTo(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("     " + e.getMessage());
        }
    }

    public static void exit() {
        Duke.terminate = true;
        Duke.lineFormat();
        System.out.println("     Bye. Hope to see you again soon!");
        Duke.lineFormat();
    }
}