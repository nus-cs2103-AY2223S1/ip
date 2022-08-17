import java.util.Scanner;

public class Duke {
    private final Item[] storedItems;
    private int counter;
    public String invalidArg = "    That's an invalid argument Dattebayo!";
    private final String done = "[X]";
    private final String unDone = "[ ]";


    protected static class Item {
        private boolean done = false;
        private final String item;

        protected Item(String item) {
            this.item = item;
        }

        protected String getItem() {
            return this.item;
        }

        protected boolean isDone() {
            return this.done;
        }

        protected void setDone() {
            this.done = true;
        }

        protected void setUnDone() {
            this.done = false;
        }
    }

    public Duke() {
        this.storedItems = new Item[100];
        this.counter = 0;
    }

    public String addItem(String item) {
        this.storedItems[this.counter] = new Item(item);
        this.counter++;
        return "    Alright! I've added '" + item + "' to our list Dattebayo!";
    }

    public String getList() {
        if (counter <= 0) {
            return "    The list is currently empty Dattebayo!";
        }

        StringBuilder list = new StringBuilder("    Here's the list you asked for Dattebayo:");
        for (int count = 0; count < counter; count++){
            String isDone = storedItems[count].isDone() ? this.done : this.unDone;
            list.append("\n").append("    ").append(count + 1).append(". ")
                    .append(isDone).append(" ").append(storedItems[count].getItem());
        }
        return list.toString();
    }

    public String markItem(int item) {
        if (item >= this.counter || item < 0) {
            return this.invalidArg;
        }
        this.storedItems[item].setDone();
        return "Alright! I've marked this task as done Dattebayo: \n  "
                + this.done + " " + this.storedItems[item].getItem();
    }

    public String unMarkItem(int item) {
        if (item >= this.counter || item < 0) {
            return this.invalidArg;
        }
        this.storedItems[item].setUnDone();
        return "Alright! I've marked this task as not done yet Dattebayo: \n  "
                + this.unDone + " " + this.storedItems[item].getItem();
    }

    public String bye() {
        return "    Looks like this is the end for now, till we meet again! Ja Ne!";
    }

    public static int string2Int(String input) throws NumberFormatException {
        return Integer.parseInt(input);
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Naruto and one day I will be Hokage! \nWhat can i do for you?");
        Scanner scanner = new Scanner(System.in);
        Duke naruto = new Duke();
        String lineBreak = "━".repeat(20);

        boolean breakLoop = false;
        while (!breakLoop) {
            String rawInput = scanner.nextLine().trim();
            String[] input = rawInput.split(" ", 2);
            System.out.println(lineBreak);
            switch (input[0]) {
                case "list":
                    System.out.println(naruto.getList());
                    break;

                case "mark":
                    try {
                        int item = Duke.string2Int(input[1]);
                        System.out.println(naruto.markItem(item - 1));
                    } catch(NumberFormatException e) {
                        System.out.println(naruto.invalidArg);
                    }
                    break;

                case "unmark":
                    try {
                        int item = Duke.string2Int(input[1]);
                        System.out.println(naruto.unMarkItem(item - 1));
                    } catch(NumberFormatException e) {
                        System.out.println(naruto.invalidArg);
                    }
                    break;

                case "bye":
                    System.out.println(naruto.bye());
                    breakLoop = true;
                    break;

                default:
                    System.out.println(naruto.addItem(rawInput));
            }
            System.out.println(lineBreak);
        }

    }
}
