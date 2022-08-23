public class Ui {
  private final String logo = "_____________             .___\n" + "\\_   _____/  | ____ __  __| _/\n" + 
  " |    __)_|  |/ /  |  \\/ __ |\n" + " |        \\    <|  |  / /_/ | \n" + "/_______  /__|_ \\____/\\____ | \n" + 
  "        \\/     \\/          \\/ \n";

  public Ui() {

  }

  private String indentMessage(String message) {
    StringBuilder builder = new StringBuilder("    ");
    for (int i = 0; i < message.length(); i++) {
      builder.append(message.charAt(i));
      if (message.charAt(i) == '\n') {
        builder.append("    ");
      }
    }
    return builder.toString();
  }

  public void sendMessage(String message) {
    String divider = "___________________________________";
    System.out.println(indentMessage(divider + "\n" + message + "\n" + divider + "\n"));
  }

  public void showErrorMessage(EkudException exception) {
    this.sendMessage(exception.getMessage());
  }

  public void greet() {
    this.sendMessage("Hello from\n" + this.logo + "What can I do for you?");
  }

}
