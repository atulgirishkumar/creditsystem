package com.graviton.io.inputprocessors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author atul.girishkumar
 */
public abstract class AbstractInputProcessor implements InputProcessor {
    @Override
    public void processInput(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    processLine(line);
                } catch (Exception exception) {
                    System.err.println("Error occurred ignoring: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file=" + filePath + ", error=" + e.getMessage());
        }
    }

    protected abstract void processLine(String line);
}
