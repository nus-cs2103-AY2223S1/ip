package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {

    LocalDate deadline;

    Deadline (int id, String name, String deadline) {
        super(id, name);
        this.deadline = LocalDate.parse(deadline);
    }

    Deadline (int id, String name, String deadline, boolean done) {
        super(id, name, done);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toString() {
        String out = super.getId() + ".[D][";
        if (super.getStatus())
            out += "X";
        else
            out += " ";
        out += "] " + super.toString() + "(by : " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        return out;
    }


}
