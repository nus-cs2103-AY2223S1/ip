package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;

public class Duke {
    private final TaskList tasks;
    private final Ui ui;

    public Duke() {
        ui = new Ui();
        tasks = new TaskList(Storage.readData());
    }

    public void run() {
        ui.printWelcome();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            try {
                Parser parser = new Parser(input);
                parser.parse();
                Command command = parser.getCommand();
                int taskIndex = parser.getTaskIndex();
                String[] args = parser.getArgs();

                switch (command) {
                case BYE:
                    // Write tasks data to storage before terminating program
                    Storage.writeData(tasks.toString());
                    ui.printGoodbye();
                    return;
                case LIST:
                    tasks.printList();
                    break;
                case MARK:
                    tasks.mark(taskIndex);
                    ui.printMark(tasks.getTask(taskIndex));
                    break;
                case UNMARK:
                    tasks.unmark(taskIndex);
                    ui.printUnmark(tasks.getTask(taskIndex));
                    break;
                case DELETE:
                    Task temp = tasks.getTask(taskIndex);
                    tasks.remove(taskIndex);
                    ui.printDelete(temp);
                    break;
                case TODO:
                    Task newTodoTask = new Todo(args[0]);
                    tasks.add(newTodoTask);
                    ui.printAdd(newTodoTask);
                    break;
                case DEADLINE:
                    Task newDeadlineTask = new Deadline(args[0], args[1]);
                    tasks.add(newDeadlineTask);
                    ui.printAdd(newDeadlineTask);
                    break;
                case EVENT:
                    Task newEventTask = new Event(args[0], args[1]);
                    tasks.add(newEventTask);
                    ui.printAdd(newEventTask);
                    break;
                default:
                    throw new DukeException("Unrecognized command");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
