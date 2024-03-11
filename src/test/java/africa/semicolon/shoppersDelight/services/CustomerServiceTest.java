package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.CustomerRegisterRequest;
import africa.semicolon.shoppersDelight.dtos.request.UpdateCustomerRequest;
import africa.semicolon.shoppersDelight.dtos.response.ApiResponse;
import africa.semicolon.shoppersDelight.dtos.response.CustomerRegisterResponse;
import africa.semicolon.shoppersDelight.dtos.response.UpdateCustomerResponse;
import africa.semicolon.shoppersDelight.exceptions.CustomerNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;
    @Test
    public void registerTest(){
        CustomerRegisterRequest registerRequest = new CustomerRegisterRequest();

        registerRequest.setEmail("test@gmail.com");
        registerRequest.setPassword("password");

        CustomerRegisterResponse response =
        customerService.register(registerRequest);

        assertNotNull(response);
        assertNotNull(response.getId());
    }

    @Test
    public void updateCustomerTest() throws CustomerNotFoundException {
        UpdateCustomerRequest customerRequest = new UpdateCustomerRequest();
//        customerRequest.setEmail("test@gmail.com");
        customerRequest.setPhoneNumber("09083456");
        customerRequest.setAddress("312, Herbert Macaulay way, Sabo");

        ApiResponse<UpdateCustomerResponse> response = customerService.updateCustomer(1L, customerRequest);

        assertThat(response).isNotNull();
        assertThat(response.getData().getMessage()).isNotNull();
    }
}
