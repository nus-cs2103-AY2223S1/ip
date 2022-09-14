package rattus.chatbot.command.filecommands;

import java.io.File;

import rattus.chatbot.command.Command;
import rattus.chatbot.storage.Storage;

/**
 * Encapsulates a command that executes on files.
 */
public abstract class FileCommand extends Command {
    protected File getFile() {
        return new File(String.format("%s/%s.txt", Storage.DATA_STORAGE_PATH_PREFIX, arguments));
    }
}
