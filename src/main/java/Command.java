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
        EVENT("event"),
        DELETE("delete");

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
            case MARK: case UNMARK: case DELETE:
                String s = str.replaceAll("[^0-9]", "");
                this.extraInformation = s.equals("") ? null : s;
                break;
            case EVENT: case DEADLINE:
                if (str.contains("/")) {
                    String[] arr = this.message.split("/", 2);
                    this.message = arr[0];
                    this.extraInformation =arr[1];
                }
                break;
        }
    }

    public boolean execution() throws DukeException {
        switch (this.instruction) {
            case EXIT:
                System.out.println("Please don't leave me >_<\nSee you soon!");
                return false;
            case LIST:
                this.storage.iterate();
                return true;
            case MARK:
                this.storage.mark(this.extraInformation);
                return true;
            case UNMARK:
                this.storage.unmark(this.extraInformation);
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
            case DELETE:
                this.storage.delete(this.extraInformation);
                return true;
            case NONE:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }

}
