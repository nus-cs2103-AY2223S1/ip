package rabbit.exception;

public class ExportDataException extends RabbitException {
    @Override
    public String toString() {
        return "Failed to write one or more lines from" +
                "the list into data.txt due to wrong format of tasks.";
    }
}
