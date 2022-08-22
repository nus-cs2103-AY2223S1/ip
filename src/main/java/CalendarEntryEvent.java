public class CalendarEntryEvent extends CalendarEntry {
    private String startTime;
    private String endTime;
    public CalendarEntryEvent(String title, String startTime, String endTime){
        super(title);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString(){
        return "[E]"+super.toString()+" (at: "+this.startTime +" - "+this.endTime +")";
    }
}
