package guru.techinsights.microservices.order.repository;

import guru.techinsights.microservices.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sattya
 * create at 9/18/2024 10:59 PM
 */
public interface OrderRepository extends JpaRepository<Order,Long> {
}
