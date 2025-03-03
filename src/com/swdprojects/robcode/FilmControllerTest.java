package com.swdprojects.robcode;

/*
    Roberto Ruiz,CEN3024C, 3/2/2025
    Software Development I
    FilmControllerTest: this class tests the main CRUD functionality and validation of the films handled the entire
    application with pass/fail tests
* */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest
{
    //add class var to use on all testing
    FilmController controller;

    //supply test data here
    @org.junit.jupiter.api.BeforeEach
    void setUp()
    {
        controller = new FilmController();
    }


    /*
     * menuOptionValidator_TestSuccess: pass test for menuOptionValidator() to display all
     * args: none
     * return: none
     * */
    @org.junit.jupiter.api.Test
    @DisplayName("menuOptionValidator_TestSuccess()")
    void menuOptionValidator_TestSuccess()
    {
        String menu = controller.GetMenuText();

        assertTrue(menu.contains("Film Management System:"), "Menu title is missing");
        assertTrue(menu.contains("1. Add Film Manually"), "Menu options are missing");
        assertTrue(menu.contains("7. Exit"), "exit option is missing");
    }

    /*
     * menuOptionValidator_TestSuccess: fail test for menuOptionValidator()
     * args: none
     * return: none
     * */
    @org.junit.jupiter.api.Test
    @DisplayName("menuOptionValidator_TestFail()")
    void menuOptionValidator_TestFail()
    {
        String menu = "";


        assertFalse(menu.contains("Film Management System:"), "Menu title is missing");
        assertFalse(menu.contains("1. Add Film Manually"), "Menu options are missing");
        assertFalse(menu.contains("7. Exit"), "exit option is missing");

    }


    /*
     * loadFilmsFromFile_TestSucceed: pass test for loadFilmsFromFile() to test functionality for adding films
     * from text file
     * args: none
     * return: none
     * */
    @org.junit.jupiter.api.Test
    @DisplayName("loadFilmsFromFile_TestSucceed()")
    void loadFilmsFromFile_TestSucceed() throws IOException
    {
        String filepath = "src/com/swdprojects/robcode/input.txt";

        File file = new File(filepath);
        if (!file.exists())
        {
            System.out.println("File does not exist: " + filepath);
            return;
        }

        boolean loadFilmsResult = controller.loadFilmsFromFile(filepath);

        assertTrue(loadFilmsResult, "Error: Films were not loaded from file");

    }

    /*
     * loadFilmsFromFile_TestSucceed: fail test for loadFilmsFromFile() to test functionality for errors in adding films
     * from text file
     * args: none
     * return: none
     * */
    @org.junit.jupiter.api.Test
    @DisplayName("loadFilmsFromFile_TestFail()")
    void loadFilmsFromFile_TestFail() throws IOException
    {
        String filepath = "src/com/swdprojects/robcode/doesntExist.txt";

        boolean loadFilmsResult = controller.loadFilmsFromFile(filepath);
        assertFalse(loadFilmsResult, "Error: Films were not loaded from file");

    }

    /*
     * displayAllFilms_TestSuccess: pass test for displayAllFilms() to test the funcitonality for the display of all films
     * in the application
     * args: none
     * return: none
     * */
    @org.junit.jupiter.api.Test
    @DisplayName("displayAllFilms_TestSuccess()")
    void displayAllFilms_TestSuccess()
    {
        int ID = 1234;
        String title = "Test Movie";
        String description = "Test description";
        float price = 20.00f;
        int releaseDate = 1999;
        int runtime = 200;

        boolean result =  controller.addFilmManually(ID, title, description, price, releaseDate, runtime);
        String filmsPrint = controller.displayAllFilms();

        assertTrue(result, "Error: Film was not added successfully");
        assertTrue(filmsPrint.contains("Film ID: 1234"), "Error: Film ID not found in output");
        assertTrue(filmsPrint.contains("Name: Test Movie"), "Error: Film title not found in output");
        assertTrue(filmsPrint.contains("Description: Test description"), "Error: Film description not found in output");
        assertTrue(filmsPrint.contains("Price: $20.0"), "Error: Film price not found in output");
        assertTrue(filmsPrint.contains("Release Date: 1999"), "Error: Film release date not found in output");
        assertTrue(filmsPrint.contains("Runtime: 200 minutes"), "Error: Film runtime not found in output");
    }

    /*
     * displayAllFilms_TestFail: fail test for displayAllFilms() to test the funcitonality for the errors of displaying of all films
     * in the application
     * args: none
     * return: none
     * */
    @org.junit.jupiter.api.Test
    @DisplayName("displayAllFilms_TestFail()")
    void displayAllFilms_TestFail()
    {
        String filmsPrint = controller.displayAllFilms();

        assertFalse(filmsPrint.isEmpty(), "Error: there are films present");
    }

    /*
     * addFilmManually_TestSuccess: pass test for addFilmManually() to test the funcitonality for adding a film
     * in the application
     * args: none
     * return: none
     * */
    @org.junit.jupiter.api.Test
    @DisplayName("addFilmManually_TestSuccess()")
    void addFilmManually_TestSuccess()
    {
        int ID = 1234;
        String title = "Test movie";
        String description = "test description";
        float price = 20.00f;
        int releaseDate = 1999;
        int runtime = 200;

        boolean result =  controller.addFilmManually(ID, title, description, price, releaseDate, runtime);
        assertTrue(result, "Error: adding film failed");
    }

    /*
     * addFilmManually_TestFail: fail test for addFilmManually() to test the funcitonality for errors in adding a film
     * in the application
     * args: none
     * return: none
     * */
    @org.junit.jupiter.api.Test
    @DisplayName("addFilmManually_TestFail()")
    void addFilmManually_TestFail()
    {
        int ID = 3333;
        String title = "Test movie";
        String description = "test description";
        float price = 20.00f;
        int releaseDate = 1999;
        int runtime = 200;
        controller.addFilmManually(ID, title, description, price, releaseDate, runtime);
        boolean addResult = controller.addFilmManually(ID, "Movie 2", "the second movie",price, releaseDate, runtime);

        assertFalse(addResult, "Error: addFilmManually should return false for duplicate IDs");
    }


    /*
     * updateFilm_TestSuccess: pass test for updateFilm() to test the funcitonality for updating a film
     * in the application
     * args: none
     * return: none
     * */
    @org.junit.jupiter.api.Test
    @DisplayName("updateFilm_TestSuccess()")
    void updateFilm_TestSuccess()
    {
        int ID = 1234;
        String title = "Test movie";
        String description = "test description";
        float price = 20.00f;
        int releaseDate = 1999;
        int runtime = 200;

        controller.addFilmManually(ID, title, description, price, releaseDate, runtime);


        boolean updateResult = controller.updateFilm(ID, "New Movie", description, price, releaseDate, runtime);

        assertTrue(updateResult, "Error in updateFilm_TestSuccess(): Film was not updated successfully");

    }

    /*
     * updateFilm_TestSuccess: fail test for updateFilm() to test the funcitonality for errors in updating a film
     * in the application
     * args: none
     * return: none
     * */
    @org.junit.jupiter.api.Test
    @DisplayName("updateFilm_TestFail")
    void updateFilm_TestFail()
    {
        int fakeID = 9999;
        String title = "Non-existent Movie";
        String description = "No description";
        float price = 15.99f;
        int releaseDate = 2000;
        int runtime = 120;

        boolean updateResult = controller.updateFilm(fakeID, title, description, price, releaseDate, runtime);


        assertFalse(updateResult, "Error: updateFilm() should return false if film does not exist");
    }

    /*
     * getAllFilmRuntime_TestSuccess: pass test for getAllFilmRuntime() to test the funcitonality for aggregation of all films
     * in the application
     * args: none
     * return: none
     * */
    @org.junit.jupiter.api.Test
    @DisplayName("getAllFilmRuntime_TestSuccess()")
    void getAllFilmRuntime_TestSuccess()
    {
        //dummy film added
        int ID = 1234;
        String title = "Test movie";
        String description = "test description";
        float price = 20.00f;
        int releaseDate = 1999;
        int runtime = 200;
        boolean addResult =  controller.addFilmManually(ID, title, description, price, releaseDate, runtime);
        int dummyRuntimeResult = controller.getAllFilmRuntime();

        assertTrue(addResult, "Error: film adding error");
        assertEquals(runtime, dummyRuntimeResult, "Error: unmatched total runtime");
    }

    /*
     * getAllFilmRuntime_TestSuccess: fail test for getAllFilmRuntime() to test the funcitonality for errors in the aggregation of all films
     * in the application
     * args: none
     * return: none
     * */
    @org.junit.jupiter.api.Test
    @DisplayName("getAllFilmRuntime_TestFail()")
    void getAllFilmRuntime_TestFail()
    {
        assertTrue(controller.getAllFilmRuntime() == 0, "Error: list is not empty");
    }



    /*
     * removeFilm_TestSucceed: pass test for removeFilm() to test functionality to remove films
     * from application
     * args: none
     * return: none
     * */
    @org.junit.jupiter.api.Test
    @DisplayName("removeFilm_TestSucceed()")
    void removeFilm_TestSucceed()
    {
        int ID = 1234;
        String title = "Test movie";
        String description = "test description";
        float price = 20.00f;
        int releaseDate = 1999;
        int runtime = 200;
        boolean addResult =  controller.addFilmManually(ID, title, description, price, releaseDate, runtime);

        boolean removeFilmResult = controller.removeFilm(ID);
        Film film = controller.getFilmByID(ID);
        assertTrue(removeFilmResult,"Error: remove film shold remove existing film");
        assertNull(film, "Error: film still present");
    }

    /*
     * removeFilm_TestFail: fail test for removeFilm() to test functionality to for errors in removing films
     * from application
     * args: none
     * return: none
     * */
    @Test
    @DisplayName("removeFilm_TestFail()")
    void removeFilm_TestFail()
    {
        int fakeID = 9999;
        boolean removeFilmResult = controller.removeFilm(fakeID);

        assertFalse(removeFilmResult, "Error: film found");
    }


}