public class CalendarEntryEvent extends CalendarEntry {
    private CalendarTime startTime;
    private CalendarTime endTime;
    public CalendarEntryEvent(String title, String startTime, String endTime) throws Exception {
        super(title);
        this.startTime = CalendarTime.parseInput(startTime);
        this.endTime = CalendarTime.parseInput(endTime);
    }

    @Override
    public String toString(){
        return "[E]"+super.toString()+" (at: "+this.startTime.toString() +" - "+this.endTime.toString() +")";
    }
}
