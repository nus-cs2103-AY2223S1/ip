public class Event extends Task{
    private String at;
    public Event(String description) throws DukeException{
        super("tempTask");
        try {
            String temp=description.split(" ")[1];
        }catch (IndexOutOfBoundsException ie) {
            throw (new DukeException("OOPS!!! The description of a event cannot be empty."));
        }
        try {
            super.correctDescrition(description.split(" /")[0].split(" ", 2)[1]);
            this.at = description.split("/")[1].split("at ")[1];
        }catch (IndexOutOfBoundsException ie){
            throw(new DukeException("OOPS!!! The description of a event is still not complete."));
        }

    }
    @Override
    public String printTask(){
        return "[E]"+super.printTask()+" (at:"+this.at+")";
    }
}
