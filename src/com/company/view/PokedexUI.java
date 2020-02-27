package com.company.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

// Has modular methods for prompting the user for input
// This class receives all user input and returns that input from its methods
// There will be some validation methods, e.g. min and max, or minimum length
public class PokedexUI {

    public enum MenuOption {
        ADD_POKEMON,
        VIEW_POKEDEX,
        REMOVE_POKEMON,
        EDIT_POKEMON,
        SORT_POKEDEX,
        EXIT
    }

    private BufferedReader in;

    public PokedexUI() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    public MenuOption promptForMenuSelection() throws IOException {
        String menuText = buildMainMenuDisplay();
        displayMessage(menuText);
        int selection = readInt(1, MenuOption.values().length);
        return MenuOption.values()[selection - 1];
    }

    private String buildMainMenuDisplay() {
        return String.format(
                "Main Menu\n" +
                "1 - %s\n" +
                "2 - %s\n" +
                "3 - %s\n" +
                "4 - %s\n" +
                "5 - %s\n" +
                "6 - %s\n",
                MenuOption.ADD_POKEMON,
                MenuOption.VIEW_POKEDEX,
                MenuOption.REMOVE_POKEMON,
                MenuOption.EDIT_POKEMON,
                MenuOption.SORT_POKEDEX,
                MenuOption.EXIT
        );
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String message) {
        System.err.println(message);
    }

    private int readInt(int min, int max) throws IOException {
        while(true) {
            String input = in.readLine();
            try {
                int parsedInput = Integer.parseInt(input);
                if (parsedInput < min || parsedInput > max) {
                    throw new NumberFormatException();
                }
                return parsedInput;
            } catch (NumberFormatException nfe) {
                displayError(String.format("You must enter an integer between %d and %d", min, max));
            }
        }
    }

    private LocalDate readDate(int minYear, int maxYear) throws IOException {
        // Enter the year
        displayMessage(String.format("Enter a year between %d and %d:", minYear, maxYear));
        int year = readInt(minYear, maxYear);

        // Enter the month
        displayMessage("Enter a month (1-12):");
        int month = readInt(1, 12);

        // Enter the day
        int maxDayInMonth = determineMaxDayInMonth(year, month);

        displayMessage("Enter a day (1-" + maxDayInMonth + "):");
        int day = readInt(1, maxDayInMonth);

        return LocalDate.of(year, month, day);
    }

    private int determineMaxDayInMonth(int year, int month) {
        //return LocalDate.of(year, month, 1).plusMonths(1).minusDays(1).getDayOfMonth();
        return LocalDate.of(year, month, 1).lengthOfMonth();
    }


    private String readString(int minLength) throws IOException {
        while(true) {
            String input = in.readLine();
            if(input.length() < minLength) {
                displayError("You must enter a string that is at least "
                                + minLength + " characters long");
            } else {
                return input;
            }
        }
    }

    public String promptForPokemonName() throws IOException {
        displayMessage("Enter the pokemon's name:");
        return readString(1);
    }

    public int promptForPokemonLevel() throws IOException {
        displayMessage("Enter the pokemon's level:");
        return readInt(1, 100);
    }

    public LocalDate promptForPokemonCaptureDate() throws IOException{
        displayMessage("Enter the capture date of the pokemon:");
        return readDate(1996, LocalDate.now().getYear());
    }
}



