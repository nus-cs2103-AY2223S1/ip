package task;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Represents a Task, with no reference to date or time.
 */
public class ToDos extends Task {

    public ToDos(String s) {
        super(s);
    }

    @Override
    public void write(FileWriter fw) throws IOException {
        String str = String.format("T | %d | %s\n", this.getDone(), this.getName());
        fw.write(str);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
