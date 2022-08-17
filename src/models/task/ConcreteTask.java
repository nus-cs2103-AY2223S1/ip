package models.task;

public class ConcreteTask extends Task {
    public ConcreteTask(String description) {
        super(description);
    }

    @Override
    String getTypeIndicator() {
        return "T";
    }
}
