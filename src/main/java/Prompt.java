public class Prompt {
    public static void startPrompt() {
        String logo = "____    ____  __     ___   ___  __       ___       __   __\n"
                + "\\   \\  /   / |  |    \\  \\ /  / |  |     /   \\     |  \\ |  |\n"
                + " \\   \\/   /  |  |     \\  V  /  |  |    /  ^  \\    |   \\|  |\n"
                + "  \\_    _/   |  |      >   <   |  |   /  /_\\  \\   |  . `  |\n"
                + "    |  |     |  |     /  .  \\  |  |  /  _____  \\  |  |\\   |\n"
                + "    |__|     |__|    /__/ \\__\\ |__| /__/     \\__\\ |__| \\__|\n";
        System.out.println("Hi from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("(Task name) / list / check (index) / uncheck (index) / bye");
        lineDivider();
    }

    public static void endPrompt() {
        String goodbye_text = "  _______   ______     ______    _______      ______   ____    ____  _______\n"
                + " /  _____| /  __  \\   /  __  \\  |       \\    |   _  \\  \\   \\  /   / |   ____|\n"
                + "|  |  __  |  |  |  | |  |  |  | |  .--.  |   |  |_)  |  \\   \\/   /  |  |__\n"
                + "|  | |_ | |  |  |  | |  |  |  | |  |  |  |   |   _  <    \\_    _/   |   __|\n"
                + "|  |__| | |  `--'  | |  `--'  | |  '--'  |   |  |_)  |     |  |     |  |____\n"
                + " \\______|  \\______/   \\______/  |_______/    |______/      |__|     |_______|\n";
        System.out.println(goodbye_text);
    }

    public static void lineDivider() {
        System.out.println("════════════════════════════ ⋆★⋆ ════════════════════════════");
    }
}
