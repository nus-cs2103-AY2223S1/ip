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
                    Todo todo = new Todo(todoDescription);
                    this.addTask(todo);
                    System.out.printf("Swee lah! I added this task liao:\n%s\n", todo);
                    this.printTaskCountMessage();
                }
                break;
            case EVENT:
                String[] splittedEvent = input.substring(5).split(" /at ", 3);
                if (splittedEvent.length == 2 && splittedEvent[0].trim().equals("")) {
                    throw new DukeException("Eh you never added your task name");
                } else if (splittedEvent.length == 2 && !splittedEvent[0].trim().equals("")
                        && !splittedEvent[1].trim().equals("")) {
                    Event event= new Event(splittedEvent[0].trim(), splittedEvent[1].trim());
                    this.addTask(event);
                    System.out.printf("Swee lah! I added this task liao: \n%s\n", event);
                    this.printTaskCountMessage();
                } else {
                    throw new DukeException("Eh you never added the event range");
                }
                break;
            case DEADLINE:
                String[] splittedDeadline = input.substring(8).split(" /by ", 3);
                if (splittedDeadline.length == 2 && splittedDeadline[0].trim().equals("")) {
                    throw new DukeException("Eh you never added your task name");
                } else if (splittedDeadline.length == 2 && !splittedDeadline[0].trim().equals("")
                        && !splittedDeadline[1].trim().equals("")) {
                    Deadline deadline = new Deadline(splittedDeadline[0].trim(), splittedDeadline[1].trim());
                    this.addTask(deadline);
                    System.out.printf("Swee lah! I added this task liao: \n%s\n", deadline);
                    this.printTaskCountMessage();
                } else {
                    throw new DukeException("Eh you never added a deadline");
                }
                break;
            case EMPTY:
                throw new DukeException("Eh you never type anything leh?");
            case MARK:
                String markIndexString = input.substring(4).trim();
                try {
                    int taskIndex = Integer.parseInt(markIndexString);
                    if (taskIndex - 1 >= this.tasks.size() || taskIndex - 1 < 0) {
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
                    if (taskIndex - 1 >= this.tasks.size() || taskIndex - 1 < 0) {
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
                    if (taskIndex - 1 >= this.tasks.size() || taskIndex - 1 < 0) {
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
}
