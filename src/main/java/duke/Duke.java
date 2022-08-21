package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;

public class Duke {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    public Duke(String name) {
        this.tasks = new ArrayList<>(100);
        String fileName = "tasks.txt";

        Storage storage;
        try {
            storage = new Storage(fileName);
        } catch (DukeException e) {
            this.speak("Unable to save tasks to disk.");
            storage = null;
        }
        this.storage = storage;

        if (storage != null) {
            try {
                ArrayList<Task> tasksFromFile = this.storage.loadTasks();
                if (tasksFromFile.size() > 0) {
                    this.tasks.addAll(tasksFromFile);
                    this.speak("%d tasks loaded from file.", tasksFromFile.size());
                }
            } catch (DukeException e) {
                this.speak(e.getMessage());
            }
        }
        speak("Hello! I'm %s\nWhat do you need to do?", name);
    }

    public static void main(String[] args) {
        Duke duke = new Duke("Duke");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.next();
            String arguments = sc.nextLine();
            if (!duke.callback(command, arguments)) {
                break;
            }
        }
    }

    /**
     * Callback for all commands.
     *
     * @param command First word of user input
     * @param input   Remaining words of user input
     * @return Whether the program should continue running.
     */
    private boolean callback(String command, String input) {
        input = input.strip();
        try {
            try {
                switch (Commands.getCommand(command)) {
                    case BYE:
                        goodbye();
                        return false;
                    case LIST:
                        listHistory();
                        break;
                    case TODO:
                        addTodo(input);
                        break;
                    case EVENT:
                        addEvent(input);
                        break;
                    case DEADLINE:
                        addDeadline(input);
                        break;
                    case MARK:
                        setTaskCompletionStatus(input, true);
                        break;
                    case UNMARK:
                        setTaskCompletionStatus(input, false);
                        break;
                    case DELETE:
                        delete(input);
                        break;
                }
            } catch (IllegalArgumentException e) {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            speak(e.getMessage());
        }
        return true;
    }

    /**
     * Handle the {@literal todo} command
     *
     * @param input the input to be handled
     * @throws DukeException if the input is invalid
     */
    private void addTodo(String input) throws DukeException {
        ToDo todo = new ToDo(input);
        addTask(todo);
        speak("Got it. I've added this todo:\n  %s\nNow you have %d tasks in your list", todo, tasks.size());
    }

    private Task deleteTask(int idx) throws DukeException, IndexOutOfBoundsException {
        Task task = tasks.remove(idx);
        if (storage != null) storage.saveTasks(tasks);
        return task;
    }

    private void addTask(Task task) throws DukeException {
        tasks.add(task);
        if (storage != null) storage.saveTasks(tasks);
    }

    /**
     * Handle the event command
     *
     * @param input the input to be handled
     * @throws DukeException if the input is invalid
     */
    private void addEvent(String input) throws DukeException {
        if (input.matches("^.* /at .*$")) {
            String[] parts = input.split(" /at ");
            Event event = new Event(parts[0].strip(), parts[1].strip());
            addTask(event);
            speak("Got it. I've added this event:\n  %s\nNow you have %d tasks in your list", event, tasks.size());
        } else {
            throw new DukeException("Invalid event format");
        }
    }

    /**
     * Handle the deadline command
     *
     * @param input the input to be handled
     * @throws DukeException if the input is invalid
     */
    private void addDeadline(String input) throws DukeException {
        if (input.matches("^.* /by .*$")) {
            String[] parts = input.split(" /by ");
            Deadline deadline = new Deadline(parts[0].strip(), parts[1].strip());
            addTask(deadline);
            speak("Got it. I've added this deadline:\n  %s\nNow you have %d tasks in your list", deadline, tasks.size());
        } else {
            throw new DukeException("Invalid event format");
        }
    }

    /**
     * Handle the input for the mark and unmark commands.
     *
     * @param input     the input to be handled
     * @param completed the status to be set
     * @throws DukeException if the input is invalid
     */
    private void setTaskCompletionStatus(String input, boolean completed) throws DukeException {
        boolean isValid = false;
        int task_id = 0;
        if (input.matches("^[0-9]+$")) {
            task_id = parseInt(input) - 1;
            if (task_id < tasks.size() && task_id >= 0) {
                isValid = true;
            }
        }
        if (!isValid) {
            throw new DukeException("Task %s doesn't exist", input);
        }
        Task task = tasks.get(task_id);
        task.setDone(completed);
        if (completed) {
            speak("Nice! I've marked this task as done:\n  %s", task);
        } else {
            speak("Ok, I've marked this task as not done yet:\n  %s", task);
        }
        if (storage != null) storage.saveTasks(tasks);
    }

    /**
     * Handle the input for the delete command.
     *
     * @param input the input to be handled
     * @throws DukeException if the input is invalid
     */
    private void delete(String input) throws DukeException {
        try {
            if (input.matches("^[0-9]+$")) {
                Task task = deleteTask(parseInt(input.strip()) - 1);
                speak("Noted. I've removed this task:\n  %s\nNow you have %d tasks in your list", task, tasks.size());
            } else {
                throw new DukeException("Failed to delete task %s", input);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Failed to delete task %s", input);
        }
    }

    /**
     * Get Duke to speak the given text.
     *
     * @param text The text to speak.
     */
    private void speak(String text) {
        String line = "_".repeat(20) + '\n';
        System.out.println(line);
        System.out.println(text);
        System.out.println(line);
    }

    /**
     * Speaks the specified text defined by its format and arguments.
     *
     * @param format A format string
     * @param args   Arguments referenced by the format specifiers in the format string.
     */
    private void speak(String format, Object... args) {
        speak(String.format(format, args));
    }

    /**
     * Displays all current tasks.
     */
    public void listHistory() {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n");
        IntStream.range(0, tasks.size()).forEach(i -> output.append(String.format("%d. %s%n", i + 1, tasks.get(i))));
        speak(output.toString());
    }

    private void goodbye() {
        speak("Bye. Hope to see you again soon!");
    }
}
