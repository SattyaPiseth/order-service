package guru.techinsights.microservices.order.mapper;

import guru.techinsights.microservices.order.dto.OrderRequest;
import guru.techinsights.microservices.order.dto.OrderResponse;
import guru.techinsights.microservices.order.model.Order;
import org.mapstruct.Mapper;

/**
 * @author Sattya
 * create at 9/18/2024 11:04 PM
 */
@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order fromOrderRequest(OrderRequest orderRequest);
    OrderResponse toOrderResponse(Order order);
}
