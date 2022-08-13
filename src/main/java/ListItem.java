/**
 * Encapsulate the item that user adds to the list
 *
 * @author: Jonas Png
 */
public class ListItem {

    private  String name;

    /**
     * Class constructor for ListItem
     * @param name name of item
     */
    public ListItem(String name) {
        this.name = name;
    }

    /**
     * Method to get the name of the item
     * @return  name of item
     */
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "added: " + name;
    }

}
