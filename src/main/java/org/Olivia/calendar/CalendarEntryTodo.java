package org.Olivia.calendar;
public class CalendarEntryTodo extends CalendarEntry {

    public CalendarEntryTodo(String title){
        super(title);
    }

    @Override
    public String toString(){
        return "[T]"+super.toString();
    }
}