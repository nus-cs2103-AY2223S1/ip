import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

/**
 * Chatbot driver code. Receives the input from the user
 * and responds accordingly.
 */
public class Tumu {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    private static final String END_CHAT_BOT_CMD = "bye";
    private static final String LIST_USER_TEXT_CMD = "list";
    private static final String MARK_CMD = "mark";
    private static final String UNMARK_CMD = "unmark";
    private static final String TODO_CMD = "todo";
    private static final String DEADLINE_CMD = "deadline";
    private static final String EVENT_CMD = "event";
    private static final String DELETE_CMD = "delete";

    public Tumu() {
        ui = new UI();
        storage = new Storage("data/Tumu.txt");
        tasks = new TaskList(storage.loadData());
    }

    public static void main(String[] args) {
        new Tumu().run();
    }

    private void run() {
        ui.greeting();
        response();
    }

    private void saveUserTasks() {
        storage.saveData(tasks.getTasks());
    }

    private void response() {
        Scanner sc = new Scanner(System.in);
        String command;

        do {
            command = sc.next().toLowerCase();

            ui.showLine();
            try {
                switch (command) {
                case END_CHAT_BOT_CMD:
                    ui.goodbye();
                    break;
                case LIST_USER_TEXT_CMD:
                    listTasks();
                    break;
                case MARK_CMD:
                    markTask(sc.nextInt());
                    saveUserTasks();
                    break;
                case UNMARK_CMD:
                    unmarkTask(sc.nextInt());
                    saveUserTasks();
                    break;
                case TODO_CMD:
                    addTodoTask(sc.nextLine().trim());
                    saveUserTasks();
                    break;
                case DEADLINE_CMD:
                    addDeadlineTask(sc.nextLine().trim());
                    saveUserTasks();
                    break;
                case EVENT_CMD:
                    addEventTask(sc.nextLine().trim());
                    saveUserTasks();
                    break;
                case DELETE_CMD:
                    deleteTask(sc.nextInt());
                    saveUserTasks();
                    break;
                default:
                    //No commands are recognised.
                    sc.nextLine(); //clear buffer
                    throw new UnrecognisedCommandException(command.trim());
                }
            } catch (InputMismatchException e) {
                ui.notifyUser("Please (un)mark or delete a task by " +
                        "its list position (must be an integer)!");
                sc.nextLine(); //clear buffer
            } catch (TumuException e) {
                ui.notifyUser(e.toString());
            }

            ui.showLine();

        } while (!command.equalsIgnoreCase(END_CHAT_BOT_CMD));

        sc.close();
    }

    private void listTasks() {
        /**
         * Lists previous user texts in succession.
         */

        ui.notifyUser("Here are your current tasks:");
        List<String> taskPrint = new ArrayList<>();
        tasks.fillTaskPrint(taskPrint);
        for (String task : taskPrint) {
            ui.notifyUser(task);
        }
    }

    private void markTask(int oneIndexedNum) throws TumuException {
        /**
         * Mark the oneIndexedNumth Task in userTasks.
         */

        Task task = tasks.markTask(oneIndexedNum);
        if (task != null) {
            ui.notifyUser("Alright, I have marked this task as done:\n\t" + task);
        }
    }

    private void unmarkTask(int oneIndexedNum) throws TumuException {
        /**
         * Unmark the oneIndexedNumth Task in userTasks.
         */

        Task task = tasks.unmarkTask(oneIndexedNum);
        if (task != null) {
            ui.notifyUser("Alright, I have unmarked this task:\n\t" + task);
        }
    }

    private void addTodoTask(String userInput) throws TumuException {
        /**
         * Adds a todo task to list.
         */

        if (userInput.isBlank()) throw new TodoException();
        else addTaskType(new Todo(userInput));
    }

    private void addDeadlineTask(String userInput) throws TumuException {
        /**
         * Adds a deadline to list.
         */

        //Check for "/by", if not available then prompt user to add timing.
        if (!userInput.contains("/by")) {
            throw new DENoTimingException("by");
        } else {
            //Parse the string. Make sure there is no multiple "/by" statements.
            String[] parse = userInput.replaceAll("\\s+", "").split("/by");
            if (parse.length > 2) throw new DETimingOverflowException();
            else if (parse.length < 2 || parse[0].isBlank() || parse[1].isBlank())
                throw new DENoArgException();
            else addTaskType(new Deadline(parse[0], parse[1]));
        }
    }

    private void addEventTask(String userInput) throws TumuException {
        /**
         * Adds an event to list.
         */

        //Check for "/at", if not available then prompt user to add timing.
        if (!userInput.contains("/at")) {
            throw new DENoTimingException("at");
        } else {
            //Parse the string. Make sure there is no multiple "/at" statements.
            String[] parse = userInput.replaceAll("\\s+", "").split("/at");
            if (parse.length > 2) throw new DETimingOverflowException();
            else if (parse.length < 2 || parse[0].isBlank() || parse[1].isBlank())
                throw new DENoArgException();
            else addTaskType(new Event(parse[0], parse[1]));
        }
    }

    private void deleteTask(int oneIndexedNum) throws TumuException {
        /**
         * Deletes a task at position oneIndexedNum - 1.
         */

        Task removedTask = tasks.deleteTask(oneIndexedNum);
        if (removedTask != null) {
            ui.notifyUser("Alright, I have removed this task for you:\n\t\t" + removedTask);
            ui.notifyUser(String.format("You have %d task(s) in the list.", tasks.getListSize()));
        }
    }

    private void addTaskType(Task task) {
        ui.notifyUser("I've added a task into your list:\n\t\t" + task);
        tasks.addTask(task);
        ui.notifyUser(String.format("You have %d task(s) in the list.", tasks.getListSize()));
    }
}
