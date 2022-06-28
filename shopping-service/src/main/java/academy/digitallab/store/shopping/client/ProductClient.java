package academy.digitallab.store.shopping.client;

import academy.digitallab.store.shopping.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service", path = "/products")
public interface ProductClient {

    @GetMapping(value = "/{id}")
    ResponseEntity<Product> getProduct(@PathVariable(value = "id") Long id);

    @PatchMapping(value = "/{id}/stock")
    ResponseEntity<Product> updateStockProduct(@PathVariable(value = "id") Long id,
                                               @RequestParam(value = "quantity") Double quantity);


}
