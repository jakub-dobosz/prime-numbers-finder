package com.dynatrace.resttask.server.service;

import com.google.common.collect.Comparators;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrimeNumbersServiceTest {
    private List<Integer> testValues = Arrays.asList(5, 2, 65, 1, 5, 43, 123, -10);

    private List<Integer> output;

    @Autowired
    private PrimeNumbersService primeNumbersService;

    @Before
    public void setup() {
        this.output = new ArrayList<>(primeNumbersService.findAndSortFrom(testValues));
    }

    @Test
    public void one_should_not_be_a_prime_number() {
        assertThat(output).doesNotContain(1);
    }

    @Test
    public void output_should_be_sorted() {
        assertThat(Comparators.isInOrder(output, Comparator.naturalOrder())).isTrue();
    }

    @Test
    public void should_not_find_negative_values() {
        assertThat(output).doesNotContain(-10);
    }

    @Test
    public void should_find_prime_numbers() {
        List<Integer> validOutput = Arrays.asList(2, 5, 43);
        assertThat(output).isEqualTo(validOutput);
    }

    @Test
    public void should_return_empty_set_when_empty_input_given() {
        List<Integer> emptyInput = Collections.emptyList();
        assertThat(primeNumbersService.findAndSortFrom(emptyInput)).isEmpty();
    }
}
