import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ToDo extends Task{
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a ToDos object
     */
    public ToDo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
/*
    public Event(String description, String at) {
        super(description);
        this.at = at;
        try {
            //reverts the format for file reading
            //not good to use exceptions as conditions though...
            this.localDate = LocalDate.parse(at.trim(), DateTimeFormatter.ofPattern("MMM d yyyy"));
            DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
            String newFormat = dtFormatter.format(localDate);
            this.at = newFormat;
            this.localDate = LocalDate.parse(this.at);
        } catch (DateTimeParseException e) {
            try {
                this.localDate = LocalDate.parse(at.trim(), DateTimeFormatter.ofPattern("yyyy-mm-dd"));
            } catch (DateTimeParseException e1) {
                System.out.println("Please provide the date in the correct format, which is yyyy-mm-dd");
                return;
            }
        }

    }

    public void revertFormat() {
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        String newFormat = dtFormatter.format(localDate);
        this.at = newFormat;
        try {
            this.localDate =  LocalDate.parse(at.trim());
        } catch (DateTimeParseException e) {
            System.out.println("Please provide the date in the correct format, which is yyyy-mm-dd");
            return;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String newFormat = dtFormatter.format(localDate);
        //String newFormat = localDate.format(DateTimeFormatter.ofPattern("dd mm yyyy"));
        return "[E]" + super.toString() + " (at: " + newFormat + ")";
    }

}
*/


//working ver
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
// java.time.format.DateTimeParseException;
//import java.time.temporal.ChronoUnit;

//public class Event extends Task{
  //  protected String description;
   // protected boolean isDone;
   // protected String at;
    //LocalDate localDate;

   // /**
   //  * Constructor for an Event object
   //  */
//public Event(String description, String at) {
   // super(description);
   // this.at = at;

   // try {
   //     this.localDate =  LocalDate.parse(at);
   // } catch (DateTimeParseException e) {
    //    System.out.println("Please provide the date in the correct format, which is yyyy-mm-dd");
    //    return;
   // }

//}

   // @Override
    //public String toString() {
     //   DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
       // String newFormat = dtFormatter.format(localDate);
       // //String newFormat = localDate.format(DateTimeFormatter.ofPattern("dd mm yyyy"));
        //return "[E]" + super.toString() + " (at: " + newFormat + ")";
    //}

