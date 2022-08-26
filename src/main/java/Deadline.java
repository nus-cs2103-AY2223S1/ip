import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by, boolean done) {
        super(description, 'D', done);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(Arrays.stream(by.split(" ")).skip(1).collect(Collectors.joining("")), formatter);
    }

    public static Deadline fromSaveString(String saveString) throws RuntimeException {
        String[] splitSaveString = saveString.split("(\",\")|(\",)|(,\")|\"");
        if(splitSaveString.length != 3) {
            throw new RuntimeException("Tried to read unexpected save data.");
        }
        String description = splitSaveString[1];
        String by = "by " + splitSaveString[2];
        boolean done = splitSaveString[0].endsWith("1");
        return new Deadline(description, by, done);
    }

    @Override
    public String toString() {
        return super.toString() + " (on " + by.toString() + ")";
    }

    @Override
    public String saveData() {
        return super.saveData() + String.format(",\"%s\"", this.by.toString());
    }
}
