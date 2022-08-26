import java.time.LocalDateTime;

public class DukeTaskDeadline extends DukeTask {
    
    LocalDateTime ldt;

    DukeTaskDeadline (String task, boolean mark, char type, LocalDateTime ldt) {
        super(task, mark, type);
        this.ldt = ldt;
    }

    @Override
    public String toString() {
        return super.toString() + "(" + ldt.toString() + ")";
    }

    public LocalDateTime getLDT() {
        return ldt;
    }
}