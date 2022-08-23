public enum TaskType {
    DEADLINE('D'),
    EVENT('E'),
    TODO('T');

    // The letter char is used to identify the type of task from the data file
    private final char letter;

    TaskType(char letter) {
        this.letter = letter;
    }

    public static TaskType fromLetter(char letter) {
        for (TaskType t : TaskType.values()) {
            if (t.letter == letter) {
                return t;
            }
        }
        return null;
    }
}
