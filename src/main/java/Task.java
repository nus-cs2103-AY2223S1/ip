public class Task {
    private final String detail;

    Task(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return this.detail;
    }
}
