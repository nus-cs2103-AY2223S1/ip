package models.task;

/**
 * List of valid task types
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

    public static TaskType fromString(String s) {
        if (s.equals(TODO.toString())) {
            return TODO;
        }
        if (s.equals(DEADLINE.toString())) {
            return DEADLINE;
        }
        if (s.equals(EVENT.toString())) {
            return EVENT;
        }
        return null;
    }
}
