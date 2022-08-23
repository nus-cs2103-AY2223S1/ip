package blink.task;

import java.time.LocalDate;

public class ToDos extends Task{

    public ToDos(String description) {
        super (description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveString() {
        return "T " + "|" + (this.isDone ? 1 : 0) + "| " + this.description + "\n";
    }

    @Override
    public boolean checkDate(LocalDate anoDate) {
        return false;
    }
}

