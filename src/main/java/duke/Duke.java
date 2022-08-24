package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * MakiBot
 */
public class Duke {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("dd/MM/yyyy ")
            .optionalStart()
            .appendPattern("HH:mm ")
            .optionalEnd()
            .appendZoneText(TextStyle.SHORT)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();
    private enum COMMAND {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;
    }
    protected ArrayList<Task> taskList = new ArrayList<>();
    protected String saveFilePath = "data.txt";
    protected ZoneId timeZone = ZoneId.of("GMT+00:00");

    protected Duke() {
    }

    protected Duke(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    public static void main(String[] args) {
        System.out.println("Hello from\n" + "MAKIBOT");
        if (args.length > 0) {
            new Duke(args[0]).start();
        } else {
            new Duke().start();
        }
    }

    protected void start() {
        System.out.println("Hello! I'm MAKIBOT");
        this.getTimeZone();
        this.loadTasks();
        System.out.println("Welcome! What can I do for you?");
        this.eventLoop();
    }

    /**
     * Start a conversation with MakiBot
     */
    protected void eventLoop() {
        Scanner sc = new Scanner(System.in);
        try {
            // duke.Event loop
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] fullCommand = input.split(" ", 2);
                COMMAND command = COMMAND.valueOf(fullCommand[0].toUpperCase());

                // Handle commands
                switch (command) {
                // Exit command
                case BYE:
                    this.bye();
                    sc.close();
                    return;
                // List all tasks
                case LIST:
                    this.listTasks();
                    break;
                // Mark task as done
                case MARK:
                    this.mark(fullCommand);
                    this.updateSaveFile();
                    break;
                // Mark task as undone
                case UNMARK:
                    this.unmark(fullCommand);
                    this.updateSaveFile();
                    break;
                case DELETE:
                    this.delete(fullCommand);
                    this.updateSaveFile();
                    break;
                case TODO:
                    this.newTodo(fullCommand);
                    this.updateSaveFile();
                    break;
                case DEADLINE:
                    this.newDeadline(fullCommand);
                    this.updateSaveFile();
                    break;
                case EVENT:
                    this.newEvent(fullCommand);
                    this.updateSaveFile();
                    break;
                default:
                    throw new DukeInvalidCommandException();
                }
            }
        } catch (DukeException de) {
            System.out.println(de.getMessage());
            this.eventLoop();
        }
        sc.close();
    }

    protected void printNewTaskMessage(Task t) {
        System.out.printf("Got it. I've added this task:\n" +
                        "\t%s\n" +
                        "Now you have %d tasks in the list.%n",
                t, taskList.size());
    }

    protected void loadTasks() {
        try {
            File saveFile = new File(this.saveFilePath);
            saveFile.createNewFile();
            Scanner saveSc = new Scanner(saveFile);

            while (saveSc.hasNextLine()) {
                String[] dataArr = saveSc.nextLine().split(" \\| ");
                char taskType = dataArr[0].charAt(0);
                boolean isDone = Boolean.parseBoolean(dataArr[1]);
                String taskDescription = dataArr[2];
                Task newTask;

                if (taskType == 'D') {
                    newTask = new Deadline(taskDescription,
                            ZonedDateTime.parse(dataArr[3]).withZoneSameInstant(this.timeZone));
                } else if (taskType == 'E') {
                    newTask = new Event(taskDescription,
                            ZonedDateTime.parse(dataArr[3]).withZoneSameInstant(this.timeZone));
                } else if (taskType == 'T') {
                    newTask = new Todo(taskDescription);
                } else {
                    System.out.println("The following task could not be loaded from memory:\n"
                            + Arrays.toString(dataArr));
                    continue;
                }

                if (isDone) {
                    newTask.markAsDone();
                }

                taskList.add(newTask);
            }

            System.out.println("Tasks successfully loaded!");
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred: " + e + "\nAborting...");
            return;
        }
    }

    protected void updateSaveFile() {
        try {
            FileWriter saveFileWriter = new FileWriter("data.txt");
            taskList.forEach(task -> {
                String saveMsg = String.format("%c | %s | %s", task.getType(), task.isDone, task.description);
                if (task instanceof Deadline) {
                    saveMsg += " | " + ((Deadline) task).by;
                } else if (task instanceof Event) {
                    saveMsg += " | " + ((Event) task).at;
                }
                try {
                    saveFileWriter.write(saveMsg + "\n");
                } catch (IOException e) {
                    System.out.println("An error occurred while saving your tasks.");
                    e.printStackTrace();
                }
            });
            saveFileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving your tasks.");
            e.printStackTrace();
        }
    }

    protected void getTimeZone() {
        Scanner sc = new Scanner(System.in);
        System.out.println("You are currently in timezone: " + this.timeZone +
                "\nWould you like to change your timezone? Y/N");

        if (sc.hasNextLine() && sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.println("What is your timezone relative to GMT? (+/-HH:mm)");
            try {
                this.timeZone = ZoneId.of("GMT" + sc.nextLine());
                System.out.println("Your timezone is now " + this.timeZone);
            } catch (DateTimeException e) {
                System.out.println("☹ OOPS!!! I don't understand that timezone.");
                this.getTimeZone();
            }
        }
    }

    protected void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    protected void listTasks() {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks at the moment!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + 1 + ". " + taskList.get(i));
            }
        }
    }

    protected void mark(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("mark");
        }

        if (taskList.isEmpty()) {
            throw new DukeIndexErrorException();
        }

        try {
            int taskNum = Integer.parseInt(fullCommand[1]) - 1;
            Task t = taskList.get(taskNum);
            t.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + t);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeIndexErrorException(taskList.size());
        }
    }

    protected void unmark(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("unmark");
        }

        if (taskList.isEmpty()) {
            throw new DukeIndexErrorException();
        }

        try {
            int taskNum = Integer.parseInt(fullCommand[1]) - 1;
            Task t = taskList.get(taskNum);
            t.markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:\n" + t);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeIndexErrorException(taskList.size());
        }
    }

    protected void delete(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("delete");
        }

        if (taskList.isEmpty()) {
            throw new DukeIndexErrorException();
        }

        try {
            int taskNum = Integer.parseInt(fullCommand[1]) - 1;
            Task t = taskList.remove(taskNum);
            System.out.printf("Noted. I've removed this task:\n" +
                            "\t%s\n" +
                            "Now you have %d tasks in the list.%n",
                    t, taskList.size());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeIndexErrorException(taskList.size());
        }
    }

    protected void newTodo(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("todo");
        }

        Todo td = new Todo(fullCommand[1]);
        taskList.add(td);
        printNewTaskMessage(td);
    }

    protected void newDeadline(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("deadline");
        }

        try {
            String[] newDeadline = fullCommand[1].split(" /by ", 2);
            System.out.println(newDeadline[1]);
            ZonedDateTime by = ZonedDateTime.parse(newDeadline[1] + " " + this.timeZone, DATE_TIME_FORMATTER);
            Deadline dl = new Deadline(newDeadline[0], by);
            taskList.add(dl);
            printNewTaskMessage(dl);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeFormatCommandException("deadline", "/by");
        }
    }

    protected void newEvent(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("event");
        }

        try {
            String[] newEvent = fullCommand[1].split(" /at ", 2);
            ZonedDateTime at = ZonedDateTime.parse(newEvent[1] + " " + this.timeZone, DATE_TIME_FORMATTER);
            Event ev = new Event(newEvent[0], at);
            taskList.add(ev);
            printNewTaskMessage(ev);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeFormatCommandException("event", "/at");
        }
    }
}
