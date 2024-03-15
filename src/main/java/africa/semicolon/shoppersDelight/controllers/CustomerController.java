package africa.semicolon.shoppersDelight.controllers;

import africa.semicolon.shoppersDelight.dtos.request.CustomerRegisterRequest;
import africa.semicolon.shoppersDelight.dtos.request.UpdateCustomerRequest;
import africa.semicolon.shoppersDelight.dtos.response.ApiResponse;
import africa.semicolon.shoppersDelight.dtos.response.CustomerRegisterResponse;
import africa.semicolon.shoppersDelight.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customer")
@CrossOrigin("*")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerRegisterResponse> registerCustomer(@RequestBody CustomerRegisterRequest registerRequest){
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(customerService.register(registerRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody UpdateCustomerRequest request, @PathVariable Long id){
        try {
            return ResponseEntity.ok(customerService.updateCustomer(id, request));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>("Failed"));
        }

    }
}
