public enum TaskType {
    TODO,
    DEADLINE,
    EVENT;

    public String getTaskSymbol() {
        switch (this) {
            case TODO: return "T";
            case DEADLINE: return "D";
            case EVENT: return "E";
        }
        return null;
    }
}
