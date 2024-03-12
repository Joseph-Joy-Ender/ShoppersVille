package africa.semicolon.shoppersDelight.controllers;

import africa.semicolon.shoppersDelight.dtos.request.CustomerRegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {


    MockMvc mockMvc = standaloneSetup(new CustomerController())
            .defaultRequest(post("api/v1/customer").accept(MediaType.APPLICATION_JSON))
            .alwaysExpect(status().isOk())
            .alwaysExpect(content().contentType("application/json;charset=UTF-8"))
            .build();

//    @Autowired
//    private MockMvc mockMvc;
//
//    private static final ObjectMapper mapper = new ObjectMapper();
//
//    @Test
//    public void testRegisterCustomer(){
//        CustomerRegisterRequest request = new CustomerRegisterRequest();
//        request.setEmail("benson12@email.com");
//        request.setPassword("benson5510");
//
//        mockMvc.perform()
//
//    }
}
