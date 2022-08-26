package betago.tasks;

import betago.exceptions.InvalidCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String byDateTime;

    public Deadline(String description, String by) throws InvalidCommandException {
        super(description);
        String[] inputs = by.split(" ", 2);
        String[] formatPatterns = {"yyyy-MM-dd", "dd-MMM-yyyy", "dd/MM/yyyy"};
        for (int i = 0; i < formatPatterns.length; i++) {
            try {
                LocalDate d = LocalDate.parse(inputs[0], DateTimeFormatter.ofPattern(formatPatterns[i]));
                this.byDateTime = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                break;
            } catch (DateTimeParseException e) {
                if(i == formatPatterns.length - 1) {
                    throw new InvalidCommandException("Please enter the date and time in these format:\n" +
                            "yyyy-MM-dd, dd-MMM-yyyy, dd/MM/yyyy\n");
                }
            }
        }
        if (inputs.length == 2) {
            this.byDateTime = this.byDateTime + ", " + inputs[1];
        }
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.getTaskDescription() + " (by: " + byDateTime + ")";
    }

    @Override
    public String saveTask() {
        String icon;
        if (this.getStatusIcon() == "X") {
            icon = "1";
        } else {
            icon = "0";
        }
        return "D , " + icon + " , " + this.description + " , " + this.byDateTime + "\n";
    }
}
