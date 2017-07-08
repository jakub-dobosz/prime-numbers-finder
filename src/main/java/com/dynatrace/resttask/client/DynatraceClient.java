package com.dynatrace.resttask.client;

public interface DynatraceClient {
    boolean isAvailable();

    DynatraceRestResponse getResponse();
}
