import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Deadline extends Task {
    private LocalDateTime deadline;

    private Deadline(boolean isDone, String text, LocalDateTime deadline, boolean isPrinting) {
        super(isDone, text, isPrinting);
        this.deadline = deadline;
        if (isPrinting) {
            System.out.println(this.toString());
        }
    }

    public static Deadline of(String command, boolean isPrinting) throws IllegalArgumentException {
        boolean isDone = command.contains("/done");
        if (isDone) {
            command = command.replace("/done", "");
        }
        String[] c1 = command.split("/by");
        String text = c1[0].replaceFirst("deadline", "").strip();
        String time = c1.length > 1 ? c1[1].strip() : "";
        if (text.isEmpty()) {
            throw new IllegalArgumentException("🙁 OOPS!!! The description of a deadline cannot be empty.\n");
        } else if (time.isEmpty()) {
            throw new IllegalArgumentException("🙁 OOPS!!! Provide a time for the deadline.\n");
        } else {
            LocalDateTime timeObj = null;
            try {
                timeObj = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd/MM/yy HHmm"));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("🙁 OOPS!!! Provide a valid time (dd/MM/yy HHmm) for the deadline.\n");
            }
            return new Deadline(isDone, text, timeObj, isPrinting);
        }
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm")));
    }
}