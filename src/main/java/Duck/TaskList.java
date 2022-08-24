package Duck;
import java.util.List;

public class TaskList<T> {
    private List<T> list;

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
    public void printList() throws IndexOutOfBoundsException {
        if (list.size() == 0) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
    }

}
