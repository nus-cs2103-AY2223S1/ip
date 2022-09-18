package duke.models.task;

/**
 * List of valid task types.
 *
 * @author Emily Ong Hui Qi
 */
public enum TaskType {
    TODO {
        @Override
        public String toString() {
            return "T";
        }
    },
    DEADLINE {
        @Override
        public String toString() {
            return "D";
        }
    },
    EVENT {
        @Override
        public String toString() {
            return "E";
        }
    };

    /**
     * Returns a corresponding TaskType from the given string.
     *
     * @param str The received string input.
     *
     * @return The corresponding TaskType.
     */
    public static TaskType fromString(String str) {
        if (str.equals(TODO.toString())) {
            return TODO;
        }
        if (str.equals(DEADLINE.toString())) {
            return DEADLINE;
        }
        if (str.equals(EVENT.toString())) {
            return EVENT;
        }
        return null;
    }
}
