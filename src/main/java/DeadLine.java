public class DeadLine extends Task{
    private String by;
    public DeadLine(String description){
        super(description.split(" /")[0].split(" ",2)[1]);
        this.by=description.split("/")[1].split("by ")[1];
    }
    @Override
    public String printTask(){
        return "[D]"+super.printTask()+" (by:"+this.by+")";
    }
}
