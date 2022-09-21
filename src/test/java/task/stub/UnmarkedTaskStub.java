package task.stub;

/**
 * A task stub that is unmarked
 *
 * @author Bryan Lim Jing Xiang
 */
public class UnmarkedTaskStub extends TaskStub {
    @Override
    protected boolean getIsMarked() {
        return false;
    }

    /**
     * Overrides the Task encoding to always return a unmarked task's encoding
     *
     * @return Encoding of an unmarked Task stub
     */
    @Override
    public String encode() {
        return "[ ] " + this.getTaskItem();
    }
}
