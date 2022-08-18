public class Naruto {
    private final Item[] storedItems;
    private int counter;

    public Naruto() {
        this.storedItems = new Item[100];
        this.counter = -1;
    }

    private String addItem(Item item) {
        this.counter++;
        this.storedItems[this.counter] = item;
        return "    Alright! I've added it to our list:\n      " + item.toString()
                + "\n    Now we have " + (this.counter + 1) + " tasks in our list Dattebayo!";
    }

    public String addToDo(String item) {
        ToDo todo = new ToDo(item);
        return this.addItem(todo);
    }

    public String addDeadline(String item, String due) {
        Deadline deadline = new Deadline(item, due);
        return this.addItem(deadline);
    }

    public String addEvent(String item, String at) {
        Event event = new Event(item, at);
        return this.addItem(event);
    }

    public String getList() {
        if (counter < 0) {
            return "    The list is currently empty Dattebayo!";
        }
        StringBuilder list = new StringBuilder("    Here's the list you asked for Dattebayo:");
        for (int count = 0; count <= counter; count++){
            list.append("\n").append("    ").append(count + 1).append(".").append(storedItems[count].toString());
        }
        return list.toString();
    }

    public String markItem(int item) {
        if (item > this.counter || item < 0) {
            return "Whoops it appears you entered an invalid index, there are " + (this.counter + 1)
                    + " items in the list Dattebayo!";
        }
        this.storedItems[item].setDone();
        return "    Alright! I've marked this task as done Dattebayo:\n  " + this.storedItems[item].toString();
    }

    public String unMarkItem(int item) {
        if (item > this.counter || item < 0) {
            return "Whoops it appears you entered an invalid index, there are " + (this.counter + 1)
                    + " items in the list Dattebayo!";
        }
        this.storedItems[item].setUnDone();
        return "    Alright! I've marked this task as not done yet Dattebayo: \n  " + this.storedItems[item].toString();
    }

    public String bye() {
        return "    Looks like this is the end for now, till we meet again! Ja Ne!";
    }

    public static int string2Int(String input) throws NumberFormatException {
        return Integer.parseInt(input);
    }
}
