package com.graviton.io.inputprocessors;

import com.graviton.controller.cmd.CreditController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

/**
 * @author atul.girishkumar
 */
class PurchaseInputProcessorTest {

    private PurchaseInputProcessor purchaseInputProcessor;
    private CreditController mockCreditController;

    @BeforeEach
    void setUp() {
        mockCreditController = mock(CreditController.class);
        purchaseInputProcessor = new PurchaseInputProcessor() {
            @Override
            public CreditController getCreditController() {
                return mockCreditController;
            }
        };
    }

    @Test
    void testProcessLineValidInput() {
        String line = "C1,Basic";
        purchaseInputProcessor.processLine(line);

        verify(mockCreditController).buyCredits("C1", "Basic");
    }

    @Test
    void testProcessLineEmptyInput() {
        purchaseInputProcessor.processLine("");

        verify(mockCreditController, never()).buyCredits(anyString(), anyString());
    }
}