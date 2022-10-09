package duke.tasks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    /** Returns formatted String to write back to our data
     *
     * @return String to save to our stored data
     */
    @Override
    public String writeToFile() {
        return "T|" + super.writeToFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}