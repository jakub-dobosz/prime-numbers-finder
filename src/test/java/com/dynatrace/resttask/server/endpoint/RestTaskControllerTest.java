package com.dynatrace.resttask.server.endpoint;

import com.dynatrace.resttask.client.DynatraceClient;
import com.dynatrace.resttask.client.DynatraceRestResponse;
import com.dynatrace.resttask.server.controller.RestTaskController;
import com.dynatrace.resttask.server.service.PrimeNumbersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestTaskController.class)
public class RestTaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DynatraceClient dynatraceClient;

    @MockBean
    private PrimeNumbersService primeNumbersService;

    @Autowired
    private ObjectMapper mapper;

    private DynatraceRestResponse response;

    @Before
    public void setup() throws Exception {
        File sampleResource = new File("src/main/resources/sampleResponse.json");
        response = mapper.readValue(sampleResource, DynatraceRestResponse.class);
        when(dynatraceClient.getResponse()).thenReturn(response);

    }

    @Test
    public void when_remote_resource_is_available_then_input_data_should_be_equal_to_data_from_resource() throws Exception {
        setDynatraceClientAvailable();
        mvc.perform(get("/data")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("inputData", is(response.getData())));
    }

    @Test
    public void when_remote_resource_is_available_then_should_find_correct_output_data() throws Exception {
        setDynatraceClientAvailable();
        Set<Integer> correctValues = new TreeSet<>(Arrays.asList(13, 109, 677));
        when(primeNumbersService.findAndSortFrom(response.getData())).thenReturn(correctValues);

        mvc.perform(get("/data")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("outputData", is(correctValues)));
    }

    @Test
    public void when_remote_resource_is_unavailable_then_return_status_ok() throws Exception {
        setDynatraceClientUnavailable();
        mvc.perform(get("/data")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private void setDynatraceClientAvailable() {
        when(dynatraceClient.isAvailable()).thenReturn(true);
    }

    private void setDynatraceClientUnavailable() {
        when(dynatraceClient.isAvailable()).thenReturn(false);
    }
}

