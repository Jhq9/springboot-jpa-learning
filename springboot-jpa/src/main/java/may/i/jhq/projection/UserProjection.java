package may.i.jhq.projection;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author jinhuaquan
 * @create 2018-01-18 下午1:02
 * @desc The User Projection
 **/
public interface UserProjection {

    String getName();

    Integer getAge();

    @Value("#{target.email}")
    String getEmail();
}
