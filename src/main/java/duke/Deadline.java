package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private String date = "";
    private LocalDate dateProper;
    public Deadline(String taskDescription, String date) {
        super(taskDescription.replace("deadline ", ""));
        this.date = date;
        this.dateProper = LocalDate.parse(date);
    }
    public Deadline(String taskDescription, String date, boolean isCompleted) {
        super(taskDescription, isCompleted);
        this.date = date;
        this.dateProper = LocalDate.parse(date);
    }

    @Override
    protected String returnDescription() {
        String formattedDate = this.dateProper.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.returnDescription() + " (by: " + formattedDate + ")";
    }

    @Override
    protected String toWriteFile(){
        return "D , " + super.toWriteFile() + " , " + this.date;
    }
}

