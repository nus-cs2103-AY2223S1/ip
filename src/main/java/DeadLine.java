import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadLine extends Task {
    LocalDate byWhen = null;

    public DeadLine() {
        super();
    }

    public void addName(String userInput) throws DukeException {
        if (userInput.length() <= 9) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        int index = userInput.indexOf("/by") - 1;
        if (index <= 8) {
            throw new DukeException("☹ OOPS!!! Please indicate when the deadline is due with '/by'.");
        }
        super.addName(userInput.substring(9, index));

        this.byWhen = LocalDate.parse(userInput.substring(index + 5).replace('/', '-'));;
    }

    @Override
    public String getStatus() {
        return String.format("[D]%s (by: %s)", super.getStatus(), this.byWhen.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
