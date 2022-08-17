package pnkp.duke.modules.todos;

import static java.lang.String.format;

public class Todo extends Task{
    public Todo(String name) {
        this(name, false);
    }

    public Todo(String name, boolean done) {
        super(name, done);
    }

    @Override
    public String toString() {
        return format("[T]%s", super.toString());
    }
}
