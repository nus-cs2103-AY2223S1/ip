import java.time.LocalDateTime;

public class ToDos extends Task {

    private static final String ID = "[T]";

    ToDos(String detail, boolean isDone) {
        super(detail, isDone);
    }
    ToDos(String detail) {
        super(detail);
    }

    @Override
    LocalDateTime getTime() {
        return LocalDateTime.now();
    }
    @Override
    String getId() {
        return ID;
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
        return ID + super.toString();
    }
}
