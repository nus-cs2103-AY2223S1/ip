package task;

import java.time.LocalDateTime;

/**
 * Represent a deadline task
 */
public class DukeTaskDeadline extends DukeTask {
    
    private LocalDateTime ldt;

    public DukeTaskDeadline (String task, boolean mark, char type, LocalDateTime ldt) {
        super(task, mark, type);
        this.ldt = ldt;
    }

    /**
     * Print out a format of deadline task
     * @return String
     */
    @Override
    public String toString() {
        return super.toString() + "(" + ldt.toString() + ")";
    }

    /**
     * print out a format of deadline task. This is for save file use.
     * @return String
     */
    @Override
    public String toStringSaveFile() {
        return super.toStringSaveFile() + "/" + "(" + this.ldt.toString() + ")";
    }

    /**
     * return the local date time
     * @return LocalDateTime
     */
    public LocalDateTime getLDT() {
        return ldt;
    }
}