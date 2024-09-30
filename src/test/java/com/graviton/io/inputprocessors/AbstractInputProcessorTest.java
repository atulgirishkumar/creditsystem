package com.graviton.io.inputprocessors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;

import static org.mockito.Mockito.*;

/**
 * @author atul.girishkumar
 */
class AbstractInputProcessorTest {

    private AbstractInputProcessor inputProcessor;

    private class TestInputProcessor extends AbstractInputProcessor {
        @Override
        protected void processLine(String line) {
            // Mocked behavior in actual test cases
        }
    }

    @BeforeEach
    public void setUp() {
        inputProcessor = Mockito.spy(new TestInputProcessor());
    }

    @Test
    public void testInputProcessorReadsAllLines() throws Exception {
        File tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("line1\n");
            writer.write("line2\n");
        }
        inputProcessor.processInput(tempFile.getAbsolutePath());

        verify(inputProcessor, times(1)).processLine("line1");
        verify(inputProcessor, times(1)).processLine("line2");
    }


    @Test
    public void testProcessInputFileNotFoundIsGracefullyHandled() {
        inputProcessor.processInput("non-existent-file.txt");
    }
}
