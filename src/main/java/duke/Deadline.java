package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Deadline extends Task {

    protected String by;
    protected String final_format;
    protected boolean check_delete = false;


    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }


    protected void date_formatter() {
        // date_str gives date only
        String date_str = this.by.replaceAll(this.by.substring(this.by.length() - 5),"");
        String[] date_slots = date_str.split("/", -1);

        if (date_slots[0].length() == 1) {
            date_slots[0] = "0" + date_slots[0];
        }
        if (date_slots[1].length() == 1) {
            date_slots[1] = "0" + date_slots[1];
        }
        String d = "-";
        String another_format = date_slots[2] + d + date_slots[1] + d + date_slots[0];

        // change format to Month
        LocalDate d1 = LocalDate.parse(another_format);
        this.final_format = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    protected void date_increment(int num) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");;
        LocalDate d1 = LocalDate.parse(this.final_format, formatter);
        d1 = d1.plusDays(num);

        String[] str_arr = this.by.split(" ", 2);
        this.by = this.by.replaceFirst(str_arr[0], d1.format(DateTimeFormatter.ofPattern("d/MM/yyyy")));
    }



    /**
     * toString method that turns the input of deadline into a String type
     *
     * @return String
     */
    @Override
    public String toString() {
        if (!check_delete) {
            date_formatter();
        }
        check_delete = false;
        String time = this.by.substring(this.by.length() - 4);
        String new_format = this.final_format + " " + time;
        return "[D]" + super.toString() + "(by: " + new_format + ")";
    }
}