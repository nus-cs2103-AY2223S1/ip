package models;

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
    }
}
