public abstract class Item {
    private boolean isDone = false;
    private final String done = "[X] ";
    private final String unDone = "[ ] ";
    private final String item;

    public Item(String item) {
        this.item = item;
    }

    protected String getItem() {
        return this.item;
    }

    protected boolean isDone() {
        return this.isDone;
    }

    protected void setDone() {
        this.isDone = true;
    }

    protected void setUnDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return (this.isDone ? this.done : this.unDone) + this.item;
    }
}
