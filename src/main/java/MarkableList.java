public class MarkableList {
    private static final int LIST_SIZE = 100;
    private String[] items = new String[LIST_SIZE];
    private Boolean[] itemMarked = new Boolean[LIST_SIZE];
    private int numOfElements = 0;

    public MarkableList() {
        for (int i = 0; i < LIST_SIZE; i++) {
            items[i] = null;
            itemMarked[i] = false;
        }
    }

    public String insertItem(String newItem) 
            throws ArrayIndexOutOfBoundsException {
        if (numOfElements == LIST_SIZE) {
            throw new ArrayIndexOutOfBoundsException();
        }
        items[numOfElements] = newItem;
        numOfElements += 1;
        return "Added " + newItem;
    }

    public String markItem(int index) 
            throws ArrayIndexOutOfBoundsException {
        index -= 1;
        if (index >= numOfElements) {
            throw new ArrayIndexOutOfBoundsException();
        }
        itemMarked[index] = true;
        return "Nice! I've marked this task as done:\n\t    [X] " + items[index];
    }

    public String unmarkItem(int index) 
            throws ArrayIndexOutOfBoundsException {
        index -= 1;
        if (index >= numOfElements) {
            throw new ArrayIndexOutOfBoundsException();
        }
        itemMarked[index] = false;
        return "OK, I've marked this task as not done yet:\n\t    [ ] " + items[index];
    }

    /**
     * Print the output in customised format.
     * @param list The list to print
     */ 
    @Override
    public String toString() {
        String res = ("Here are the tasks in your list:");
        for (int i = 0; i < numOfElements; i++) {
            if (items[i] == null) {
                break;
            }
            res += String.format("\n\t  %d.[%c] %s", 
                    i + 1, 
                    itemMarked[i] ? 'X' : ' ', 
                    items[i]);
        }
        return res;
    }
}
