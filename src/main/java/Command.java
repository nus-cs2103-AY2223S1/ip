import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class Command {

    private CommandType commandType;
    private ArrayList<Task> tasks;
    private String input;

    Command(CommandType commandType, ArrayList<Task> tasks, String input) {
        this.commandType = commandType;
        this.tasks = tasks;
        this.input = input;
    }

    private void printTaskCountMessage() {
        System.out.printf("Boss, you got %s tasks now\n", this.tasks.size());
    }

    private void printStoredInputs() {
        int numberOfTasks = this.tasks.size();
        if (numberOfTasks > 0) {
            System.out.println("Boss ah, this one your tasks:");
            for (int i = 0; i < numberOfTasks; i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
            this.printTaskCountMessage();
        } else if (numberOfTasks == 0) {
            System.out.println("Boss, you got no task yet ah");
        }
    }
    private void addTask(Task input) {
        tasks.add(input);
    }

    private LocalDateTime deadlineStringToLocalDate(String deadline) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, formatter);
        return deadlineDateTime;
    }

    private boolean hasTaskIndex(int taskIndex) {
        return taskIndex - 1 < this.tasks.size() && taskIndex - 1 >= 0;
    }

    public void run() throws DukeException {
        switch (this.commandType) {
        case BYE:
            break;
        case LIST:
            printStoredInputs();
            break;
        case TODO:
            String todoDescription = input.substring(4).trim();
            if (todoDescription.equals("")) {
                throw new DukeException("Eh your task cannot be empty lah!");
            } else {
                Todo todo = new Todo(todoDescription, false);
                this.addTask(todo);
                printAddedTaskMessage(todo);
                this.printTaskCountMessage();
            }
            break;
        case EVENT:
            String[] splittedEvent = input.substring(5).split(" /at ", 3);
            boolean isEmptyEvent = splittedEvent.length == 2 && splittedEvent[0].trim().equals("");
            boolean isIncorrectEventRange = splittedEvent.length != 2 || splittedEvent[0].trim().equals("")
                    || splittedEvent[1].trim().equals("");
            if (isEmptyEvent) {
                throw new DukeException("Eh you never added your task name");
            } else if (isIncorrectEventRange) {
                throw new DukeException("Eh you never added the event range");
            } else {
                Event event = new Event(splittedEvent[0].trim(), splittedEvent[1].trim(), false);
                this.addTask(event);
                printAddedTaskMessage(event);
                this.printTaskCountMessage();
            }
            break;
        case DEADLINE:
            String[] splittedDeadline = input.substring(8).split(" /by ", 3);
            boolean isEmptyDeadline = splittedDeadline.length == 2 && splittedDeadline[0].trim().equals("");
            boolean isIncorrectDeadlineDate = splittedDeadline.length != 2 || splittedDeadline[0].trim().equals("")
                    || splittedDeadline[1].trim().equals("");
            if (isEmptyDeadline) {
                throw new DukeException("Eh you never added your task name");
            } else if (isIncorrectDeadlineDate) {
                throw new DukeException("Eh you never added a deadline");
            } else {
                try {
                    LocalDateTime deadlineDateTime = deadlineStringToLocalDate(splittedDeadline[1].trim());
                    System.out.println(deadlineDateTime);
                    Deadline deadline = new Deadline(splittedDeadline[0].trim(), false, deadlineDateTime);
                    this.addTask(deadline);
                    printAddedTaskMessage(deadline);
                    this.printTaskCountMessage();
                } catch (DateTimeParseException e) {
                    throw new DukeException("Eh you never add a proper deadline date! \n" +
                            "Your deadline date should be like this lah: Jan 21 2023 04:10 AM");
                }
            }
            break;
        case EMPTY:
            throw new DukeException("Eh you never type anything leh?");
        case MARK:
            String markIndexString = input.substring(4).trim();
            try {
                int taskIndex = Integer.parseInt(markIndexString);
                if (!hasTaskIndex(taskIndex)) {
                    throw new DukeException("Eh, you got that task number meh?");
                }
                if (this.tasks.get(taskIndex - 1).canChangeIsDone(true)) {
                    this.tasks.get(taskIndex - 1).changeIsDone(true);
                    System.out.println("Swee lah! Your task done liao: \n" + this.tasks.get(taskIndex - 1));
                } else {
                    throw new DukeException("Eh, you done that task alr lah");
                }
            }
            catch (NumberFormatException e) {
                throw new DukeException("Eh, you enter your task number correctly anot?");
            }
            break;
        case UNMARK:
            String unmarkIndexString = input.substring(6).trim();
            try {
                int taskIndex = Integer.parseInt(unmarkIndexString);
                if (!hasTaskIndex(taskIndex)) {
                    throw new DukeException("Eh, you got that task number meh?");
                }
                if (this.tasks.get(taskIndex - 1).canChangeIsDone(false)) {
                    this.tasks.get(taskIndex - 1).changeIsDone(false);
                    System.out.println("Eh? Not done yet? Okay I change liao: \n" + this.tasks.get(taskIndex - 1));
                } else {
                    throw new DukeException("Eh, your task alr not done lah");
                }
            }
            catch (NumberFormatException e) {
                throw new DukeException("Eh, you enter your task number correctly anot?");
            }
            break;
        case DELETE:
            String taskIndexToDelete = input.substring(6).trim();
            try {
                int taskIndex = Integer.parseInt(taskIndexToDelete);
                if (!hasTaskIndex(taskIndex)) {
                    throw new DukeException("Eh, you got that task number meh?");
                } else {
                    Task deletedTask = tasks.remove(taskIndex - 1);
                    System.out.printf("Okay boss, this task I delete le:\n%s\n", deletedTask);
                }
            }
            catch (NumberFormatException e) {
                throw new DukeException("Eh, you enter your task number correctly anot?");
            }
            break;
        default:
            throw new DukeException("What talking you");
        }
    }

    private PrintStream printAddedTaskMessage(Task task) {
        return System.out.printf("Swee lah! I added this task liao:\n%s\n", task);
    }
}
