package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

import java.time.LocalDate;

public class Parser {
    private Storage storage;
    private TaskList tasks;
    public Parser(Storage s, TaskList tasks) {
        this.storage = s;
        this.tasks = tasks;
    }
    enum Command {
        list,
        bye,
        mark,
        unmark,
        delete,
        deadline,
        todo,
        event
    }
    public boolean parse(String[] command) {
        try {
            Command cmd = Command.valueOf(command[0]);
            int index;
            switch (cmd) {
            case bye:
                System.out.println("Bye! Don't Come back!");
                storage.write(tasks);
                return true;
            case list:
                tasks.list();
                break;
            case mark:
                index = Integer.parseInt(command[1]) - 1;
                tasks.mark(index);
                break;
            case event:
                String[] desc = command[1].split("/at ", 2);
                Event e = new Event(desc[0], desc[1]);
                tasks.add(e);
                break;
            case todo:
                try {
                    Todo t = new Todo(command[1]);
                    tasks.add(t);
                } catch (ArrayIndexOutOfBoundsException err) {
                    System.out.println("oops the description of a todo cannot be empty!");
                }
                break;
            case delete:
                if (command.length < 2) {
                    throw new DukeException("please specify which item to delete");
                }
                index = Integer.parseInt(command[1]) - 1;
                tasks.delete(index);
                break;
            case unmark:
                index = Integer.parseInt(command[1]) - 1;
                tasks.unMark(index);
                break;
            case deadline:
                String[] dl = command[1].split("/by ", 2);
                Deadline d = new Deadline(dl[0], LocalDate.parse(dl[1]));
                tasks.add(d);
                break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid command");
        }
        return false;
    }
}
