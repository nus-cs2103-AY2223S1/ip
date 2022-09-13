package task;

import java.io.FileWriter;
import java.io.IOException;

/**
 * A Task, with no reference to date or time.
 */
public class ToDo extends Task {

    public static final String SYMBOL = "T";
    private static final String PRINT_FORMAT = "[T]%s";
    private static final String WRITE_FORMAT = "T | %d | %s\n";

    public ToDo(String name) {
        super(name);
    }

    @Override
    public void write(FileWriter fw) throws IOException {
        String line = String.format(ToDo.WRITE_FORMAT, this.getDoneInt(), this.getName());
        fw.write(line);
    }

    @Override
    public String toString() {
        return String.format(ToDo.PRINT_FORMAT, super.toString());
    }

    @Override
    public boolean equals(Task task) {
        if (task == null) {
            return false;
        } else if (!(task instanceof ToDo)) {
            return false;
        } else {
            return this.getName().equals(task.getName());
        }
    }
}
