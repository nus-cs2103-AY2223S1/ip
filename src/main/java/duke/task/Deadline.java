package duke.task;

import duke.task.Task;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public String dl;
    public LocalDate exactTime;

    public Deadline(String name, String dl) {
        super(name);
        this.dl = dl;
        try {
            this.exactTime = LocalDate.parse(dl);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong input format!");
        }
    }

    @Override
    public boolean needToRemind() {
        if (isDone) {
            return false;
        } else {
            LocalDate now = LocalDate.now();
            Period period = Period.between(now, exactTime);
            return (period.getYears() == 0 && period.getMonths() == 0 && period.getDays() <= 7);
        }
    }

    @Override
    public String getOutput() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, name, dl);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + exactTime.getMonth() + " "
                + exactTime.getDayOfMonth() + " " + exactTime.getYear() + ")";
    }
}