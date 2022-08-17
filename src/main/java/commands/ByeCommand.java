public class ByeCommand is Command {

  public ByeCommand(String[] commandArgs) {
    this.commandArgs = commandArgs;
  }

  public boolean performAction() {
    System.out.println("MumBot: Goodbyeeee sweetheart <3");
    return false;
  }
}

