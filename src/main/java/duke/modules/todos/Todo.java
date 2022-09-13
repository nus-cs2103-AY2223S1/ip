package duke.modules.todos;

import duke.MessagefulException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.format;

/**
 * Todos - tasks with no attached timing.
 */
public class Todo extends Task {
    /**
     * Constructor
     *
     * @param name The name of the task.
     */
    public Todo(String name) {
        this(name, false);
    }

    /**
     * Constructor
     *
     * @param name The name of the task
     * @param done Whether the task is done.
     */
    public Todo(String name, boolean done) {
        super(name, done);
    }

    /**
     * Constructs a Todo from a Scanner with arguments.
     *
     * @param sc The scanner with the remaining text in the message.
     * @return The constructed todo.
     * @throws MessagefulException There is an issue with the arguments.
     */
    public static Todo fromChat(Scanner sc) throws MessagefulException {
        if (!sc.hasNextLine()) {
            throw new MessagefulException("Missing name", "Please give your todo a name.");
        }

        return new Todo(sc.nextLine());
    }

    public static final String TYPE_CODE = "T";

    /**
     * Packs the task's data into a List.
     *
     * @return The packed data.
     */
    @Override
    public List<String> flatPack() {
        List<String> result = new ArrayList<>(super.flatPack());
        result.set(0, TYPE_CODE);

        return result;
    }

    /**
     * Unpacks the task's data from a List.
     *
     * @param l The packed data.
     */
    public Todo(List<? extends String> l) {
        super(l);
        if (!l.get(0).equals(TYPE_CODE)) {
            throw new IllegalArgumentException("Trying to hydrate non-todo as todo: " + l);
        }
    }

    @Override
    public String toString() {
        return format("[T]%s", super.toString());
    }
}
