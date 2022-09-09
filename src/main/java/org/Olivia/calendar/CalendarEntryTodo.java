package org.Olivia.calendar;

import java.util.List;

/**
 * represents a to-do on the calendar (i.e. a task with no time information)
 * It takes all the parameters as Strings and parse them using CalendarTime.parseInput()
 * @author ZHANG TONGJUN (albertZhangTJ)
 */
public class CalendarEntryTodo extends CalendarEntry {

    public CalendarEntryTodo(String title){
        super(title);
    }

    public CalendarEntryTodo(String title, List<String> tags){
        super(title, tags);
    }

    @Override
    public String toString(){
        return "[T]"+super.toString();
    }
}