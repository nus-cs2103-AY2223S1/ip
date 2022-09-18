package duke.utils;

import duke.exceptions.DukeException;
import duke.models.Event;

public class IntervalUtil {
    public static Interval getInterval(String taskInterval) throws DukeException {
        switch (taskInterval) {
            case "D":
                return Interval.Day;
            case "W":
                return Interval.Week;
            case "M":
                return Interval.Month;
            case " ":
                return Interval.None;
            default:
                throw new DukeException("Invalid Interval Flag in storage file, please delete duke.txt and restart.");
        }
    }
}
