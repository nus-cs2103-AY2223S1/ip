package task.stub;

public class MarkedTaskStub extends TaskStub {
    @Override
    protected boolean getIsMarked() {
        return true;
    }

    @Override
    public String encode() {
        return "[X] " + this.getTaskItem();
    }
}
