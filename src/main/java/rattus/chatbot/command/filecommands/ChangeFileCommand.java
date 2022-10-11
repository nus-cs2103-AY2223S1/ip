package rattus.chatbot.command.filecommands;

import static rattus.chatbot.common.Message.MESSAGE_CHANGE_FILE;

import java.io.File;

import rattus.chatbot.command.CommandResult;
import rattus.chatbot.data.exception.InvalidInputException;
import rattus.chatbot.storage.Storage;

/**
 * A command that changes the working file to a different file.
 */
public class ChangeFileCommand extends FileCommand {
    /**
     * The command word to invoke this command.
     */
    public static final String COMMAND_WORD = "filechange";

    public ChangeFileCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    protected String buildMessage() {
        messageBuilder.buildLines(MESSAGE_CHANGE_FILE, arguments);
        return messageBuilder.toString();
    }

    @Override
    public CommandResult execute() throws InvalidInputException {
        File file = getFile();
        Storage storage = Storage.of(file);
        duke.setStorage(storage);
        return super.execute();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof ChangeFileCommand;
    }
}
