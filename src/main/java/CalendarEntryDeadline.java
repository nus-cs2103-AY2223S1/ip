public class CalendarEntryDeadline extends CalendarEntry {
    private String time;
    public CalendarEntryDeadline(String title, String time){
        super(title);
        this.time=time;
    }

    @Override
    public String toString(){
        return "[D]"+super.toString()+" (by: "+this.time+")";
    }
}