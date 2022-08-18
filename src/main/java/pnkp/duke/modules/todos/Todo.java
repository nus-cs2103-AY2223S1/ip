package pnkp.duke.modules.todos;

import pnkp.duke.MessagefulException;

import java.util.Scanner;

import static java.lang.String.format;

public class Todo extends Task{
    public Todo(String name) {
        this(name, false);
    }

    public Todo(String name, boolean done) {
        super(name, done);
    }

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
