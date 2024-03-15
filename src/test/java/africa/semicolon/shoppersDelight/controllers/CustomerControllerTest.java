package africa.semicolon.shoppersDelight.controllers;

import africa.semicolon.shoppersDelight.dtos.request.CustomerRegisterRequest;
import africa.semicolon.shoppersDelight.dtos.request.UpdateCustomerRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {


//    MockMvc = standaloneSetup(new CustomerController())
//            .defaultRequest(post("api/v1/customer").accept(MediaType.APPLICATION_JSON))
//            .alwaysExpect(status().isOk())
//            .alwaysExpect(content().contentType("application/json;charset=UTF-8"))
//            .build();

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testRegisterCustomer() throws Exception{
        CustomerRegisterRequest request = new CustomerRegisterRequest();
        request.setEmail("benson12@email.com");
        request.setPassword("benson5510");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(request)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

    }

    @Test
    public void testUpdateCustomer() throws Exception{
        UpdateCustomerRequest request = new UpdateCustomerRequest();
        request.setAddress("12, Oregun Ikeja");
        request.setPhoneNumber("+2349087321");

        mockMvc.perform(patch("/api/v1/customer/5")
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(request)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
}
