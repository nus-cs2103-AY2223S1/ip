package duke.items;

import duke.items.Item;
import duke.items.ItemTypes;

public class ToDo extends Item {

    public ToDo(String name) {
        super(name, ItemTypes.TODO, null);
    }

    public ToDo(String name, boolean isDone) {
        super(name, isDone, ItemTypes.TODO, null);
    }

    @Override
    public String toString() {
        return super.getItemType() + super.toString();
    }
}
