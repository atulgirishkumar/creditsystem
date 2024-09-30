package com.graviton.io.inputprocessors;

import com.graviton.controller.cmd.PricingController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

/**
 * @author atul.girishkumar
 */
class PricingInputProcessorTest {

    private PricingInputProcessor pricingInputProcessor;
    private PricingController mockPricingController;

    @BeforeEach
    void setUp() {
        mockPricingController = mock(PricingController.class);
        pricingInputProcessor = new PricingInputProcessor() {
            @Override
            public PricingController getPricingController() {
                return mockPricingController;
            }
        };
    }

    @Test
    void testProcessLineValidService() {
        String line = "Service,S2,2";
        pricingInputProcessor.processLine(line);

        verify(mockPricingController).addServices("S2", 2);
    }

    @Test
    void testProcessLineValidPackage() {
        String line = "Package,Standard,250,225.20";
        pricingInputProcessor.processLine(line);

        verify(mockPricingController).addCreditPackages("Standard", 250, 225.20);
    }

    @Test
    void testProcessLineInvalidFormat() {
        String line = "service,service123";
        pricingInputProcessor.processLine(line);

        verify(mockPricingController, never()).addServices(anyString(), anyInt());
    }

    @Test
    void testProcessLineUnknownKeyword() {
        String line = "unknown,abc,10";
        pricingInputProcessor.processLine(line);

        verify(mockPricingController, never()).addServices(anyString(), anyInt());
        verify(mockPricingController, never()).addCreditPackages(anyString(), anyInt(), anyDouble());
    }

}