public class ToDo extends Item{

    public ToDo(String name) {
        super(name, itemType.TODO, null);
    }

    public ToDo(String name, boolean isDone) {
        super(name, isDone, itemType.TODO, null);
    }

    @Override
    public String toString() {
        return super.getItemType() + super.toString();
    }
}
