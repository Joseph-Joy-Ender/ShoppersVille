package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.data.models.Customer;
import africa.semicolon.shoppersDelight.data.repositories.CustomerRepository;
import africa.semicolon.shoppersDelight.dtos.request.CustomerRegisterRequest;
import africa.semicolon.shoppersDelight.dtos.request.UpdateCustomerRequest;
import africa.semicolon.shoppersDelight.dtos.response.ApiResponse;
import africa.semicolon.shoppersDelight.dtos.response.CustomerRegisterResponse;
import africa.semicolon.shoppersDelight.dtos.response.UpdateCustomerResponse;
import africa.semicolon.shoppersDelight.exceptions.CustomerNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShoppersCustomerService implements CustomerService{

    private final CustomerRepository customerRepository;
    @Override
    public CustomerRegisterResponse register(CustomerRegisterRequest registerRequest) {
        Customer customer = new Customer();
        customer.setEmail(registerRequest.getEmail());
        customer.setPassword(registerRequest.getPassword());

        Customer savedCustomer = customerRepository.save(customer);

        CustomerRegisterResponse response = new CustomerRegisterResponse();
        response.setId(savedCustomer.getId());
        return response;
    }

    @Override
    public ApiResponse<UpdateCustomerResponse> updateCustomer(Long id, UpdateCustomerRequest customerRequest) throws CustomerNotFoundException {
        Customer customer = findCustomerById(id);


        customer.setEmail(customerRequest.getEmail());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        customer.setAddress(customerRequest.getAddress());
       customerRepository.save(customer);

       UpdateCustomerResponse customerResponse = new UpdateCustomerResponse();
       customerResponse.setMessage("Account updated successfully");
        return new ApiResponse<>(customerResponse);
    }

    private Customer findCustomerById(Long id) throws CustomerNotFoundException {
        return customerRepository.findById(id)
                                  .orElseThrow(() -> new CustomerNotFoundException(
                                   String.format("Customer with id %d not found", id)));
    }
}
