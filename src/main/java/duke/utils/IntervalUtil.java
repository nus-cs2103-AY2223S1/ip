package duke.utils;

import duke.exceptions.DukeException;
import duke.models.Event;

public class IntervalUtil {
    public static Event.Interval getInterval(String taskInterval) throws DukeException {
        switch (taskInterval) {
            case "D":
                return Event.Interval.Day;
            case "W":
                return Event.Interval.Week;
            case "M":
                return Event.Interval.Month;
            case " ":
                return Event.Interval.None;
            default:
                throw new DukeException("Invalid Interval Flag in storage file, please delete duke.txt and restart.");
        }
    }
}
