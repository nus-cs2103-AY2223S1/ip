package org.Olivia.calendar;
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

    @Override
    public boolean equals(Object other){
        if (other instanceof CalendarEntryEvent){
            @SuppressWarnings("unchecked")
            CalendarEntryEvent ot=(CalendarEntryEvent)other;
            return super.equals(other) && this.startTime.equals(ot.startTime) && this.endTime.equals(ot.endTime);
        }
        return false;
    }
}
