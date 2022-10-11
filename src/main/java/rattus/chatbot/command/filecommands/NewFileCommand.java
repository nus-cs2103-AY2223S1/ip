package rattus.chatbot.command.filecommands;

import static rattus.chatbot.common.Message.MESSAGE_FILE_ALREADY_EXISTS;
import static rattus.chatbot.common.Message.MESSAGE_NEW_FILE;

import java.io.File;
import java.io.IOException;

import rattus.chatbot.command.CommandResult;
import rattus.chatbot.data.exception.InvalidInputException;

/**
 * A command that creates a new file.
 */
public class NewFileCommand extends FileCommand {
    /**
     * The command word to invoke this command.
     */
    public static final String COMMAND_WORD = "filenew";

    public NewFileCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Creates a new file.
     */
    public void createFile() {
        try {
            File file = getFile();
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected String buildMessage() {
        messageBuilder.buildLines(MESSAGE_NEW_FILE, arguments);
        return messageBuilder.toString();
    }

    @Override
    public CommandResult execute() throws InvalidInputException {
        File file = getFile();
        if (file.exists()) {
            throw new InvalidInputException(MESSAGE_FILE_ALREADY_EXISTS);
        }
        createFile();
        return super.execute();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof NewFileCommand;
    }
}
