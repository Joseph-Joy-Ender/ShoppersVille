package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.CustomerRegisterRequest;
import africa.semicolon.shoppersDelight.dtos.request.UpdateCustomerRequest;
import africa.semicolon.shoppersDelight.dtos.response.ApiResponse;
import africa.semicolon.shoppersDelight.dtos.response.CustomerRegisterResponse;
import africa.semicolon.shoppersDelight.dtos.response.UpdateCustomerResponse;
import africa.semicolon.shoppersDelight.exceptions.CustomerNotFoundException;

public interface CustomerService {

     CustomerRegisterResponse register(CustomerRegisterRequest registerRequest);

    ApiResponse<UpdateCustomerResponse> updateCustomer(Long id, UpdateCustomerRequest customerRequest) throws CustomerNotFoundException;
}
