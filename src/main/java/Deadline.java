<<<<<<< HEAD
import java.time.LocalDate;
=======
>>>>>>> branch-Level-8
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private String dateTime;
<<<<<<< HEAD
    //private LocalDateTime date;
=======
    private LocalDateTime date;
>>>>>>> branch-Level-8

    public Deadline(String name, boolean completed, String dateTime){
        super(name,completed);
        this.dateTime = dateTime;
<<<<<<< HEAD
        //stringToDate(dateTime);
    }
    /*
    public void stringToDate(String dateTime){
        String[] dateTimeArr = dateTime.split(" ");
        LocalDate.parse(
        .format(DateTimeFormatter.ofPattern("MMM d yyyy"))
    }
    */
    @Override
    public String getTime() {
        return dateTime;
    }

    @Override
    public String getTaskType() {
        return "D";
=======
        applyDate(dateTime);

    }

    private void applyDate(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.date = LocalDateTime.parse(dateTime, formatter);
    }

    public String getDateTime(){
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    public String getDate(){
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
>>>>>>> branch-Level-8
    }

    @Override
    public Task toggleCompleted(){
<<<<<<< HEAD
        return new Deadline(getName(),!isCompleted(), dateTime);
=======
        return new Deadline(getName(),!isCompleted(),dateTime);

>>>>>>> branch-Level-8
    }

    @Override
    public String toString(){
<<<<<<< HEAD
        return String.format("[D][%s] %s (by: %s)",checkMarked(),getName(), dateTime);
=======
        return String.format("[D][%s] %s (by: %s)",checkMarked(),getName(),dateTime);
>>>>>>> branch-Level-8
    }

}
