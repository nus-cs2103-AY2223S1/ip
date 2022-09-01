package duke;

import java.time.LocalDate;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

/**
 * Parser class to manage user inputs
 */
public class Parser {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor Method for Parser class
     * @param s
     * @param tasks
     */
    public Parser(Storage s, TaskList tasks) {
        this.storage = s;
        this.tasks = tasks;
    }

    /**
     * List of commands that the user can enter
     */
    enum Command {
        list,
        bye,
        mark,
        unmark,
        delete,
        deadline,
        todo,
        event,
        find
    }

    /**
     * Parses the command entered by the user and prints out the output required
     * @param command
     * @return true if the command is by, else returns false
     */
    public String parse(String[] command) {
        String result = "";
        try {
            Command cmd = Command.valueOf(command[0]);
            int index;
            switch (cmd) {
            case bye:
                result = "Bye! Don't Come back!";
                storage.write(tasks);
                return result;
            case list:
                result = tasks.list();
                break;
            case mark:
                index = Integer.parseInt(command[1]) - 1;
                result = tasks.mark(index);
                break;
            case event:
                String[] desc = command[1].split("/at ", 2);
                Event e = new Event(desc[0], desc[1]);
                result = tasks.add(e);
                break;
            case todo:
                try {
                    Todo t = new Todo(command[1]);
                    result += tasks.add(t);
                } catch (ArrayIndexOutOfBoundsException err) {
                    System.out.println("oops the description of a todo cannot be empty!");
                }
                break;
            case delete:
                if (command.length < 2) {
                    throw new DukeException("please specify which item to delete");
                }
                index = Integer.parseInt(command[1]) - 1;
                result = tasks.delete(index);
                break;
            case unmark:
                index = Integer.parseInt(command[1]) - 1;
                result = tasks.unMark(index);
                break;
            case deadline:
                String[] dl = command[1].split("/by ", 2);
                Deadline d = new Deadline(dl[0], LocalDate.parse(dl[1]));
                result = tasks.add(d);
                break;
            case find:
                String item = command[1];
                result = tasks.find(item);
                break;
            default:
                result = "Invalid Command";
            }

        } catch (IllegalArgumentException e) {
            return "Invalid command";
        }
        return result;
    }
}
