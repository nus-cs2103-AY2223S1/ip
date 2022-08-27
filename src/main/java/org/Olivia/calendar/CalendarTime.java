package org.Olivia.calendar;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;

public class CalendarTime {
    // Not using LocalDateTime in case user only specifies one of date/time
    private LocalDate date;
    private LocalTime time;

    // The constructors are set to public for ease of testing
    public CalendarTime(LocalDate date) {
        this.date=date;
        this.time=null;
    }
    public CalendarTime(LocalTime time) {
        this.time=time;
        this.date=null;
    }
    public CalendarTime(LocalDate date, LocalTime time) {
        this.date=date;
        this.time=time;
    }

    private static String stripPaddingBlanks(String input) {
        while (input.charAt(0)==' ') {
            input=input.substring(1);
        }
        while (input.charAt(input.length()-1)==' ') {
            input=input.substring(0, input.length()-1);
        }
        return input;
    }

    private static LocalTime parseTime(String input) throws Exception {
        String[] splitedInput=input.split(":");
        if (splitedInput.length!=2){
            throw new ParseException("cannot parse input time of day, please use format hh:mm for time of day",-1);
        }
        int hr=Integer.parseInt(splitedInput[0]);
        int min=Integer.parseInt(splitedInput[1]);
        return LocalTime.of(hr, min);
    }

    private static LocalDate parseDate(String input) throws Exception {
        String[] splitedInput=input.split("/");
        if (splitedInput.length!=3){
            throw new ParseException("cannot parse input time of date, please use format DD/MM/YYYY for date",-1);
        }
        int day=Integer.parseInt(splitedInput[0]);
        int month=Integer.parseInt(splitedInput[1]);
        int year=Integer.parseInt(splitedInput[2]);
        return LocalDate.of(year,month,day);
    }

    public static CalendarTime parseInput(String input) throws Exception{
        input=stripPaddingBlanks(input);
        //both date (/) and time(:) are present but no blank is present
        //or length of the input string is not expected
        if ((input.length()!=5 && input.length()!=10 && input.length()!=16) ||
                (input.indexOf(":")!=0 && input.indexOf("/")!=0 && input.indexOf(" ")==0)){
            throw new ParseException("cannot parse input time, please use either of the following three formats for time:\n" +
                    "    DD/MM/YYYY hh:mm\n" +
                    "    DD/MM/YYYY\n" +
                    "    hh:mm", -1);
        }
        else if (input.length()==16 && input.indexOf(":")!=0 && input.indexOf("/")!=0){
            String[] splitedInput=input.split(" ");
            return new CalendarTime(parseDate(splitedInput[0]), parseTime(splitedInput[1]));
        }
        else if (input.length()==5 && input.indexOf(":")!=0){
            return new CalendarTime(parseTime(input));
        }
        else if (input.length()==10 && input.indexOf("/")!=0){
            return new CalendarTime(parseDate(input));
        }
        throw new ParseException("Unknown error occurred during parsing, please make sure to use use either of the following three formats for time:\n" +
                "    DD/MM/YYYY hh:mm\n" +
                "    DD/MM/YYYY\n" +
                "    hh:mm", -1);
    }

    public boolean hasOnlyTime(){
        return this.date==null;
    }
    public boolean hasOnlyDate(){
        return this.time==null;
    }

    @Override
    public String toString(){
        if (hasOnlyDate()){
            int year=this.date.getYear();
            int month=this.date.getMonthValue();
            int day=this.date.getDayOfMonth();
            return String.format("%02d", day)+"/"+String.format("%02d", month)+"/"+String.format("%04d", year);
        }
        else if (hasOnlyTime()){
            int hour=this.time.getHour();
            int minute=this.time.getMinute();
            return String.format("%02d", hour)+":"+String.format("%02d", minute);
        }
        int year=this.date.getYear();
        int month=this.date.getMonthValue();
        int day=this.date.getDayOfMonth();
        int hour=this.time.getHour();
        int minute=this.time.getMinute();
        return String.format("%02d", day)+"/"+String.format("%02d", month)+"/"+String.format("%04d", year)+
                " "+String.format("%02d", hour)+":"+String.format("%02d", minute);
    }

    @Override
    public boolean equals(Object other){
        if (other instanceof CalendarTime){
            @SuppressWarnings("unchecked")
            CalendarTime ot=(CalendarTime)other;
            if (this.time==null && ot.time==null){
                return this.date.equals(ot.date);
            }
            else if (this.date==null && ot.date==null){
                return this.time.equals(ot.time);
            }
            else if (this.time!=null && this.date!=null){
                return this.date.equals(ot.date) && this.time.equals(ot.time);
            }
        }
        return false;
    }
}
