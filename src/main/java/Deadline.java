import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public Deadline(String description) {
        super(description);
    }


    // Adapted from
    // https://stackoverflow.com/questions/14316487/java-getting-a-substring-from-a-string-starting-after-a-particular-character

    protected String by = description.substring(description.lastIndexOf("/") + 4);

    private DateTimeFormatter formatted = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate date = LocalDate.parse(by, formatted);

    @Override
    public String toString() {
        return "[D]" + super.toString().substring(0, 4) + getSubstring()
                + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    // To return substring before "/"
    // Adapted from
    // https://stackoverflow.com/questions/7683448/in-java-how-to-get-substring-from-a-string-till-a-character-c
    private String getSubstring() {
        int index = description.indexOf("/");
        if (index != - 1) {
            return description.substring(9, index);
        }
        return null;
    }
}

