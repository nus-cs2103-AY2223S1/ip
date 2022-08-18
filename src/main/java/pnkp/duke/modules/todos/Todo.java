package pnkp.duke.modules.todos;

import pnkp.duke.MessagefulException;

import java.util.Scanner;

import static java.lang.String.format;

/**
 * Todos - tasks with no attached timing.
 */
public class Todo extends Task {
    /**
     * Constructor
     * @param name The name of the task.
     */
    public Todo(String name) {
        this(name, false);
    }

    /**
     * Constructor
     * @param name The name of the task
     * @param done Whether the task is done.
     */
    public Todo(String name, boolean done) {
        super(name, done);
    }

    /**
     * Constructs a Todo from a Scanner with arguments.
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
    @Override
    public String toString() {
        return format("[T]%s", super.toString());
    }
}
