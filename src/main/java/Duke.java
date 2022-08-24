import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * MakiBot
 */
public class Duke {
    protected ArrayList<Task> taskList = new ArrayList<Task>();
    protected String saveFilePath = "data.txt";

    protected Duke() {
    };

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
                    newTask = new Deadline(taskDescription, dataArr[3]);
                } else if (taskType == 'E') {
                    newTask = new Event(taskDescription, dataArr[3]);
                } else if (taskType == 'T') {
                    newTask = new Todo(taskDescription);
                } else {
                    System.out.println("The following task could not be loaded from memory:\n" + dataArr);
                    continue;
                }

                if (isDone) {
                    newTask.markAsDone();
                }

                taskList.add(newTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occured: " + e + "\nAborting...");
            return;
        }

        System.out.println("Hello! I'm Makibot\n" +
                "What can I do for you?");
        this.eventLoop();
    }

    /**
     * Start a conversation with MakiBot
     */
    protected void eventLoop() {
        Scanner sc = new Scanner(System.in);
        try {
            // Event loop
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] fullCommand = input.split(" ", 2);
                String command = fullCommand[0];

                // Handle special commands
                switch (command) {
                    // Exit command
                    case "bye":
                        this.bye();
                        sc.close();
                        return;
                    // List all tasks
                    case "list":
                        this.listTasks();
                        break;
                    // Mark task as done
                    case "mark":
                        this.mark(fullCommand);
                        this.updateSaveFile();
                        break;
                    // Mark task as undone
                    case "unmark":
                        this.unmark(fullCommand);
                        this.updateSaveFile();
                        break;
                    case "delete":
                        this.delete(fullCommand);
                        this.updateSaveFile();
                        break;
                    case "todo":
                        this.newTodo(fullCommand);
                        this.updateSaveFile();
                        break;
                    case "deadline":
                        this.newDeadline(fullCommand);
                        this.updateSaveFile();
                        break;
                    case "event":
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
        System.out.println(String.format("Got it. I've added this task:\n" +
                        "\t%s\n" +
                        "Now you have %d tasks in the list.",
                t, taskList.size())
        );
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
                    return;
                }
            });
            saveFileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving your tasks.");
            e.printStackTrace();
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
            throw new DukeEmptyCommandException("mark");
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
            throw new DukeEmptyCommandException("unmark");
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
            throw new DukeEmptyCommandException("delete");
        }

        if (taskList.isEmpty()) {
            throw new DukeIndexErrorException();
        }

        try {
            int taskNum = Integer.parseInt(fullCommand[1]) - 1;
            Task t = taskList.remove(taskNum);
            System.out.println(String.format("Noted. I've removed this task:\n" +
                            "\t%s\n" +
                            "Now you have %d tasks in the list.",
                    t, taskList.size())
            );
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeIndexErrorException(taskList.size());
        }
    }

    protected void newTodo(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeEmptyCommandException("todo");
        }

        Todo td = new Todo(fullCommand[1]);
        taskList.add(td);
        printNewTaskMessage(td);
    }

    protected void newDeadline(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeEmptyCommandException("deadline");
        }

        try {
            String[] newDeadline = fullCommand[1].split(" /by ", 2);
            Deadline dl = new Deadline(newDeadline[0], newDeadline[1]);
            taskList.add(dl);
            printNewTaskMessage(dl);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeEmptyCommandException("deadline", "/by");
        }
    }

    protected void newEvent(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeEmptyCommandException("event");
        }

        try {
            String[] newEvent = fullCommand[1].split(" /at ", 2);
            Event ev = new Event(newEvent[0], newEvent[1]);
            taskList.add(ev);
            printNewTaskMessage(ev);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeEmptyCommandException("event", "/at");
        }
    }
}
