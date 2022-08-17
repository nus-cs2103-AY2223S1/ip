public class calendar_entry_event extends  calendar_entry{
    private String start_time;
    private String end_time;
    public calendar_entry_event(String title, String start_time,String end_time){
        super(title);
        this.start_time=start_time;
        this.end_time=end_time;
    }

    @Override
    public String toString(){
        return "[E]"+super.toString()+" (at: "+this.start_time+" - "+this.end_time+")";
    }
}
