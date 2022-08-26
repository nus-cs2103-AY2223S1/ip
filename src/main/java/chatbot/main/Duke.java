package chatbot.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

import chatbot.tasks.Task;
import chatbot.tasks.Event;
import chatbot.tasks.Deadline;
import chatbot.tasks.Todo;
import chatbot.tasks.TaskBucket;

/**
 * The Duke chatbot named Zlimez functions as a todo list
 * that helps users keep track of their upcoming tasks and
 * the completion status of each of them.
 *
 * @author James Chiu
 */
public class Duke {
    private static final String root = System.getProperty("user.dir");
    private static final Path dataPath = Paths.get(root, "data", "data.txt");
    private boolean isActive = true;
    private final String emoji = "<_>";
    private final Scanner reader = new Scanner(System.in);
    private List<Task> todos = new ArrayList<>();
    private HashMap<LocalDate, TaskBucket> taskByDates = new HashMap<>();

    /**
     * The enumerations serves to represent three possible actions
     * users can ask the chatbot to perform aside from adding tasks
     * to the todo list.
     */
    private enum Action {
        MARK (5),
        UNMARK (7),
        DELETE (7);

        private final int parseIndex;

        Action(int parseIndex) {
            this.parseIndex = parseIndex;
        }

        int process(String userInput) throws DukeException {
            try {
                return Integer.parseInt(userInput.substring(parseIndex));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Do tell me the index...");
            } catch (NumberFormatException e) {
                throw new DukeException("HELLO do you know index is a number");
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke bot = new Duke();
        bot.greet();
        while (bot.isActive) {
            try {
                bot.respond();
            } catch (DukeException e) {
                System.out.println("\t" + e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }

    public void saveData() throws IOException {
        File targetFile = dataPath.toFile();
        if (targetFile.getParentFile().mkdirs()) {
            targetFile.createNewFile();
        }

        FileWriter out = new FileWriter(targetFile);
        for (Task task : todos) {
            out.write(task.save() + "\n");
        }

        out.close();
    }

    public void initData() throws IOException, DukeException {
        Scanner in = new Scanner(dataPath);
        while (in.hasNextLine()) {
            String[] nextTaskInfo = in.nextLine().split(" \\| ");
            Task newTask;
            switch (nextTaskInfo[0]) {
                case "T":
                    newTask = new Todo(nextTaskInfo[2], nextTaskInfo[1].equals("1"));
                    todos.add(newTask);
                    break;
                case "D":
                    newTask = new Deadline(nextTaskInfo[2], nextTaskInfo[1].equals("1"), nextTaskInfo[3]);
                    todos.add(newTask);
                    LocalDate deadlineDate = ((Deadline) newTask).getDate();
                    bucketTasks(deadlineDate, newTask);
                    break;
                case "E":
                    newTask = new Event(nextTaskInfo[2], nextTaskInfo[1].equals("1"), nextTaskInfo[3]);
                    todos.add(newTask);
                    LocalDate eventDate = ((Event) newTask).getDate();
                    bucketTasks(eventDate, newTask);
                    break;
                default:
                    throw new DukeException("You have corrupted the data I saved");
            }
        }

        in.close();
    }

    private void bucketTasks(LocalDate date, Task task) {
        if (taskByDates.containsKey(date)) {
            taskByDates.get(date).addTask(task);
        } else {
            TaskBucket newBucket = new TaskBucket();
            newBucket.addTask(task);
            taskByDates.put(date, newBucket);
        }
    }

    /**
     * The method allows the chatbot to greet the user upon initialization.
     */
    public void greet() {
        System.out.print("Yes? I'm Zlimez~~ \nWhat can I possibly do for you?\n >>>^<<<\n\n");

        try {
            this.initData();
        } catch (DukeException e) {
            System.out.println("\t" + e.getMessage());
        } catch (IOException e) {
            // Do nothing data might not exist when app first used
        }
    }

    /**
     * The method determines the subsequent action performed by the chatbot
     * upon user input.
     * @throws DukeException The various exceptions that can be raised
     * when the chatbot is used.
     */
    public void respond() throws DukeException {
        String userInput = reader.nextLine();
        if (userInput.equals("bye")) {
            bye();
        } else if (userInput.startsWith("list")) {
            listTasks(userInput);
        } else if (userInput.startsWith("mark")) {
            markTask(Action.MARK.process(userInput));
        } else if (userInput.startsWith("unmark")) {
            unmarkTask(Action.UNMARK.process(userInput));
        } else if (userInput.startsWith("delete")) {
            deleteTask(Action.DELETE.process(userInput));
        } else {
            try {
                addTask(userInput);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Please your task lacks the necessary specifications");
            }
        }
    }

    /**
     * The method allows user to add different types of task to their todo list.
     *
     * @param task The String that should contain the information of the given task.
     * @throws DukeException if the input given does not correspond to a valid command.
     * @throws IndexOutOfBoundsException if the input is not correctly formatted .
     */
    private void addTask(String task) throws DukeException, IndexOutOfBoundsException {
        Task newTask;
        if (task.startsWith("todo")) {
            newTask = new Todo(task);
        } else if (task.startsWith("deadline")) {
            newTask = new Deadline(task);
            LocalDate date = ((Deadline) newTask).getDate();
            bucketTasks(date, newTask);

        } else if (task.startsWith("event")) {
            newTask= new Event(task);
            LocalDate date = ((Event) newTask).getDate();
            bucketTasks(date, newTask);

        } else {
            // Fallback should not occur
            throw new DukeException("Your command lacks the keyword for me to act upon");
        }

        System.out.println("\tLazily added this task for you " + emoji);
        todos.add(newTask);
        System.out.println("\t\t" + newTask);
        System.out.println("\tWala now you have " + todos.size() + " tasks in the list.");
    }

    private void getTaskOn(LocalDate date) {
        if (taskByDates.containsKey(date)) {
            System.out.println("\tThese are your tasks for that day");
            List<Task> tasksToday = taskByDates.get(date).getTasks();
            for (int i = 1; i <= tasksToday.size(); i++) {
                System.out.println("\t" + i + ". " + tasksToday.get(i - 1));
            }
        } else {
            System.out.println("\tWell you are a lazy bum, you have nothing on the day");
        }
    }

    /**
     * The method allows user to delete a given task from the todo list.
     *
     * @param index The index of the task to be deleted.
     * @throws DukeException If the index to be deleted does not exist.
     */
    private void deleteTask(int index) throws DukeException {
        try {
            Task removed = todos.remove(index - 1);
            System.out.println("\tYES, I've removed this task for YOU:");
            System.out.println("\t\t" + removed);
            System.out.println("\tWala now you have " + todos.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Read the index of the existing tasks carefully...");
        }
    }

    /**
     * The method lists out all the tasks in the todo list.
     */
    private void listTasks(String date) throws DukeException {
        System.out.println("\tReally? If you are so forgetful...");
        Scanner dateParser = new Scanner(date);
        dateParser.next();

        if (dateParser.hasNext()) {
            try {
                getTaskOn(LocalDate.parse(dateParser.next()));
            } catch (DateTimeParseException e) {
                throw new DukeException("Your date is rubbish");
            }
        } else {
            // List all tasks
            for (int i = 1; i <= todos.size(); i++) {
                System.out.println("\t" + i + ". " + todos.get(i - 1));
            }
        }
    }

    /**
     * The method allows user to mark a task as completed.
     *
     * @param index The task to be marked as completed.
     * @throws DukeException If no task exists at the provided index.
     */
    private void markTask(int index) throws DukeException {
        try {
            todos.get(index - 1).mark();
            System.out.println("\tWellz, I've marked this task for YOU:");
            System.out.println("\t\t" + todos.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Read the index of the existing tasks carefully...");
        }
    }

    /**
     * The method allows user to mark a task as incomplete.
     *
     * @param index The task to be marked as incomplete.
     * @throws DukeException If no task exists at the provided index.
     */
    private void unmarkTask(int index) throws DukeException {
        try {
            todos.get(index - 1).unmark();
            System.out.println("\t-_-, I've unmarked this task for YOU AGAIN:");
            System.out.println("\t\t" + todos.get(index - 1));
        } catch (NullPointerException e) {
            throw new DukeException("Read the index of the existing tasks carefully...");
        }
    }

    private void bye() throws DukeException {
        this.isActive = false;
        System.out.println("\tBye. zzz FINALLY~~" + " " + emoji);
        try {
            this.saveData();
        } catch (IOException e) {
            throw new DukeException("For some stupid reasons I cannot save the data, too bad");
        }

        reader.close();
    }
}
