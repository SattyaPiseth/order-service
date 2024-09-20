package guru.techinsights.microservices.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Sattya
 * create at 9/19/2024 3:48 AM
 */
@FeignClient(value = "inventory",url = "${inventory.url}")
public interface InventoryClient {
    @GetMapping("api/inventory")
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
}
