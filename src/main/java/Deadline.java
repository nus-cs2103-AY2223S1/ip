import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private String dateTime;
    //private LocalDateTime date;

    public Deadline(String name, boolean completed, String dateTime){
        super(name,completed);
        this.dateTime = dateTime;
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
    }

    @Override
    public Task toggleCompleted(){
        return new Deadline(getName(),!isCompleted(), dateTime);
    }

    @Override
    public String toString(){
        return String.format("[D][%s] %s (by: %s)",checkMarked(),getName(), dateTime);
    }
}
