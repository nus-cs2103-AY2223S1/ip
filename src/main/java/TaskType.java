public enum TaskType {
    TODO,
    DEADLINE,
    EVENT;

    public String getTaskType() {
        switch (this) {
            case TODO: return "a todo";
            case DEADLINE: return "a deadline";
            case EVENT: return "an event";
        }
        return null;
    }
}
