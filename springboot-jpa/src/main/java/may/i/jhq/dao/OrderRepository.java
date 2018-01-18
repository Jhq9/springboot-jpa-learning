package may.i.jhq.dao;

import may.i.jhq.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author jinhuaquan
 * @create 2018-01-18 下午1:59
 * @desc The repository of user
 **/
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

}
