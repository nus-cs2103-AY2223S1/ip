package task;

import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with a deadline
 */
public class Deadline extends Task {

    private static final char SYMBOL = 'D';
    private LocalDateTime deadline;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy h:mma");

    public Deadline(String s1, String s2) {
        super(s1);
        this.deadline = LocalDateTime.parse(s2.replace(' ', 'T').replaceAll("/", "-"));
    }

    @Override
    public void write(FileWriter fw) throws IOException {
        String str = String.format("D | %d | %s | %s\n", this.getDoneInt(), this.getName(), this.deadline);
        fw.write(str);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline.format(formatter));
    }
}
