package scottie.tasks;

/**
 * An Enum for the possible types of Tasks.
 */
enum TaskType {
    DEADLINE('D'),
    EVENT('E'),
    TODO('T');

    // The letter char is used to identify the type of task from the data file
    private final char letter;

    TaskType(char letter) {
        this.letter = letter;
    }

    /**
     * Returns the TaskType associated with the given letter.
     * Returns null if there is no TaskType with the given letter.
     *
     * @param letter The letter of the TaskType to return.
     * @return The TaskType associated with the given letter.
     */
    static TaskType fromLetter(char letter) {
        for (TaskType t : TaskType.values()) {
            if (t.letter == letter) {
                return t;
            }
        }
        return null;
    }
}
