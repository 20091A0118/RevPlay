package com.revplay.controller;

import com.revplay.model.Genre;
import com.revplay.service.GenreServiceImpl;
import com.revplay.service.IGenreService;

import java.util.Scanner;

public class GenreController {

    private IGenreService genreService = new GenreServiceImpl();

    public void addGenre() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter genre name: ");
        String name = sc.nextLine();

        genreService.addGenre(new Genre(0, name));
    }
}

