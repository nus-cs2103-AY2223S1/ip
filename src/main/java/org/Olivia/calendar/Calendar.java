package org.Olivia.calendar;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private ArrayList<CalendarEntry> cache;

    public Calendar() {
        this.cache = new ArrayList<>();
    }

    /**
     * add an entry to the calendar
     *
     * @param to_add
     * @return
     */
    public int addEntry(CalendarEntry to_add) {
        this.cache.add(to_add);
        return 200;
    }

    /**
     * return an entry marked by the index
     *
     * @param index index of the entry, start from 1
     * @return
     */
    public CalendarEntry getEntry(int index) {
        return cache.get(index - 1);
    }

    /**
     * delete an entry from the calendar
     *
     * @param index
     * @return
     */
    public CalendarEntry deleteEntry(int index) {
        if (index > this.cache.size()) {
            throw new IndexOutOfBoundsException("Es tut mir leid. There is no event " + index + " in the current calendar\n");
        }
        return this.cache.remove(index - 1);
    }

    /**
     * mark an entry in the calendar as complete
     *
     * @param index index of the event, starting from 1
     * @return
     */
    public int markAsDone(int index) {
        if (index > this.cache.size()) {
            throw new IndexOutOfBoundsException("Es tut mir leid. There is no event " + index + " in the current calendar\n");
            //return 417;
        }
        return this.getEntry(index).markAsCompleted();
    }

    /**
     * mark an entry in the calendar as incomplete
     *
     * @param index index of the event, starting from 1
     * @return
     */
    public int markAsUndone(int index) {
        if (index > this.cache.size()) {
            throw new IndexOutOfBoundsException("Es tut mir leid. There is no event " + index + " in the current calendar\n");
            //return 417;
        }
        return this.getEntry(index).markAsIncomplete();
    }

    /**
     * Delete all entries in the calendar
     * !!!DESTRUCTIVE!!!
     *
     * @return
     */
    public int clearAllEntries() {
        this.cache.clear();
        return 200;
    }

    /**
     * return all entries that contains a keywords
     *
     * @param keyword
     * @return a list of CalendarEntry
     */
    public List<CalendarEntry> getEntriesContains(String keyword) {
        List<CalendarEntry> ans = new ArrayList<>();
        for (CalendarEntry e : this.cache) {
            if (e.toString().indexOf(keyword) != -1) {
                ans.add((e));
            }
        }
        return ans;
    }

    public int size() {
        return this.cache.size();
    }

    @Override
    public String toString() {
        String ans = "";
        int index = 1;
        for (CalendarEntry e : this.cache) {
            ans = ans + index + ". " + e.toString() + "\n";
            index++;
        }
        return ans;
    }
}
