package com.graviton.io.outputprocessors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author atul.girishkumar
 */
class AbstractOutputProcessorTest {

    private TestOutputProcessor outputProcessor;

    private class TestOutputProcessor extends AbstractOutputProcessor<String> {
        @Override
        protected String formatData(String data) {
            return data;
        }
    }

    @BeforeEach
    void setUp() {
        outputProcessor =  Mockito.spy(new TestOutputProcessor());
    }

    @Test
    void testOutputFileIsCreated() throws IOException {
        String filePath = "output.txt";
        String data = "Test data";

        outputProcessor.processOutput(filePath, data);

        File file = new File(filePath);
        assertTrue(file.exists(), "File should exist after processing output.");
        file.delete();
    }


}