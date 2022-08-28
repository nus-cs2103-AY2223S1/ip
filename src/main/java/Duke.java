import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
public class Duke {
    public static void main(String[] args) throws IOException {
        Ui newUser = new Ui();
        newUser.userInput();
        SavingFiles.saveFile(newUser.getTaskArr(), "duke.txt");
    }

}

