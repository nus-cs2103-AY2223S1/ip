package task.stub;

/**
 * A task stub that is marked
 *
 * @author Bryan Lim Jing Xiang
 */
public class MarkedTaskStub extends TaskStub {
    @Override
    protected boolean getIsMarked() {
        return true;
    }

    /**
     * Overrides the Task encoding to always return a marked task's encoding
     *
     * @return Encoding of a marked Task stub
     */
    @Override
    public String encode() {
        return "[X] " + this.getTaskItem();
    }
}
