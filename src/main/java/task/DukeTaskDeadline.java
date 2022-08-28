package task;

import java.time.LocalDateTime;

public class DukeTaskDeadline extends DukeTask {
    
    private LocalDateTime ldt;

    public DukeTaskDeadline (String task, boolean mark, char type, LocalDateTime ldt) {
        super(task, mark, type);
        this.ldt = ldt;
    }

    @Override
    public String toString() {
        return super.toString() + "(" + ldt.toString() + ")";
    }

    @Override
    public String toStringSaveFile() {
        return super.toStringSaveFile() + "/" + "(" + this.ldt.toString() + ")";
    }

    public LocalDateTime getLDT() {
        return ldt;
    }
}