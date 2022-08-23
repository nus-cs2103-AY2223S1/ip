import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class Event extends Task{
    private String at;
    private String description;
    public Event(String description) throws DukeException{
        super("tempTask");
        try {
            String temp=description.split(" ")[1];
        }catch (IndexOutOfBoundsException ie) {
            throw (new DukeException("OOPS!!! The description of a event cannot be empty."));
        }
        try {
            super.correctDescrition(description.split("on")[0].split(" ", 2)[1]);
            super.getFullDescription(description.split(" ",2)[1]);
            this.at = description.split("on")[1];
        }catch (Exception ie){
            throw(new DukeException("OOPS!!! The description of a event is still not correct."));
        }
        this.description=description;

    }
    @Override
    public String printTask(){
        try {
            return "[E]" + super.printTask() + " (at:"
                    + super.showTime() + ")";
        }catch (DukeException d){
            return "[E]" + super.printTask() + " (at:"
                    + this.at + ")";
        }
    }
    @Override
    public String getDescription(){
        return this.description;
    }
}
