package guru.techinsights.microservices.order.service.impl;

import guru.techinsights.microservices.order.client.InventoryClient;
import guru.techinsights.microservices.order.dto.OrderRequest;
import guru.techinsights.microservices.order.model.Order;
import guru.techinsights.microservices.order.repository.OrderRepository;
import guru.techinsights.microservices.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

/**
 * @author Sattya
 * create at 9/18/2024 11:03 PM
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    @Override
    public void placeOrder(OrderRequest orderRequest) {
        var isPresentInStock = inventoryClient.isInStock(orderRequest.skuCode(),orderRequest.quantity());
        log.info("isPresentInStock : {}",isPresentInStock);
        if (isPresentInStock){
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setSkuCode(orderRequest.skuCode());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            orderRepository.save(order);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Product with SkuCode %s is not available in stock",orderRequest.skuCode()));
        }

    }
}
