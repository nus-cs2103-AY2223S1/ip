public class NoSuchCommandException extends Exception{
    public NoSuchCommandException() {
        super("No such command");
    }

    @Override
    public String toString() {
        return "I dont understand this command boooo";
    }
}
