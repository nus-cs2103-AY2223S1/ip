package models.task;

public class ConcreteTask extends Task {
    public ConcreteTask(String description) {
        super(description);
    }

    @Override
    protected String getTypeIndicator() {
        return "T";
    }
}
