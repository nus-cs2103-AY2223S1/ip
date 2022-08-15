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
        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            switch (parseInput(userInput)) {
                case BYE:
                    System.out.println("Bye. Hope to see you again soon!\n");
                    return;

                case ECHO:
                    System.out.println(userInput);
                    break;

                default:
                    // do nothing
            }
        }
    }

    private enum InstructionType {
        BYE,
        ECHO
    }

    private static InstructionType parseInput(String input) {
        switch (input.trim()) {
            case "bye":
                return InstructionType.BYE;

            default:
                return InstructionType.ECHO;
        }
    }
}
