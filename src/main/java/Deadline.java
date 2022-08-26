import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

class Deadline extends Task {

    LocalDate deadline;

    Deadline (int id, String name, String deadline) {
        super(id, name);
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
