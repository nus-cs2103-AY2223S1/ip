import java.time.LocalDate;

public class DeadLine extends Task{
    private String by;
    private String description;
    public DeadLine(String description) throws DukeException{
        super("tempTask");
        try {
            String temp=description.split(" ")[1];
        }catch (IndexOutOfBoundsException ie) {
            throw (new DukeException("OOPS!!! The description of a deadline cannot be empty."));
        }try{
            super.correctDescrition(description.split("by")[0].split(" ", 2)[1]);
            super.getFullDescription(description.split(" ",2)[1]);
            this.by = description.split("by")[1];
        }catch (IndexOutOfBoundsException ie){
            throw(new DukeException("OOPS!!! The description of a deadline is still not correct."));
        }
        this.description=description;
    }
    @Override
    public String printTask(){
        try {
            return "[D]" + super.printTask() + " (by:" + super.showTime() + ")";
        }catch (DukeException d){
            return "[D]" + super.printTask() + " (by:"
                    + this.by + ")";
        }
    }
    @Override
    public String getDescription(){
        return this.description;
    }
}
