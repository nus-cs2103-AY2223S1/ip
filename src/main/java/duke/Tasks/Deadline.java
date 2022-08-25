package duke.Tasks;

import duke.DukeException.DateTimeFormatException;
import duke.Parser.Parser;

import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) throws DateTimeFormatException {
        super(description);
        this.by = Parser.strToDate(by);
    }

    public static Deadline addTask(String name, String by) throws DateTimeFormatException {
        Deadline newDDL = new Deadline(name, by);
        System.out.println("       " + newDDL.printSelf());
        return newDDL;
    }

    @Override
    public String printSelf() throws DateTimeFormatException {
        return "[D]" + super.printSelf() + " (by: " + Parser.dateToString(this.by) + ")";
    }

    @Override
    public String recordString() {
        return "D | " + super.recordString() + " | " + by;
    }
}
