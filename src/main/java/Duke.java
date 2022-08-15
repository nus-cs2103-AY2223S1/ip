public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        ItemList itemList = new ItemList();
        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            switch (parseInput(userInput)) {
                case EXIT:
                    System.out.println("Bye. Hope to see you again soon!\n");
                    return;

                case ADD_TO_LIST:
                    itemList.addItem(userInput);
                    break;

                case PRINT_LIST:
                    itemList.printList();
                    break;

                case NONE:
                    // do nothing
                    break;

                default:
                    // do nothing
            }
        }
    }

    private enum InstructionType {
        NONE,
        EXIT,
        ADD_TO_LIST,
        PRINT_LIST,
    }

    private static InstructionType parseInput(String input) {
        switch (input.trim()) {
            case "":
                return InstructionType.NONE;

            case "bye":
                return InstructionType.EXIT;

            case "list":
                return InstructionType.PRINT_LIST;

            default:
                return InstructionType.ADD_TO_LIST;
        }
    }

    private static class ItemList {
        private java.util.ArrayList<String> itemList;

        public ItemList() {
            this.itemList = new java.util.ArrayList<String>();
        }

        public void addItem(String item) {
            itemList.add(item);
            System.out.println("added: " + item);
        }

        public void printList() {
            for (int i = 0; i < itemList.size(); i += 1) {
                System.out.println((i + 1) + ". " + itemList.get(i));
            }
        }
    }
}
