public class ToDos extends Task {
    ToDos(String detail, boolean isDone) {
        super(detail, isDone);
    }
    ToDos(String detail) {
        super(detail);
    }

    @Override
    public Task markDone() {
        return new ToDos(super.getDetail(), true);
    }

    @Override
    public Task unmarkDone() {
        return new ToDos(super.getDetail(), false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
