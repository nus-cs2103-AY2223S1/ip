public class Test {
    public enum Command {
        TODO, LIST
    };

    public static Command reader(String command) {
        return Command.valueOf(command.toUpperCase());
    }
    public static void main(String[] args) {
        System.out.println(reader("tODo"));
    }
}
