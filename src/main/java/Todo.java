import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    String writeToFile() {
        return "T|" + super.writeToFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}