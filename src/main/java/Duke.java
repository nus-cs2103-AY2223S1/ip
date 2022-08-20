import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import java.nio.file.Path;

public class Duke {
    private Ui ui;
    private Storage storage;
    private List<Task> taskArr;

    
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("storage/tasks.txt");
        try {
            taskArr = this.storage.load();
        } catch(FileNotFoundException fnfe) {
            ui.showLoadingError();
            taskArr = new ArrayList<>();
        }
    }
    
    public void listTasks() {
        System.out.println("Here are your tasks: ");
        System.out.println("===============================");
        int len = this.taskArr.size();
        if (len == 0) {
            System.out.println("       YOU HAVE NO TASKS");
        } else {
            for (int i = 0; i < len; i++) {
                Task task = this.taskArr.get(i);
                System.out.printf("   %d.%s%n", i + 1, task);
            }
        }
        System.out.println("===============================");
    }
    

    public void addTask(Command command, String details) throws DukeException, IOException {
        if (details.length() == 0) {
            throw new MissingArgumentException(command);
        }
        Task task;
        switch (command) {
            case TODO:
                task = new Todo(details);
                this.taskArr.add(task);
                break;
            case DEADLINE:
                String[] detailsArr = details.split( " /by ", 2);
                if (detailsArr.length < 2) { // Missing date
                    throw new MissingArgumentException(command);
                }
                task = new Deadline(detailsArr[0], detailsArr[1]);
                this.taskArr.add(task);
                break;
            default: // EVENT
                detailsArr = details.split( " /at ", 2);
                if (detailsArr.length < 2) { // Missing date
                    throw new MissingArgumentException(command);
                }
                task = new Event(detailsArr[0], detailsArr[1]);
                this.taskArr.add(task);
                break;
        }
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println("Now, you have " + this.taskArr.size() + " tasks in the list");
        
    }

    public void markTaskAsDone(int taskIndex) {
        Task task = this.taskArr.get(taskIndex);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("   " + task);
    }

    public void unmarkTaskAsDone(int taskIndex) {
        Task task = this.taskArr.get(taskIndex);
        task.unmarkAsDone();
        System.out.println("Sure! I've marked this task as not yet done: ");
        System.out.println("   " + task);
    }
    
    public void deleteTask(int taskIndex) {
        Task task = this.taskArr.get(taskIndex);
        this.taskArr.remove(taskIndex);
        System.out.println("Swee! I've removed this task: ");
        System.out.println("   " + task);
        System.out.println("Now, you have " + this.taskArr.size() + " tasks in the list");
    }

    public void editTask(Command command, String details) throws DukeException {
        // missing task index
        if (details.length() == 0) {
            throw new MissingArgumentException(command);
        }
        try {
            int taskIndex = Integer.parseInt(details) - 1;

            // integer out of bounds
            if (taskIndex < 0 || taskIndex >= this.taskArr.size()) {
                throw new TaskIndexOutOfBoundsException(taskIndex + 1, this.taskArr.size());
            }
            switch (command) {
                case MARK:
                    this.markTaskAsDone(taskIndex);
                    break;
                case UNMARK:
                    this.unmarkTaskAsDone(taskIndex);
                    break;
                case DELETE:
                    this.deleteTask(taskIndex);
                    break;
            }
        } catch (NumberFormatException nfe) { // did not provide integer type as argument
            throw new InvalidArgumentException(command, details);
        }
    }

    public boolean readInput(String inputString) throws DukeException, IOException {
        String[] inputStringArr = inputString.split(" ", 2);
        String commandString = inputStringArr[0].strip();
        String detailsString = inputStringArr.length > 1 ? inputStringArr[1].strip() : "";

        // command (input) is all whitespace
        if (commandString.length() == 0) {
            return false;
        }
        
        // invalid command
        if (!Command.isValidCommand(commandString)) {
            throw new UnknownCommandException(commandString);
        }

        // valid commands
        Command command = Command.valueOf(commandString.toUpperCase());
        switch (command) {
            case BYE:
                this.ui.showGoodbye();
                return true;
            case LIST:
                this.listTasks();
                break;
            case MARK:
            case UNMARK:    
            case DELETE:
                this.editTask(command, detailsString);
                this.storage.save(taskArr);
                break;
            case TODO:
            case EVENT:
            case DEADLINE:
                this.addTask(command, detailsString);
                this.storage.save(taskArr);
                break;
        }
        return false;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        this.ui.showWelcome();

        boolean end = false;
     

        while (!end) {
            this.ui.showPrompt();
            String inputString = scanner.nextLine().strip();
            try {
                end = readInput(inputString);
            } catch (DukeException de) {
                this.ui.showError(de.getMessage());
            } catch (IOException ioe) {
                this.ui.showError(ioe.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}