package task;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a Task, with no reference to date or time.
 */
public class ToDo extends Task {

    public static final String SYMBOL = "T";
    private static final String FORMAT = "[T]%s";

    public ToDo(String s) {
        super(s);
    }

    @Override
    public void write(FileWriter fw) throws IOException {
        String str = String.format("T | %d | %s\n", this.getDoneInt(), this.getName());
        fw.write(str);
    }

    @Override
    public String toString() {
        return String.format(ToDo.FORMAT, super.toString());
    }
}
