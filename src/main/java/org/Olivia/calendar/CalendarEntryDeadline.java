package org.Olivia.calendar;
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

    @Override
    public boolean equals(Object other){
        if (other instanceof CalendarEntryDeadline){
            @SuppressWarnings("unchecked")
            CalendarEntryDeadline ot=(CalendarEntryDeadline)other;
            return super.equals(other) && this.time.equals(ot.time);
        }
        return false;
    }
}