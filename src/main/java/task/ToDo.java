package task;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a Task, with no reference to date or time.
 */
public class ToDo extends Task {

    public static final String SYMBOL = "T";
    private static final String PRINT_FORMAT = "[T]%s";
    private static final String WRITE_FORMAT = "T | %d | %s\n";

    public ToDo(String s) {
        super(s);
    }

    @Override
    public void write(FileWriter fw) throws IOException {
        String str = String.format(ToDo.WRITE_FORMAT, this.getDoneInt(), this.getName());
        fw.write(str);
    }

    @Override
    public String toString() {
        return String.format(ToDo.PRINT_FORMAT, super.toString());
    }
}
