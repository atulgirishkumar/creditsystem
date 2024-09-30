package com.graviton.io.outputprocessors;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author atul.girishkumar
 */
public abstract class AbstractOutputProcessor<T> implements OutputProcessor<T>{

    @Override
    public void processOutput(String filePath, T data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            String formattedData = formatData(data);
            bw.write(formattedData);
        } catch (IOException e) {
            System.err.println("Error occurred: "+e.getMessage());
        }
    }

    protected abstract String formatData(T data);
}
