class Task {
    String description;
    boolean isDone = false;

    public Task(String text) {
        description = text;
    }

    public void changeMark(boolean arg) {
        isDone = arg;
    }

    @Override
    public String toString() {
        String res = "";

        if(isDone) {
            res += "[X] ";
        } else {
            res += "[ ] ";
        }

        res += this.description;
        return res;
    }
}
