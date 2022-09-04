public class Parser {
    private final UI ui;

    public Parser(UI ui) {
        this.ui = ui;
    }

    public Command parse(String rawInput) {
        String[] input = rawInput.trim().split(" ", 2);
        switch (input[0].trim()) {
        case "list":
            return new Command(CommandsList.LIST);

        case "todo":
            try {
                return new Command(CommandsList.TODO, new String[]{input[1]});
            } catch (ArrayIndexOutOfBoundsException e) {
                this.ui.printErrorMessage("Whoops! todo needs a description of the task Dattebayo!\n'todo <Task>'");
            }
            break;

        case "deadline":
            try {
                String[] splitArgs = input[1].split("/by", 2);
                return new Command(CommandsList.DEADLINE, splitArgs);
            } catch (ArrayIndexOutOfBoundsException e) {
                this.ui.printErrorMessage("Whoops! deadline needs a description of the task and due date Dattebayo!" +
                        "\n'deadline <Task> /by <Due By>'");
            }
            break;

        case "event":
            try {
                String[] splitArgs = input[1].split("/at", 2);
                return new Command(CommandsList.DEADLINE, splitArgs);
            } catch (ArrayIndexOutOfBoundsException e) {
                this.ui.printErrorMessage("Whoops! event needs a description of the task and time Dattebayo!" +
                        "\n'event <Task> /by <Time>'");
            }
            break;

        case "mark":
            try {
                return new Command(CommandsList.MARK, new String[]{input[1]});
            } catch (NumberFormatException e) {
                this.ui.printErrorMessage("Whoops! it seems you your index is not an integer Dattebayo!" +
                        "\n'mark <Index>'");
            } catch (ArrayIndexOutOfBoundsException e) {
                this.ui.printErrorMessage("Whoops! mark needs the index of the item Dattebayo!" +
                        "\n'mark <Index>'");
            }
            break;

        case "unmark":
            try {
                return new Command(CommandsList.UNMARK, new String[]{input[1]});
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Whoops! unmark needs the index of the item Dattebayo!" +
                        "\n'unmark <Index>'");
            }
            break;

        case "delete":
            try {
                return new Command(CommandsList.DELETE, new String[]{input[1]});
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Whoops! delete needs the index of the item Dattebayo!" +
                        "\n'delete <Index>'");
            }
            break;


        case "bye":
            return new Command(CommandsList.BYE);
        }
        return new Command(CommandsList.UNKNOWN);
    }
}
