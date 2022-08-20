import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * This class deals with interactions with the user
 */
public class Ui {
  private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
  private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("HH:mm, E, MMM dd yyyy");
  
  private StorageList storageList;
  private final Scanner SCANNER = new Scanner(System.in);
  private String lastInput;
  private String lastCommand;
  public Ui(StorageList storageList) {
    this.storageList = storageList;
  }
  
  /**
   * Handles the user input and calls the appropriate method
   */
  public String readCommand() {
    String input = SCANNER.nextLine();
    lastInput = input;
    String command = input.split(" ")[0];
    lastCommand = command;
    return command;
  }


  /**
   * Return the formatter for the DateTime
   * @return DateTimeFormatter
   */
  public static DateTimeFormatter getInputFormatter() {
    return INPUT_FORMATTER;
  }
  
  public static DateTimeFormatter getOutputFormatter() {
    return OUTPUT_FORMATTER;
  }
  
  public String getLastInput() {
    return lastInput;
  }
  
  public String getLastCommand() {
    return lastCommand;
  }
}
