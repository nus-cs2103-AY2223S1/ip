package org.Olivia.calendar;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private ArrayList<CalendarEntry> cache;

    public Calendar() {
        this.cache = new ArrayList<>();
    }

    public int addEntry(CalendarEntry to_add) {
        this.cache.add(to_add);
        return 200;
    }

    public CalendarEntry getEntry(int index) {
        return cache.get(index - 1);
    }

    public CalendarEntry deleteEntry(int index) {
        if (index > this.cache.size()) {
            throw new IndexOutOfBoundsException("Es tut mir leid. There is no event " + index + " in the current calendar\n");
        }
        return this.cache.remove(index - 1);
    }

    public int markAsDone(int index) {
        if (index > this.cache.size()) {
            throw new IndexOutOfBoundsException("Es tut mir leid. There is no event " + index + " in the current calendar\n");
            //return 417;
        }
        return this.getEntry(index).markAsCompleted();
    }

    public int markAsUndone(int index) {
        if (index > this.cache.size()) {
            throw new IndexOutOfBoundsException("Es tut mir leid. There is no event " + index + " in the current calendar\n");
            //return 417;
        }
        return this.getEntry(index).markAsIncomplete();
    }

    public int clearAllEntries() {
        this.cache.clear();
        return 200;
    }

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
