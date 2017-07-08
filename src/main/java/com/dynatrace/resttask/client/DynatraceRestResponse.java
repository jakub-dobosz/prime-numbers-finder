package com.dynatrace.resttask.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DynatraceRestResponse {
    private String id;
    private int size;
    private List<Integer> data;
}
