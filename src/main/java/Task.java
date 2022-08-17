public class Task {
    public String content;

    Task (String c) {
        this.content = c;
    }

    @Override
    public String toString() {
        return this.content + "\n";
    }
}
