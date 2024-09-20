package guru.techinsights.microservices.order.service;

import guru.techinsights.microservices.order.dto.OrderRequest;

/**
 * @author Sattya
 * create at 9/18/2024 11:00 PM
 */
public interface OrderService {
    void placeOrder(OrderRequest orderRequest);
}
