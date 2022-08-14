public enum UserOperation {
    MARK;

    public String getOperation() {
        switch (this) {
            case MARK:
                return "mark";

            default:
                return "I can't tell what this is!";
        }
    }
}
