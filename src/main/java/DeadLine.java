public class DeadLine extends Task{
    private String by;
    public DeadLine(String description) throws DukeException{
        super("tempTask");
        try {
            String temp=description.split(" ")[1];
        }catch (IndexOutOfBoundsException ie) {
            throw (new DukeException("OOPS!!! The description of a deadline cannot be empty."));
        }try{
            super.correctDescrition(description.split(" /")[0].split(" ", 2)[1]);
            this.by = description.split("/")[1].split("by ")[1];
        }catch (IndexOutOfBoundsException ie){
            throw(new DukeException("OOPS!!! The description of a deadline is still not complete."));
        }
    }
    @Override
    public String printTask(){
        return "[D]"+super.printTask()+" (by:"+this.by+")";
    }
}
