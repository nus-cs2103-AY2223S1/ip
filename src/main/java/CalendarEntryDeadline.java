public class CalendarEntryDeadline extends CalendarEntry {
    private CalendarTime time;
    public CalendarEntryDeadline(String title, String time) throws Exception {
        super(title);
        this.time=CalendarTime.parseInput(time);
    }

    @Override
    public String toString(){
        return "[D]"+super.toString()+" (by: "+this.time.toString()+")";
    }
}