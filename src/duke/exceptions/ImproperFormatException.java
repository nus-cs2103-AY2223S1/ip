package duke.exceptions;

public class ImproperFormatException extends DukeException {

    public ImproperFormatException() {
        super("PLEASE FORMAT CORRECTLY\n" +
                "todo: todo (task)\n" +
                "i.e todo clean home\n\n" +
                "deadline: deadline (task) /by yyyy-mm-dd hh:mm\n" +
                "i.e deadline submit homework /by 2020-12-31 08:45\n\n" +
                "event: event (task) /at yyyy-mm-dd hh:mm\n" +
                "i.e event dinner /at 2021-10-22 23:45"
                );
    }
}
