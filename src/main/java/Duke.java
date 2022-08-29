import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    
    public static void main(String[] args) { 
        new Duke().start();
    }
    
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
    }
    
    private void start() {
        try {
            tasks = new TaskList(storage.loadFromDisk());
            serviceLoop();
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void serviceLoop() {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] words = parser.parseInput(input);
            String keyword = parser.getKeyword(input);
            
            try {
                if (keyword.equals("bye")) {
                    ui.bye();
                    return;
                } else if (keyword.equals("list")) {
                    ui.printTasks(tasks.getTaskList());
                } else if (keyword.equals("todo")) {
                    createToDos(words);
                } else if (keyword.equals("deadline")) {
                    createDeadlines(words);
                } else if (keyword.equals("event")) {
                    createEvents(words);
                } else if (keyword.equals("mark")) {
                    mark(words);
                } else if (keyword.equals("unmark")) {
                    unmark(words);
                } else if (keyword.equals("delete")) {
                    deleteTask(words);
                } else {
                    throw new DukeInvalidException();
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void mark(String[] currInput) throws DukeException {
        if (currInput.length < 2) {
            throw new DukeEmptyException(currInput[0]);
        }
        try {
            int taskIndex = parser.parseForNumber(currInput);
            System.out.println(tasks.getTask(taskIndex).markAsDone());
            storage.saveToDisk(tasks.getTaskList());
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(tasks.getSize());
        }
    }

    private void unmark(String[] currInput) throws DukeException {
        if (currInput.length < 2) {
            throw new DukeEmptyException(currInput[0]);
        }
        try {
            int taskIndex = parser.parseForNumber(currInput);
            System.out.println(tasks.getTask(taskIndex).unmarkAsNotDone());
            storage.saveToDisk(tasks.getTaskList());
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(tasks.getSize());
        }
    }

    private void createToDos(String[] currInput) throws DukeException {
        if (currInput.length < 2) {
            throw new DukeEmptyException(currInput[0]);
        }
        try {
            ToDos task = new ToDos(currInput[1]);
            tasks.addTask(task);
            ui.printTaskAdded(task, tasks.getSize());
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(tasks.getSize());
        }
    }

    private void createDeadlines(String[] currInput) throws DukeException {
        if (currInput.length < 2) {
            throw new DukeEmptyException(currInput[0]);
        }
        try {
            String[] taskDetails = currInput[1].split(" /by ", 2);
            String tasking = taskDetails[0];
            String deadline = taskDetails[1];
            LocalDateTime dateTime = LocalDateTime.parse(deadline, DATE_TIME_FORMATTER);
            Deadlines task = new Deadlines(tasking, dateTime);
            tasks.addTask(task);
            ui.printTaskAdded(task, tasks.getSize());
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(tasks.getSize());
        } catch (DateTimeException ex) {
            throw new DukeDateTimeException();
        }
    }

    private void createEvents(String[] currInput) throws DukeException {
        if (currInput.length < 2) {
            throw new DukeEmptyException(currInput[0]);
        }
        try {
            String[] taskDetails = currInput[1].split(" /at ", 2);
            String tasking = taskDetails[0];
            String eventTime = taskDetails[1];
            LocalDateTime dateTime = LocalDateTime.parse(eventTime, DATE_TIME_FORMATTER);
            Events task = new Events(tasking, dateTime);
            tasks.addTask(task);
            ui.printTaskAdded(task, tasks.getSize());
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(tasks.getSize());
        } catch (DateTimeException ex) {
            throw new DukeDateTimeException();
        }
    }

    private void deleteTask(String[] currInput) throws DukeException {
        if (currInput.length < 2) {
            throw new DukeEmptyException(currInput[0]);
        }
        try {
            int taskIndex = parser.parseForNumber(currInput);
            Task deletedTask = tasks.deleteTask(taskIndex);
            ui.printTaskDeleted(deletedTask, tasks.getSize());
            storage.saveToDisk(tasks.getTaskList());
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(tasks.getSize());
        }
    }
}
