import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Duke is a basic chat-bot that echoes whatever the user inputs.
 *
 * @author Chi Song Yi Amadeus
 * @version Level-8
 * @since 17-08-2022
 */
public class Duke {

    /**
     * Array used to store user inputs as a Task Object.
     */
    public static ArrayList<Task> taskArray = new ArrayList<>();

    /**
     * Main method initializes welcome message, and then calls taskList method.
     *
     * @param args unused.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String initMessage =
                "    ____________________________________________________________\n" +
                        "     Hello! I'm Duke\n" +
                        "     I can store a to-do list for you!\n" +
                        "     Enter 'list' to display current contents and 'bye' to end the program.\n" +
                        "     Start entering your tasks!\n" +
                        "    ____________________________________________________________";

        System.out.println(initMessage);
        initData();
        taskList();
    }

    /**
     * initData checks for existence of file, attempts to read and process the data if it exists,
     * creates a new file if it does not.
     */
    public static void initData() {
        File data = new File("./data/duke.txt");
        try {
            if (!data.createNewFile()) {
                // If file exists, read from it
                createObject(data);
            }
        } catch (IOException e) {
            System.out.println("Error reading from data!");
        }
    }

    /**
     * creates a Task object from given File data.
     *
     * @param data file data to read from.
     */
    public static void createObject(File data) {
        try {
            Scanner s = new Scanner(data);
            while (s.hasNextLine()) {
                String entry = s.nextLine();
                String[] inputs = entry.split("\\|", 4);
                for (int i = 0; i < inputs.length; i++) {
                    inputs[i] = inputs[i].trim();
                }
                boolean completed = Objects.equals(inputs[1], "1");
                if (Objects.equals(inputs[0], "T")) {
                    ToDo newToDo = new ToDo(inputs[2], true, completed);
                    taskArray.add(newToDo);
                } else {
                    if (Objects.equals(inputs[0], "D")) {
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime formattedDate = LocalDateTime.parse(inputs[3], format);
                        Deadlines newDeadline = new Deadlines(inputs[2], true, completed, formattedDate);
                        taskArray.add(newDeadline);
                    } else if (Objects.equals(inputs[0], "E")) {
                        Events newEvent = new Events(inputs[2], true, completed, inputs[3]);
                        taskArray.add(newEvent);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    /**
     * parses and writes content of Task to the data file.
     *
     * @param input given Task to write to file.
     */
    public static void writeToFile(Task input) {
        try {
            FileWriter writer = new FileWriter("data/duke.txt", true);
            String taskType = input.taskType();
            String completed = input.isCompleted() ? "1" : "0";
            String name = input.getTaskName();
            if (Objects.equals(taskType, "T")) {
                String str = String.format("%s | %s | %s \n", taskType, completed, name);
                writer.write(str);
            } else {
                String date = input.getDate();
                String str = String.format("%s | %s | %s | %s\n", taskType, completed, name, date);
                writer.write(str);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to data!");
        }
    }

    /**
     * rewrites the entire data file with everyone item in current Array.
     */
    public static void rewriteFile() {
        try {
            FileWriter writer = new FileWriter("data/duke.txt", false);

            for (Task task : taskArray) {
                String taskType = task.taskType();
                String completed = task.isCompleted() ? "1" : "0";
                String name = task.getTaskName();
                if (Objects.equals(taskType, "T")) {
                    String str = String.format("%s | %s | %s \n", taskType, completed, name);
                    writer.write(str);
                } else {
                    String date = task.getDate();
                    String str = String.format("%s | %s | %s | %s\n", taskType, completed, name, date);
                    writer.write(str);
                }
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("Error writing to data!");
        }
    }

    /**
     * Function prints all items in the list chronologically entered and numbered from 1 upwards.
     */
    public static void readList() {
        System.out.println("    ____________________________________________________________\n");

        for (int i = 1; i <= taskArray.size(); i++) {
            String index = String.format("%d.", i);
            System.out.println(index + taskArray.get(i - 1).toString());
        }

        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * taskList method creates an input loop, creating Task objects and adding it to the array.
     *
     * @throws NumberFormatException if User inputs a non integer after mark/unmark
     */
    public static void taskList() throws NumberFormatException {
        Scanner userInput = new Scanner(System.in);

        while (userInput.hasNext()) {
            String input = userInput.nextLine();
            if (Objects.equals(input.toLowerCase(), "bye")) {
                break;
            } else if (Objects.equals(input.toLowerCase(), "list")) {
                // readList function called to display list contents
                readList();
            } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                String[] command = input.split(" ", 2);
                String action = command[0];
                String number = command[1];
                try {
                    int num = Integer.parseInt(number);
                    markTasks(action, num);
                    rewriteFile();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task! Please input a number!");
                }
            } else if (input.startsWith("delete")) {
                String[] command = input.split(" ", 2);
                String number = command[1];
                try {
                    int num = Integer.parseInt(number);
                    delete(num - 1);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task! Please input a number!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please Include a valid index!");
                }
            } else {
                try {
                    createTask(input, false);
                } catch (DukeException.EmptyTaskException | DukeException.UnkownCommandException error) {
                    System.out.println(error.getMessage());
                }
            }
        }
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________\n");
    }

    /**
     * markTasks applies the required action on the correct task ID.
     *
     * @param action to indicate mark/unmark
     * @param index  to indicate which task to apply action to
     */
    public static void markTasks(String action, int index) {
        if (index > taskArray.size() || index < 1) {
            System.out.println("Invalid task ID!");
        } else if (Objects.equals(action, "mark")) {
            taskArray.get(index - 1).mark();
        } else {
            taskArray.get(index - 1).unMark();
        }
    }

    /**
     * removes task from array, and removes entry from data file.
     *
     * @param index id of the task to be deleted.
     */
    public static void delete(int index) {
        Task deletable = taskArray.get(index);
        taskArray.remove(deletable);
        rewriteFile();
        System.out.printf(
                "    ____________________________________________________________\n" +
                        "     Noted. I've removed this task:\n" +
                        "       %s\n" +
                        "     Now you have %d tasks in the list.\n" +
                        "    ____________________________________________________________\n", deletable, taskArray.size());
    }

    /**
     * createTask handles task of child type ToDo, Deadlines and Events.
     *
     * @param input user input.
     * @throws ArrayIndexOutOfBoundsException       used to handle invalid inputs.
     * @throws DukeException.EmptyTaskException     Thrown when todo task is empty.
     * @throws DukeException.UnkownCommandException Thrown when command is unknown.
     */
    public static void createTask(String input, boolean init)
            throws ArrayIndexOutOfBoundsException, DukeException.EmptyTaskException, DukeException.UnkownCommandException {

        String[] commands = input.split("/", 2);
        String[] inputArr = commands[0].split(" ", 2);

        if (Objects.equals(inputArr[0], "todo")) {
            try {
                if (!(inputArr[1].trim().length() > 0)) {
                    throw new DukeException.EmptyTaskException("OOPS! please include a name for your task!");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException.EmptyTaskException("OOPS! please include a name for your task!");
            }
            ToDo newToDo = new ToDo(inputArr[1], init, false);
            taskArray.add(newToDo);
            writeToFile(newToDo);
        } else {
            if (Objects.equals(inputArr[0], "deadline") || Objects.equals(inputArr[0], "event")) {
                try {
                    String[] date = commands[1].split(" ", 2);
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime formattedDate = LocalDateTime.parse(date[1], format);

                    if (Objects.equals(inputArr[0], "deadline")) {
                        if (Objects.equals(date[0].toLowerCase(), "by")) {
                            Deadlines newDeadline = new Deadlines(inputArr[1], init, false, formattedDate);
                            taskArray.add(newDeadline);
                            writeToFile(newDeadline);
                        } else {
                            System.out.println("Include '/by' followed by a date after!");
                        }
                    } else {
                        if (Objects.equals(date[0].toLowerCase(), "at")) {
                            Events newEvent = new Events(inputArr[1], init, false, date[1]);
                            taskArray.add(newEvent);
                            writeToFile(newEvent);
                        } else {
                            System.out.println("Include '/at' followed by a date after!");
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("deadline/event requires date as a third parameter after /by or /at respectively!");
                } catch (DateTimeParseException e) {
                    System.out.println("Please input in date time format 'yyyy-MM-dd HH:mm'");
                }
            } else {
                throw new DukeException.UnkownCommandException("OOPS! Indicate todo/deadline/event before a task");
            }
        }
    }
}
