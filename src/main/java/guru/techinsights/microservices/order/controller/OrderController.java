package guru.techinsights.microservices.order.controller;

import guru.techinsights.microservices.order.dto.OrderRequest;
import guru.techinsights.microservices.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sattya
 * create at 9/18/2024 11:10 PM
 */
@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
    }
}
