package org.Olivia.calendar;

import java.util.ArrayList;
import java.util.List;

/**
 * a specific entry in a calendar
 * over the course of the development process, i will try to make this as close to the ics file format as possible
 * @author albertZhangTJ
 */
public class CalendarEntry {
    private String title;
    private boolean isCompleted;
    private List<String> tags;

    public CalendarEntry(String title){
        this.title=title;
        this.isCompleted =false;
        this.tags=new ArrayList<String>();
    }

    public CalendarEntry(String title, List<String> tags){
        this.title=title;
        this.isCompleted =false;
        this.tags=tags;
    }

    public int markAsCompleted(){
        if (this.isCompleted){
            //I know this is not what exactly http status code 208 means
            //but it is the closest one that I can think of
            //to represent the idea "It was already done. Not what u expected but it's alright"
            return 208;
        }
        this.isCompleted =true;
        return 200;
    }

    public int markAsIncomplete(){
        if (!this.isCompleted){
            return 208;
        }
        this.isCompleted =false;
        return 200;
    }

    public String getTitle(){
        return this.title;
    }

    public List<String> getTags(){
        return this.tags;
    }

    public String getTagsAsString(){
        String ans="";
        for (String tag: tags){
            ans=ans+"#"+tag+" ";
        }
        return ans;
    }

    @Override
    public String toString(){
        String ans="";
        if (this.isCompleted){
            ans=ans+"[X] ";
        }
        else {
            ans=ans+"[ ] ";
        }
        return ans+this.title+" "+this.getTagsAsString();
    }

    @Override
    public boolean equals(Object other){
        if (other instanceof  CalendarEntry){
            return this.title.equals(((CalendarEntry)other).title);
        }
        return false;
    }
}
