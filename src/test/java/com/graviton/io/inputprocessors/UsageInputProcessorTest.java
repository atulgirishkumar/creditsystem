package com.graviton.io.inputprocessors;

import com.graviton.controller.cmd.CreditController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

/**
 * @author atul.girishkumar
 */
class UsageInputProcessorTest {

    private UsageInputProcessor usageInputProcessor;
    private CreditController mockCreditController;

    @BeforeEach
    void setUp() {
        mockCreditController = mock(CreditController.class);
        usageInputProcessor = new UsageInputProcessor() {
            @Override
            protected CreditController getCreditController() {
                return mockCreditController;
            }
        };
    }

    @Test
    void testProcessLineValidInput() {
        String line = "C2,S1,10";
        usageInputProcessor.processLine(line);

        verify(mockCreditController).useCredits("C2", "S1", 10);
    }

    @Test
    void testProcessLineEmptyInput() {
        usageInputProcessor.processLine("");

        verify(mockCreditController, never()).useCredits(anyString(), anyString(), anyInt());
    }

}