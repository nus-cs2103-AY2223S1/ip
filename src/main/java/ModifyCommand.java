public class ModifyCommand extends Command{
    enum ModifyType{
        MARK, UNMARK
    }

    private ModifyType modifyType;
    private int index;

    public ModifyCommand(ModifyType modifyType, int index){
        this.modifyType = modifyType;
        this.index = index;
    }

    @Override
    void execute(TaskList tasklist, Ui ui, Storage storage) {
        switch (modifyType) {
            case MARK:
                tasklist.markTask(this.index, storage);
                break;
            case UNMARK:
                tasklist.unmarkTask(this.index, storage);
                break;
        }
    }
}
