import java.io.FileWriter;
import java.io.IOException;

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
