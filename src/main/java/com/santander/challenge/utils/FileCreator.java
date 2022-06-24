package com.santander.challenge.utils;

import com.santander.challenge.exceptions.FileAlreadyOpenException;
import com.santander.challenge.models.User;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class FileCreator {
    public static void createOperationDataFile(String message, User user) {
        String path = null;
        String uri = "Some URI";
        try {
            convertUriToPath(path, uri);
        } catch (FileNotFoundException e) {
            //Treat exception
        }
        catch (SecurityException e) {
            //Treat exception
        }
        catch (FileAlreadyOpenException e) {
            //Treat exception
        }
        catch (IllegalArgumentException e) {
            //Treat exception
        }
        try {
            writeFile(user);
        } catch (IOException e) {
            //Treat exception
        }
        catch (SecurityException e) {
            //Treat exception
        }
        catch (UnsupportedOperationException e) {
            //Treat exception
        }
        catch (IllegalArgumentException e) {
            //Treat exception
        }
    }

    public static void convertUriToPath(String path, String uri) throws FileNotFoundException {
        //This method should convert the uri to a path, and throws an exception if it can't
    }

    public static void writeFile(User user) throws IOException {
        //This method should write a file with the provided user data
    }
}
