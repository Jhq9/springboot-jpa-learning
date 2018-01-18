package may.i.jhq.dao;

import may.i.jhq.domain.User;
import may.i.jhq.projection.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;
import java.util.Date;
import java.util.List;

import static org.hibernate.jpa.QueryHints.HINT_COMMENT;

/**
 * @author jinhuaquan
 * @create 2018-01-18 上午9:22
 * @desc The repository of user
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {

    /**
     * ？加数字表示占位符，？1代表在方法参数里的第一个参数，区别于其他的index，这里从1开始
     *
     * =:加上变量名，这里是与方法参数中有@Param的值匹配的，而不是与实际参数匹配的
     *
     * JPQL的语法中，表名的位置对应Entity的名称，字段对应Entity的属性,详细语法见相关文档
     *
     * 要使用原生SQL需要在@Query注解中设置nativeQuery=true，然后value变更为原生SQL即可
     *
     * @param name
     * @param age
     * @return
     */

    @Query("select u from may.i.jhq.domain.User u where u.name=:name and u.age=:age")
    User findUserByNameAndAge2(@Param("name") String name, @Param("age") Integer age);

    User findByEmail(String email, Sort sort);

    @Query("select u from may.i.jhq.domain.User u where u.email=?1")
    User findByEmail2(String email);

    @Query(nativeQuery = true, value = "SELECT * FROM s_user WHERE age=?1")
    List<User> findUserByAge(@Param("age") Integer age);

    List<User> findUsersByAgeBetween(Integer start, Integer end);

    List<User> findUsersByAgeGreaterThanEqual(Integer age);

    List<User> findUsersByAgeLessThan(Integer age);

    List<User> findByNameContains(String name);

    List<User> findByNameStartingWith(String name);

    List<User> findUsersByNameIgnoreCase(String name);

    List<User> findByEmailEndingWith(String email);

    List<User> findUsersByAgeOrName(Integer age, String name);

    List<User> findDistinctByName(String name);

    List<User> findByNameNot(String name);

    List<User> findUsersByAgeIn(List<Integer> age);

    List<User> findUsersByAgeNotIn(List<Integer> age);

    List<User> findUsersByNameLike(String name);

    List<User> findUsersByNameNotLike(String name);

    List<User> findUsersByEmailIsNull();

    List<User> findUsersByCreateTimeBefore(Date createTime);

    @Modifying
    @Transactional(rollbackFor = TransactionException.class)
    @Query(value = "update may.i.jhq.domain.User u set u.email=:email where u.id=:id")
    Integer updateEmail(@Param("id") Long id, @Param("email") String email);

    @QueryHints(value = {
        @QueryHint(name = HINT_COMMENT, value = "age")
    })
    @Query("select u from may.i.jhq.domain.User u where u.age=:age")
    Page<User> findAllByAge(@Param("age") Integer age, Pageable pageable);

    @Query("select u.name as name, u.age as age, u.email as email from may.i.jhq.domain.User u where u.name=:name")
    List<UserProjection> findUsersByName(@Param("name") String name);
}
