import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    private static final char SYMBOL = 'D';
    private LocalDateTime deadline;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy h:mma");

    public Deadlines(String s1, String s2){
        super(s1);
        this.deadline = LocalDateTime.parse(s2.replace(' ', 'T').replaceAll("/", "-"));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline.format(formatter));
    }
}
