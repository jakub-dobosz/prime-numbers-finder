package com.dynatrace.resttask.server.service;

import java.util.List;
import java.util.Set;

public interface PrimeNumbersService {
    Set<Integer> findAndSortFrom(List<Integer> numbers);
}
