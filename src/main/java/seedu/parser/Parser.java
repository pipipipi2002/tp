package seedu.parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.api.Api;
import seedu.commands.AuthCommand;
import seedu.commands.Command;
import seedu.commands.ExitCommand;
import seedu.commands.FavouriteCommand;
import seedu.commands.FilterCommand;
import seedu.commands.FindCommand;
import seedu.commands.HelpCommand;
import seedu.commands.InvalidCommand;
import seedu.commands.ListCommand;
import seedu.commands.UnfavouriteCommand;
import seedu.commands.UpdateCommand;
import seedu.common.CommonData;
import seedu.data.CarparkList;
import seedu.exception.UnneededArgumentsException;
import seedu.files.Favourite;
import seedu.parser.search.Sentence;

/**
 * Class to deal with parsing commands.
 */
public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final String EMPTY_RESPONSE_HEADER = "Empty argument. Valid command(s): \n";
    private static final String INVALID_NUMBER_OF_ARGS_HEADER = "This command only takes exactly %s argument(s). Valid "
        + "command(s): \n";

    private CarparkList carparkList;
    private Api api;
    private Favourite favourite;

    /**
     * Parses user input into command for execution.
     *
     * @param input       full user input string
     * @param api         api of the carpark data
     * @param carparkList carpark List
     * @return the command based on user input
     */
    public Command parseCommand(String input, Api api, CarparkList carparkList, Favourite favourite) {
        this.api = api;
        this.carparkList = carparkList;
        this.favourite = favourite;
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            return new InvalidCommand("Invalid Command");
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments").trim();

        switch (commandWord) {
        case AuthCommand.COMMAND_WORD:
            if (arguments.isEmpty()) {
                return new InvalidCommand(EMPTY_RESPONSE_HEADER + CommonData.AUTH_FORMAT);
            } else if (numberOfArguments(arguments) != AuthCommand.NUMBER_OF_ARGUMENTS) {
                return new InvalidCommand(String.format(INVALID_NUMBER_OF_ARGS_HEADER,
                    AuthCommand.NUMBER_OF_ARGUMENTS) + CommonData.FAVOURITE_FORMAT);
            }
            return prepareAuth(arguments);
        case ExitCommand.COMMAND_WORD:
            return prepareExit(arguments);
        case FavouriteCommand.COMMAND_WORD:
            if (arguments.isEmpty()) {
                return new InvalidCommand(EMPTY_RESPONSE_HEADER + CommonData.FAVOURITE_FORMAT);
            } else if (numberOfArguments(arguments) != FavouriteCommand.NUMBER_OF_ARGUMENTS) {
                return new InvalidCommand(String.format(INVALID_NUMBER_OF_ARGS_HEADER,
                    FavouriteCommand.NUMBER_OF_ARGUMENTS) + CommonData.FAVOURITE_FORMAT);
            }
            return prepareFavourite(arguments);
        case FindCommand.COMMAND_WORD:
            if (arguments.isEmpty()) {
                return new InvalidCommand(EMPTY_RESPONSE_HEADER + CommonData.FIND_FORMAT);
            } else if (numberOfArguments(arguments) != FindCommand.NUMBER_OF_ARGUMENTS) {
                return new InvalidCommand(String.format(INVALID_NUMBER_OF_ARGS_HEADER,
                    FindCommand.NUMBER_OF_ARGUMENTS) + CommonData.FIND_FORMAT);
            }
            return prepareFind(arguments);
        case ListCommand.COMMAND_WORD:
            return prepareList(arguments);
        case FilterCommand.COMMAND_WORD:
            if (arguments.isEmpty()) {
                return new InvalidCommand(EMPTY_RESPONSE_HEADER + CommonData.FILTER_FORMAT);
            }
            return prepareFilter(arguments);
        case UpdateCommand.COMMAND_WORD:
            return prepareUpdate(arguments);
        case UnfavouriteCommand.COMMAND_WORD:
            if (arguments.isEmpty()) {
                return new InvalidCommand(EMPTY_RESPONSE_HEADER + CommonData.UNFAVOURITE_FORMAT);
            }
            return prepareUnfavourite(arguments);
        case HelpCommand.COMMAND_WORD:
            return prepareHelp(arguments);
        default:
            return new InvalidCommand("Invalid Command. ");
        }
    }

    /**
     * To check that user does not input in any parameters for ExitCommand.
     *
     * @param arguments arguments that may be given by the user after the command word
     * @return Command to be carried out
     */
    private Command prepareExit(String arguments) {
        try {
            if (arguments.trim().isEmpty()) {
                return new ExitCommand();
            }
            throw new UnneededArgumentsException("exit");
        } catch (UnneededArgumentsException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * To prepare the arguments to be taken in for Auth Command.
     *
     * @param arguments arguments given by the user after the command word
     * @return command to be carried out
     */
    private Command prepareAuth(String arguments) {
        final String apiKey = arguments.trim();
        return new AuthCommand(api, apiKey);
    }

    /**
     * To prepare the arguments to be taken in for Favourite Command.
     *
     * @param arguments arguments given by the user after the command word
     * @return command to be carried out
     */
    private Command prepareFavourite(String arguments) {
        final String carparkID = arguments.trim();
        return new FavouriteCommand(carparkID, favourite, carparkList);
    }

    /**
     * To prepare the arguments to be taken in for unfavourite Command.
     *
     * @param arguments arguments given by the user after the command word
     * @return command to be carried out
     */
    private Command prepareUnfavourite(String arguments) {
        final String carparkID = arguments.trim();
        return new UnfavouriteCommand(carparkID, favourite, carparkList);
    }

    /**
     * To prepare the arguments to be taken in for Find Command.
     *
     * @param arguments arguments given by the user after the command word
     * @return command to be carried out
     */
    private Command prepareFind(String arguments) {
        final String carparkID = arguments.trim();
        return new FindCommand(carparkID, carparkList);
    }

    /**
     * To check that user does not input in any parameters for ListCommand.
     *
     * @param arguments arguments that may be given by the user after the command word
     * @return Command to be carried out
     */
    private Command prepareList(String arguments) {
        try {
            if (arguments.trim().isEmpty()) {
                return new ListCommand(carparkList);
            }
            throw new UnneededArgumentsException("list");
        } catch (UnneededArgumentsException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * To prepare the arguments to be taken in for Search Command.
     *
     * @param arguments arguments given by the user after the command word
     * @return command to be carried out
     */
    private Command prepareFilter(String arguments) {
        Sentence searchQuery = new Sentence(arguments);
        return new FilterCommand(carparkList, searchQuery);
    }

    /**
     * To check that user does not input in any parameters for UpdateCommand.
     *
     * @param arguments arguments that may be given by the user after the command word
     * @return Command to be carried out
     */
    private Command prepareUpdate(String arguments) {
        try {
            if (arguments.trim().isEmpty()) {
                return new UpdateCommand(api, carparkList);
            }
            throw new UnneededArgumentsException("update");
        } catch (UnneededArgumentsException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * To check that user does not input in any parameters for HelpCommand.
     *
     * @param arguments arguments that may be given by the user after the command word
     * @return Command to be carried out
     */
    private Command prepareHelp(String arguments) {
        try {
            if (arguments.trim().isEmpty()) {
                return new HelpCommand();
            }
            throw new UnneededArgumentsException("help");
        } catch (UnneededArgumentsException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * Returns number of arguments separated by a space.
     *
     * @param input input string to check
     * @return return the number of space-separated words
     */
    public int numberOfArguments(String input) {
        String[] words = input.trim().split("\\s+");
        return words.length;
    }

    public static String[] splitCommandArgument(String input) {
        return input.split("\\s+", 2);
    }
}
