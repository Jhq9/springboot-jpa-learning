package may.i.jhq.service;

import may.i.jhq.domain.User;
import may.i.jhq.vo.UserRequestVO;

import java.util.List;

/**
 * @author jinhuaquan
 * @create 2018-01-18 上午9:30
 * @desc The service implement of user
 **/
public interface UserService {

    /**
     * Save a new user
     * @param userRequestVO
     * @return
     */
    Long saveUser(UserRequestVO userRequestVO);

    /**
     * Remove the user by his id
     * @param id
     * @return
     */
    Boolean removeUser(Long id);

    /**
     * Update the user's info
     * @param userRequestVO
     * @return
     */
    Boolean updateUser(Long id, UserRequestVO userRequestVO);

    /**
     * Get the user info by his id
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * List all users
     * @return
     */
    List<User> listUser();
}
