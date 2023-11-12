package Duck;
import java.util.List;

/**
 * @param <T> generic type of the List for the objects to store
 */
public class TaskList<T> {
    private List<T> list;

    /**
     * Encapsulates the list of tasks objects that Duck will use
     * @param list List of type T for the TaskList to store,
     *             typically  read from the Storage
     */
    public TaskList(List<T> list){
        this.list = list;
    }


    public void addToList(T item) {
        list.add(item);
    }
    public T removeFromList(int index) {
        return list.remove(index);
    }
    public T get(int index) {
        return list.get(index);
    }
    public int size() {
        return list.size();
    }

    /**
     * Simply prints all items from within the list variable
     * @throws IndexOutOfBoundsException is thrown when the list is empty
     * @return returns the string of all printed items
     */
    public String printList() throws IndexOutOfBoundsException {
        String s = "Here are the items found\n";
        if (list.size() == 0) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < list.size(); i++) {
            s = s.concat(i + 1 + ". " + list.get(i) + "\n");
        }
        return s;
    }

}
