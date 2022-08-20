public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    void mark() {
        isDone = true;
        System.out.println(Roofus.LINESEP);
        System.out.println("Nice! I've marked this task as done:\n" +
                this.toString());
        System.out.println(Roofus.LINESEP);
    }
    
    void setDone() {
        this.isDone = true;
    }

    void unmark() {
        isDone = false;
        System.out.println(Roofus.LINESEP);
        System.out.println("OK, I've marked this task as not done yet:\n" +
                this.toString());
        System.out.println(Roofus.LINESEP);
    }

    String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X]"  + description;
        } else {
            return "[ ]" + description;
        }
    }
}
