import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Command {

    private enum Instruction {
        EXIT("bye"),
        LIST("list"),
        UNMARK("unmark"),
        MARK("mark"),
        ADD("add");

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
    private int index;
    private Storage storage;

    public Command(String str, Storage storage) throws NoSuchElementException, NumberFormatException {
        this.message = str;
        this.keyword = str.contains(" ") ? str.split(" ")[0] : str;
        try {
            this.instruction = Instruction.get(keyword).get();
            this.index = Integer.parseInt(str.replaceAll("[^0-9]", "")) - 1;
        } catch (NoSuchElementException e) {
            System.out.println("Command not found.");
        } catch (NumberFormatException e) {
            this.index = 0;
        }
        this.storage = storage;
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
                this.storage.mark(this.index);
                return true;
            case UNMARK:
                this.storage.unmark(this.index);
                return true;
            default:
                this.storage.add(new Task(this.message));
                return true;
        }
    }

}
