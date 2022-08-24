import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends ListObject{

    String doBy;

    public Deadline(String task, int status) {
        super(task, status);
        this.doBy= "untimed";
    }

    public Deadline(String task, int status, String doBy){
        super(task, status);
        this.doBy = doBy;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + formatDateTime(doBy) + ")";
    }

    public String formatDateTime(String txt){
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate deadline = LocalDate.parse(txt, formatter);
        DateTimeFormatter formatNew = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedTxt = deadline.format(formatNew);
        return formattedTxt;
    }

}
