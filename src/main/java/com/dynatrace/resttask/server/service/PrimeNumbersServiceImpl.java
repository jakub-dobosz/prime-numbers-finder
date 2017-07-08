package com.dynatrace.resttask.server.service;

import org.apache.commons.math3.primes.Primes;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class PrimeNumbersServiceImpl implements PrimeNumbersService {
    @Override
    public Set<Integer> findAndSortFrom(List<Integer> numbers) {
        return numbers.stream()
                .filter(Primes::isPrime)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
