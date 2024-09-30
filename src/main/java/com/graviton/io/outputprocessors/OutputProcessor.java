package com.graviton.io.outputprocessors;

import java.util.List;

/**
 * @author atul.girishkumar
 */
public interface OutputProcessor<T> {
    void processOutput(String filePath, T data);
}
