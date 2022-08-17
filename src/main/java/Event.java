public class Event extends Task{
    private String at;
    public Event(String description){
        super(description.split(" /")[0].split(" ",2)[1]);
        this.at=description.split("/")[1].split("at ")[1];

    }
    @Override
    public String printTask(){
        return "[E]"+super.printTask()+" (at:"+this.at+")";
    }
}
