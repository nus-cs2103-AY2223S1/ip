package carbon.error;

public class CorruptedSavefileException extends CarbonException {
    String data;

    public CorruptedSavefileException(String data) {
        super(data);
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("The savefiles appear to be corrupted.\n"
                + "\"%s\" is not formatted properly.", data);
    }
}
