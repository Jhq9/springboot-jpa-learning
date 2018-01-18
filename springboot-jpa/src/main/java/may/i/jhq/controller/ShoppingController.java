package may.i.jhq.controller;

import may.i.jhq.core.SpecificationFactory;
import may.i.jhq.dao.OrderRepository;
import may.i.jhq.domain.Order;
import may.i.jhq.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jinhuaquan
 * @create 2018-01-18 下午2:03
 * @desc The controller of shopping
 **/
@RestController
@RequestMapping(value = "/orders")
public class ShoppingController {

    @Autowired
    private OrderRepository myOrderRepository;

    /**
     * 内连接查询
     */
    @RequestMapping("/q1")
    public void specification1() {
        //根据查询结果，声明返回值对象，这里要查询用户的订单列表，所以声明返回对象为MyOrder
        Specification<Order> spec = new Specification<Order>() {
            //Root<X>  根查询，默认与声明相同
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //声明并创建MyOrder的CriteriaQuery对象
                CriteriaQuery<Order> q1 = cb.createQuery(Order.class);
                //连接的时候，要以声明的根查询对象（这里是root，也可以自己创建）进行join
                //Join<Z,X>是Join生成的对象，这里的Z是被连接的对象，X是目标对象，
                //  连接的属性字段是被连接的对象在目标对象的属性，这里是我们在MyOrder内声明的customer
                //join的第二个参数是可选的，默认是JoinType.INNER(内连接 inner join)，也可以是JoinType.LEFT（左外连接 left join）
                Join<User, Order> myOrderJoin = root.join("user", JoinType.INNER);
                //用CriteriaQuery对象拼接查询条件，这里只增加了一个查询条件，cId=1
                q1.select(myOrderJoin).where(cb.equal(root.get("uId"), 1L));
                //通过getRestriction获得Predicate对象
                Predicate p1 = q1.getRestriction();
                //返回对象
                return p1;
            }
        };
        resultPrint(spec);
    }


    /**
     * 增加查询条件，关联的对象Customer的对象值
     */
    @RequestMapping("/q2")
    public void specification2() {
        Specification<Order> spec = new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                CriteriaQuery<Order> q1 = cb.createQuery(Order.class);
                Join<User, Order> myOrderJoin = root.join("user");
                q1.select(myOrderJoin)
                    .where(
                        cb.equal(root.get("uId"), 1),//cId=1
                        cb.equal(root.get("user").get("name"), "jhq")//对象user的name=jhq
                    );
                Predicate p1 = q1.getRestriction();
                return p1;
            }
        };
        resultPrint(spec);
    }

    /**
     * in的条件查询
     * 需要将对应的结果集以root.get("attributeName").in(Object.. values)的方式传入
     * values支持多个参数，支持对象（Object），表达式Expression<?>，集合Collection以及Expression<Collection<?>>
     */
    @RequestMapping("/q3")
    public void specification3() {
        Specification<Order> spec = new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                CriteriaQuery<Order> q1 = cb.createQuery(Order.class);
                Join<User, Order> myOrderJoin = root.join("user");
                q1.select(myOrderJoin)
                    .where(
                        cb.equal(root.get("uId"), 1)
                        , root.get("id").in(1, 2, 4)
                    );

                Predicate p1 = q1.getRestriction();
                return p1;
            }
        };
        resultPrint(spec);
    }


    /**
     * 左外链接查询，对比inner join，
     * 这里只是改了一个参数，将JoinType.INNER改成JoinType.LEFT
     * <p>
     * 注意，当前示例不支持JoinType.RIGHT，用的比较少，没有探究
     */
    @RequestMapping("/q4")
    public void specification4() {
        Specification<Order> spec = new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                CriteriaQuery<Order> q1 = cb.createQuery(Order.class);
                Join<User, Order> myOrderJoin = root.join("user", JoinType.LEFT);
                q1.select(myOrderJoin).where(cb.equal(root.get("uId"), 1));
                Predicate p1 = q1.getRestriction();
                return p1;
            }
        };
        resultPrint(spec);
    }


    @RequestMapping("/q5")
    public void specification5() {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(4L);
        Specification<Order> spec = SpecificationFactory.in("id", list);

        resultPrint(spec);
    }


    private void resultPrint(Specification<Order> spec) {
        //分页查询
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
        //查询的分页结果
        Page<Order> page = myOrderRepository.findAll(spec, pageable);
        System.out.println(page);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        for (Order c : page.getContent()) {
            System.out.println(c.toString());
        }
    }


}
