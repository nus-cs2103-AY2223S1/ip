
package duke.tasks;

import java.time.LocalDate;

import duke.dukeexception.DateTimeFormatException;
import duke.parser.Parser;

/**
 * Sub-class of task, which has an additional ddl date.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a Deadline object given description string and date string.
     * @param description The name of task.
     * @param by A date in format: yyyy-MM-dd.
     * @throws DateTimeFormatException
     */
    public Deadline(String description, String by) throws DateTimeFormatException {
        super(description);
        this.by = Parser.strToDate(by);
    }

    /**
     * Adds a new Deadline task.
     * @param name The name of task.
     * @param by A date in format: yyyy-MM-dd.
     * @return The generated Deadline Task.
     * @throws DateTimeFormatException
     */
    public static Deadline addTask(String name, String by) throws DateTimeFormatException {
        Deadline newDdl = new Deadline(name, by);
        System.out.println("       " + newDdl.printSelf());
        return newDdl;
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
