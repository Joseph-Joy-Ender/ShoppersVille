package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.data.models.Customer;
import africa.semicolon.shoppersDelight.data.repositories.CustomerRepository;
import africa.semicolon.shoppersDelight.dtos.request.CustomerRegisterRequest;
import africa.semicolon.shoppersDelight.dtos.request.UpdateCustomerRequest;
import africa.semicolon.shoppersDelight.dtos.response.ApiResponse;
import africa.semicolon.shoppersDelight.dtos.response.CustomerRegisterResponse;
import africa.semicolon.shoppersDelight.dtos.response.UpdateCustomerResponse;
import africa.semicolon.shoppersDelight.exceptions.CustomerNotFoundException;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;

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
        Customer customer = findCustomerBy(id);

        List<JsonPatchOperation> jsonPatchOperations = new ArrayList<>();
        buildPatchOperations(customerRequest, jsonPatchOperations);
        customer = applyPatch(jsonPatchOperations, customer);
        customerRepository.save(customer);
        return new ApiResponse<>(buildUpdateCustomerResponse());


    }

    private static UpdateCustomerResponse buildUpdateCustomerResponse(){
        UpdateCustomerResponse customerResponse = new UpdateCustomerResponse();
        customerResponse.setMessage("Account updated successfully");
        return customerResponse;
    }

    private static void buildPatchOperations(UpdateCustomerRequest request, List<JsonPatchOperation> jsonPatchOperations){
       stream(request.getClass().getDeclaredFields())
               .filter(field -> isValidUpdate(field, request))
               .forEach(field -> addOperation(request, field, jsonPatchOperations));
    }

    private static Customer applyPatch(List<JsonPatchOperation> jsonPatchOperations, Customer customer){
       try {
           ObjectMapper mapper = new ObjectMapper();
           JsonPatch jsonPatch = new JsonPatch(jsonPatchOperations);
           JsonNode customerNode = mapper.convertValue(customer, JsonNode.class);
           JsonNode updatedNode = jsonPatch.apply(customerNode);
           customer = mapper.convertValue(updatedNode, Customer.class);
       }catch (Exception exception){
           throw new RuntimeException(exception);
       }
       return customer;
    }

    private static boolean isValidUpdate(Field field, UpdateCustomerRequest request){
        field.setAccessible(true);
        try {
            return field.get(request) != null;
        }catch (IllegalAccessException e){
            throw new RuntimeException(e);
        }
    }

    private static void addOperation(UpdateCustomerRequest request, Field field, List<JsonPatchOperation> jsonPatchOperations){
      try {
          JsonPointer path = new JsonPointer("/"+ field.getName());
          JsonNode value = new TextNode(field.get(request).toString());
          ReplaceOperation replaceOperation = new ReplaceOperation(path, value);
          jsonPatchOperations.add(replaceOperation);
      }catch (Exception e){
          throw new RuntimeException(e);
      }
    }

    private Customer findCustomerBy(Long id) throws CustomerNotFoundException {
        return customerRepository.findById(id)
                                  .orElseThrow(() -> new CustomerNotFoundException(
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            String.format("Customer with id %d not found", id)));
    }
}
