package guru.techinsights.microservices.order.dto;

import java.math.BigDecimal;

/**
 * @author Sattya
 * create at 9/18/2024 11:01 PM
 */
public record OrderRequest(
//        String orderNumber,
        String skuCode,
        BigDecimal price,
        Integer quantity) {
}
