package may.i.jhq.repository;

import may.i.jhq.Tester;
import may.i.jhq.core.SpecificationFactory;
import may.i.jhq.dao.UserRepository;
import may.i.jhq.domain.User;
import may.i.jhq.projection.UserProjection;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

/**
 * @author jinhuaquan
 * @create 2018-01-18 上午10:57
 * @desc The repository tester of user
 **/
public class UserRepositoryTester extends Tester {

    @Autowired
    private UserRepository userRepository;

    private Long id;

    @Before
    public void testSave() {
        User user = new User();

        user.setName("jhq");
        user.setAge(23);
        user.setEmail("111@qq.com");

        userRepository.save(user);

        Assert.isTrue((id = user.getId()) > 0, "Save failed");
    }


    @Test
    public void testDelete() {

    }

    @Test
    public void testFindByEmailAndSort() {

        Sort sort = Sort.by(Sort.Order.asc("id"));
        Sort.by(Arrays.asList(Sort.Order.asc("id"), Sort.Order.desc("email")));
        User user = userRepository.findByEmail("111@qq.com", sort);

        Assert.notNull(user, "Find failed");
    }

    @Test
    public void testUpdateEmail() {
        Integer result = userRepository.updateEmail(id, "123");

        Assert.isTrue(result == 1, "Update failed");
    }

    @Test
    public void testFindByAgePageable() {
        Pageable pageable = PageRequest.of(0, 3, Sort.Direction.ASC, "id" );
        Page<User> result = userRepository.findAllByAge(23, pageable);

        Assert.isTrue(result.getContent().size() > 0, "Find failed");
    }

    @Test
    public void testFindByUserName() {
        List<UserProjection> userProjections = userRepository.findUsersByName("jhq");

        System.out.println(userProjections.get(0).getAge());
        System.out.println(userProjections.get(0).getEmail());
        System.out.println(userProjections.get(0).getName());
    }

    @Test
    public void testSpecificationQuery() {
        Specification<User> specification = Specification.where(SpecificationFactory.containsLike("name", "j")).or(SpecificationFactory.isBetween("age", 1, 100));

        List<User> users = userRepository.findAll(specification);

        Assert.isTrue(users.size() > 0, "Find failed");
    }

    @Test
    public void testSpecificationQueryAndPage() {

        Specification<User> specification = Specification.where(SpecificationFactory.equal("name", "jhq")).and(SpecificationFactory.in("age", Arrays.asList(23, 22)));

        Pageable pageable = PageRequest.of(0, 3, Sort.by("age"));

        Page<User> users = userRepository.findAll(specification, pageable);

        Assert.isTrue(users.getContent().size() > 0, "Find failed");
    }
}
