package academy.digitallab.store.shopping.client;

import academy.digitallab.store.shopping.model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", path = "/customers")
public interface CustomerClient {

    @CircuitBreaker(name = "customerCB", fallbackMethod = "getCustomerFallbackMethod")
    @GetMapping(value = "/{id}")
    ResponseEntity<Customer> getCustomer(@PathVariable("id") long id);

    default ResponseEntity<Customer> getCustomerFallbackMethod(Exception exc) {
        Customer customer = Customer.builder()
                .firstName(exc.getMessage())
                .lastName("none")
                .email("none")
                .photoUrl("none").build();
        return ResponseEntity.ok(customer);
    }
}
