package com.company.controller;

import com.company.model.Pokemon;
import com.company.view.PokedexUI;


import java.io.*;
import java.util.ArrayList;

public class PokedexManager {

    private PokedexUI ui = new PokedexUI();

    private ArrayList<Pokemon> pokedexList = new ArrayList<>();

    private static final String POKEDEX_FILE_NAME = "pokedex.txt";

    public PokedexManager() throws IOException {
        try {
            loadPokemon();
        } catch(FileNotFoundException fnfe) {
            ui.displayError("No pokedex file found; starting a new one");
        }
    }

    public void run() throws IOException {
        boolean exitRequested = false;
        while(!exitRequested) {
            PokedexUI.MenuOption selection = ui.promptForMenuSelection();
            switch(selection) {
                case ADD_POKEMON:
                    addPokemon();
                    break;
                case VIEW_POKEDEX:
                    viewPokemon();
                    break;
                case REMOVE_POKEMON:
                    break;
                case EDIT_POKEMON:
                    break;
                case SORT_POKEDEX:
                    break;
                case EXIT:
                    exitRequested = !exitRequested;
                    break;
            }
        }
    }

    private void viewPokemon() {
        ui.displayMessage(pokedexList.toString());
    }

    private void addPokemon() throws IOException {
        Pokemon p = new Pokemon();
        p.setName(ui.promptForPokemonName());
        p.setLevel(ui.promptForPokemonLevel());
        p.setCaptureDate(ui.promptForPokemonCaptureDate());
        pokedexList.add(p);
        savePokemon();
    }

    private void savePokemon() throws IOException {
        PrintStream outFile = new PrintStream(POKEDEX_FILE_NAME);
        try {
            for(Pokemon p : pokedexList) {
                outFile.println(p.serialize());
            }
        } finally {
            outFile.close();
        }
    }

    private void loadPokemon() throws IOException {

        BufferedReader inFile = new BufferedReader(new InputStreamReader(new FileInputStream(POKEDEX_FILE_NAME)));

        this.pokedexList.clear();

        try {
            while(inFile.ready()) {
                String line = inFile.readLine();
                if(!line.trim().isEmpty()) {
                    // Turn that line into a pokemon
                    // Add it to the list
                    Pokemon p = new Pokemon();
                    p.deserialize(line);
                    pokedexList.add(p);
                }
            }
        } finally {
            inFile.close();
        }
    }
}
