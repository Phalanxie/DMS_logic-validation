package com.swdprojects.robcode;

/*
*
    Roberto Ruiz,CEN3024C, 3/2/2025
    Software Development I
    Main: this class launches the application. The application creates film entries (manually and from file), deletes entries,
     updates entries, adds the total runtime of all films, and displays all entered films.
* */

public class Main
{
    public static void main(String[] args)
    {
        //application launcher, menu inside FilmController
        FilmController controller = new FilmController();
        controller.MenuOptionValidator();

    }

}