import java.util.Scanner;

public class Duke {
    protected TaskList tasks = new TaskList();

    public enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    public Command getCommand(String str) throws DukeException {
        try {
            return Command.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("Sorry! I don't know what that means :(");
        }
    }

    public void greet() {
        System.out.println("Hello! I'm Pip :)\nWhat can I do for you?");
    }

    public void exit() {
        System.out.println("Goodbye and see you again soon!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();

        Scanner in = new Scanner(System.in);
        
        String input;
        do {
            input = in.nextLine();
            String[] splitInputArray = input.split(" ", 2);
            String firstWord = splitInputArray[0];
            try {
                switch (duke.getCommand(firstWord)) {
                    case BYE:
                        duke.exit();
                        break;
                    case LIST:
                        duke.tasks.list();
                        break;
                    case MARK:
                    case UNMARK:
                        duke.tasks.changeTaskStatus(splitInputArray);
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        duke.tasks.addTask(splitInputArray);
                        duke.tasks.displayNumOfTasks();
                        break;
                    case DELETE:
                        duke.tasks.deleteTask(splitInputArray);
                        duke.tasks.displayNumOfTasks();
                        break;
                    default:
                        throw new DukeException("Sorry! I don't know what that means :(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        } while (!input.equals("bye"));

        in.close();
    }
}
