public abstract class Command implements Actionable {

    CommandType commandType;

    Command(CommandType commandType) {
        this.commandType = commandType;
    }
}
