package org.Olivia.calendar;

import java.util.ArrayList;
import java.util.List;

/**
 * a specific entry in a calendar
 * over the course of the development process, i will try to make this as close to the ics file format as possible
 *
 * @author albertZhangTJ
 */
public class CalendarEntry {
    private String title;
    private boolean isCompleted;
    private List<String> tags;

    /**
     * Construct a CalendarEntry object with only a title
     * @param title
     */
    public CalendarEntry(String title) {
        this.title = title;
        this.isCompleted = false;
        this.tags = new ArrayList<String>();
    }

    /**
     * Construct a CalendarEntry object with a title and tags
     * @param title
     * @param tags
     */
    public CalendarEntry(String title, List<String> tags) {
        this.title = title;
        this.isCompleted = false;
        this.tags = tags;
    }

    /**
     * mark self as completed
     * @return status for operation (200 for OK)
     */
    public int markAsCompleted() {
        if (this.isCompleted) {
            //I know this is not what exactly http status code 208 means
            //but it is the closest one that I can think of
            //to represent the idea "It was already done. Not what u expected but it's alright"
            return 208;
        }
        this.isCompleted = true;
        return 200;
    }

    /**
     * mark self as incompleted
     * @return status for operation (200 for OK)
     */
    public int markAsIncomplete() {
        if (!this.isCompleted) {
            return 208;
        }
        this.isCompleted = false;
        return 200;
    }

    /**
     * get the title of this entry
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * get the tags of this entry
     * @return the tags
     */
    public List<String> getTags() {
        return this.tags;
    }

    /**
     * get the tags of this entry
     * concatenate all tags into a string
     * @return a string representing the tags
     */
    public String getTagsAsString() {
        String ans = "";
        for (String tag : tags) {
            ans = ans + "#" + tag + " ";
        }
        return ans;
    }

    @Override
    public String toString() {
        String ans = "";
        if (this.isCompleted) {
            ans = ans + "[X] ";
        } else {
            ans = ans + "[ ] ";
        }
        return ans + this.title + " " + this.getTagsAsString();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof CalendarEntry) {
            return this.title.equals(((CalendarEntry) other).title);
        }
        return false;
    }
}
