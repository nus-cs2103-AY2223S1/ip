package ekud.task;

public enum TaskType {
    TODO, DEADLINE, EVENT;

    /**
     * Gets string representation of task type (lower case).
     * 
     * @return String representation of task type.
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
