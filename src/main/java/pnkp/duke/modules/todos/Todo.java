package pnkp.duke.modules.todos;

import java.util.Scanner;

import static java.lang.String.format;

public class Todo extends Task{
    public Todo(String name) {
        this(name, false);
    }

    public Todo(String name, boolean done) {
        super(name, done);
    }

    public static Todo fromChat(Scanner sc) throws IllegalArgumentException {
        if (!sc.hasNextLine()) {
            throw new IllegalArgumentException("Missing name");
        }

        return new Todo(sc.nextLine());
    }
    @Override
    public String toString() {
        return format("[T]%s", super.toString());
    }
}
