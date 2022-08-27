package task.stub;

public class UnmarkedTaskStub extends TaskStub {
    @Override
    protected boolean getIsMarked() {
        return false;
    }

    @Override
    public String encode() {
        return "[ ] " + this.getTaskItem();
    }
}
