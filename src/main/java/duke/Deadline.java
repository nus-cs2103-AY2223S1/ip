package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {

    LocalDate deadline;

    Deadline (String name, String deadline) {
        super(name);
        this.deadline = LocalDate.parse(deadline.trim());
    }

    Deadline (String name, String deadline, boolean done) {
        super(name, done);
        this.deadline = LocalDate.parse(deadline.trim(), DateTimeFormatter.ofPattern("MMM dd yyyy"));
        assert(this.deadline.isAfter(LocalDate.now()));
    }

    @Override
    public String toString() {
        String out = "[D][";
        if (super.getStatus()) {
            out += "X";
        } else {
            out += " ";
        }
        out += "] " + super.toString() + "(by : " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        return out;
    }


}
