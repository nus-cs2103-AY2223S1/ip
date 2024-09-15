package duke.utils;

/**
 * An enum class containing interval enums of a Task.
 */
public enum Interval {
    Day,
    Week,
    Month,
    None;

    @Override
    public String toString() {
        switch(this) {
        case Day: return "D";
        case Week: return "W";
        case Month: return "M";
        case None: return " ";
        default: throw new IllegalArgumentException();
        }
    }
}
