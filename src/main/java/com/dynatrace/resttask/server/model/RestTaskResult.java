package com.dynatrace.resttask.server.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Getter
public class RestTaskResult {
    private List<Integer> inputData;
    private Set<Integer> outputData;

    public RestTaskResult() {
        this.inputData = new ArrayList<>();
        this.outputData = new TreeSet<>();
    }

    public RestTaskResult(List<Integer> inputData, Set<Integer> outputData) {
        this.inputData = inputData;
        this.outputData = outputData;
    }
}
