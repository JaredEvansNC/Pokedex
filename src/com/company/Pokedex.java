package com.company;

import com.company.controller.PokedexManager;
import com.company.view.PokedexUI;

import java.io.IOException;
import java.time.LocalDate;

public class Pokedex {

    public static void main(String[] args) throws IOException {
        new PokedexManager().run();
    }
}
