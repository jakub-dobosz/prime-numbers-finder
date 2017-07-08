package com.dynatrace.resttask.server.controller;

import com.dynatrace.resttask.client.DynatraceClient;
import com.dynatrace.resttask.server.model.RestTaskResult;
import com.dynatrace.resttask.server.service.PrimeNumbersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class RestTaskController {

    private DynatraceClient dynatraceClient;
    private PrimeNumbersService primeNumbersService;

    @Autowired
    public RestTaskController(DynatraceClient dynatraceClient, PrimeNumbersService primeNumbersService) {
        this.dynatraceClient = dynatraceClient;
        this.primeNumbersService = primeNumbersService;
    }

    @GetMapping("/data")
    public ResponseEntity getResponse() {
        try {
            if (dynatraceClient.isAvailable()) {
                return ResponseEntity.ok(getCalculationResult());
            } else {
                return ResponseEntity.ok().body("Remote resource unavailable");
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return ResponseEntity.status(503).body("Service unavailable");
        }
    }

    private RestTaskResult getCalculationResult() {
        List<Integer> inputNumbers = dynatraceClient.getResponse().getData();
        Set<Integer> primeNumbers = primeNumbersService.findAndSortFrom(inputNumbers);
        return new RestTaskResult(inputNumbers, primeNumbers);
    }
}
