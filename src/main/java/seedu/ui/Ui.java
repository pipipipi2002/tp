package seedu.ui;

import java.util.Scanner;

/**
 * Represents a user interface that handles input and output with the user.
 */
public class Ui {

    private Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Print string to command line.
     *
     * @param line String to print.
     */
    public void print(String line) {
        System.out.println(line);
    }

    /**
     * Asks for input and returns user input.
     *
     * @return User input.
     */
    public String getLine() {
        return in.nextLine();
    }

    /**
     * Asks user to enter a command and returns user input.
     *
     * @return User input.
     */
    public String getCommand() {
        print("Enter a command:");
        return getLine();
    }

    /**
     * Prints ASCII art of a car and parKING logo.
     */
    //@@author eehongchan-reused
    //Reused from https://www.asciiart.eu/vehicles/cars and http://patorjk.com/software/taag/
    // with minor modifications
    private void showLogo() {
        print(
            "    ____\n"
                + " __/  |_\\_\n"
                + "|  _     _``-.\n"
                + "'-(_)---(_)--'   _  _____ _  _  ___ \n"
                + "  _ __  __ _ _ _| |/ /_ _| \\| |/ __|\n"
                + " | '_ \\/ _` | '_| ' < | || .` | (_ |\n"
                + " | .__/\\__,_|_| |_|\\_\\___|_|\\_|\\___|\n"
                + " |_|                                ");
    }
    //@@author

    /**
     * Prompts user to enter name and prints welcome message.
     */
    private void askName() {
        print("What is your name?");
        String name = getLine();
        print(String.format("Welcome to parKING, %s!", name));
    }

    /**
     * Prints ASCII art and logo, and asks user for their name.
     */
    public void greetUser() {
        showLogo();
        askName();
    }

    /**
     * Show error message when fetching data is unsuccessful.
     */
    public void showFetchError() {
        print("Something went wrong when fetching data, trying again...");
    }

    /**
     * Show error message when fetching data took too long.
     */
    public void showFetchTimeout() {
        print("Fetch Timeout, trying again...");
    }

    /**
     * Show error message when saving data to file.
     */
    public void showSaveError() {
        print("Something went wrong when saving data.");
    }

    /**
     * Show error message when creating file is unsuccessful.
     */
    public void showCreateFileError() {
        print("Something wrong happened in file creation.");
    }

    /**
     * Show error message when invalid command is entered.
     */
    public void showInvalidCommandError() {
        print("Invalid command. Try again.");
    }

    /**
     * Show goodbye message before user quits program.
     */
    public void showByeMessage() {
        print("Goodbye.");
    }

    /**
     * Show message when loading data.
     */
    public void showLoadingDataMessage() {
        print("Trying to load data...");
    }

    /**
     * Show message when data is successfully loaded.
     */
    public void showLoadingDataSuccess() {
        print("Load data sequence successful!");
    }

    /**
     * Changes the scanner for the Ui object. To be used for JUnit testing.
     *
     * @param in New Scanner object to be used.
     */
    public void changeScanner(Scanner in) {
        this.in = in;
    }
}