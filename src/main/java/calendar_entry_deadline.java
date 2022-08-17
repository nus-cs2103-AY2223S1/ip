public class calendar_entry_deadline extends  calendar_entry{
    private String time;
    public calendar_entry_deadline(String title, String time){
        super(title);
        this.time=time;
    }

    @Override
    public String toString(){
        return "[D]"+super.toString()+" (by: "+this.time+")";
    }
}