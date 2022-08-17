public class Task {
    private String name;

    Task(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
