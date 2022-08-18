public class ToDo extends Item{
    private final String itemType = "[T]";

    public ToDo(String item) {
        super(item);
    }

    @Override
    public String toString() {
        return this.itemType + super.toString();
    }
}
