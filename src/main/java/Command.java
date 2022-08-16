import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Command {

    private enum Instruction {
        NONE("none"),
        EXIT("bye"),
        LIST("list"),
        UNMARK("unmark"),
        MARK("mark"),
        DEADLINE("deadline"),
        TODO("todo"),
        EVENT("event");

        private String value;

        private Instruction(String value) {
            this.value = value;
        }

        public static Optional<Instruction> get(String str) {
            return Arrays.stream(Instruction.values())
                    .filter(instruction -> instruction.value.equals(str))
                    .findFirst();
        }
    }
    private Instruction instruction;
    private String message;
    private String keyword;
    private String extraInformation;
    private Storage storage;

    public Command(String str, Storage storage) throws NoSuchElementException, NumberFormatException {
        extractInformation(str);
        this.storage = storage;
    }

    public void extractKeyword(String str) {
        String[] arr = str.split(" ", 2);
        this.keyword = arr[0];
        this.message = str.contains(" ") ? arr[1] : null;
        this.instruction = Instruction.get(keyword).orElse(Instruction.NONE);
    }

    public void extractInformation(String str) {
        extractKeyword(str);
        switch (this.instruction) {
            case MARK: case UNMARK:
                this.extraInformation = str.replaceAll("[^0-9]", "");
                break;
            case EVENT:
                if (str.contains("/")) {
                    this.extraInformation = this.message.split(" /at ", 2)[1];
                    this.message = this.message.split("/")[0];
                } else {
                    System.out.println("Wrong input format.");
                    System.out.println("Please enter /at followed by the date.");
                }
                break;
            case DEADLINE:
                if (str.contains("/")) {
                    this.extraInformation = this.message.split(" /by ", 2)[1];
                    this.message = this.message.split("/")[0];
                } else {
                    System.out.println("Wrong input format.");
                    System.out.println("Please enter /by followed by the date.");
                }
        }
    }

    public boolean execution() {
        switch (this.instruction) {
            case EXIT:
                System.out.println("Please don't leave me >_<\nSee you soon!");
                return false;
            case LIST:
                this.storage.iterate();
                return true;
            case MARK:
                int index = Integer.parseInt(this.extraInformation) - 1;
                this.storage.mark(index);
                return true;
            case UNMARK:
                int number = Integer.parseInt(this.extraInformation) - 1;
                this.storage.unmark(number);
                return true;
            case TODO:
                Task task1 = new ToDo(this.message);
                this.storage.add(task1);
                return true;
            case DEADLINE:
                Task task2 = new Deadline(this.message, this.extraInformation);
                this.storage.add(task2);
                return true;
            case EVENT:
                Task task3 = new Event(this.message, this.extraInformation);
                this.storage.add(task3);
                return true;
        }
        return true;
    }

}
