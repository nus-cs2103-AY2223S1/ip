import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
public class Duke {
    protected static boolean terminate = false;
    protected static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        // Load file from hard disk
        File hardDiskTasks = new File("data/duke.txt");
        try {
           hardDiskTasks.createNewFile();
        } catch (IOException e) {
            System.out.println("     " + e.getMessage());
        } catch (SecurityException e) {
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
                    Duke.removeTask(taskNumber, taskDescription);
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
                boolean statusIsDone = status == "1";

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
        } catch (FileNotFoundException e) {
            System.out.println("     " + e.getMessage());
        }

    }

    public static void addTaskFromDisk(String task, String description, String time, boolean isDone) {
        Task newTask = null;
        if (task.equals("T")) {
            newTask = new ToDo(description);
        } else if (task.equals("D")) {
            newTask = new Deadline(description, time);
        } else if (task.equals("E")) {
            newTask = new Event(description, time);
        } else {
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

    public static void removeTask(int taskNumber, String description) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The task number for delete cannot be empty.");
        }
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
        if (at.equals("")) {
            throw new DukeException("OOPS!!! The time of a event cannot be empty.");
        }
        Task task = new Event(description, at);
        Duke.addTask(task);
    }

    public static void addDeadline(String description, String by) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        if (by.equals("")) {
            throw new DukeException("OOPS!!! The time of a deadline cannot be empty.");
        }
        Task task = new Deadline(description, by);
        Duke.addTask(task);
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
        System.out.println(taskList.size());
        Task currentTask = taskList.get(taskNumber - 1);
        currentTask.setTaskStatus(true);
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
        Task currentTask = taskList.get(taskNumber - 1);
        currentTask.setTaskStatus(false);
        String taskDescription = currentTask.toString();
        Duke.lineFormat();
        System.out.println("     OK, I've marked this task as not done yet:\n" +
                "       " + taskDescription);
        Duke.lineFormat();
    }

    public static void exit() {
        Duke.terminate = true;
        Duke.lineFormat();
        System.out.println("     Bye. Hope to see you again soon!");
        Duke.lineFormat();
    }
}