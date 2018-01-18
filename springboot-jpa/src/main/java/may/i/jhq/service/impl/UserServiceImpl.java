package may.i.jhq.service.impl;

import may.i.jhq.dao.UserRepository;
import may.i.jhq.domain.User;
import may.i.jhq.service.UserService;
import may.i.jhq.vo.UserRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author jinhuaquan
 * @create 2018-01-18 上午9:43
 * @desc The service implement of user
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(rollbackFor = TransactionException.class)
    public Long saveUser(UserRequestVO userRequestVO) {
        Date now = Date.from(Instant.now());

        User user = new User();

        user.setCreateTime(now);
        user.setName(userRequestVO.getName());
        user.setAge(userRequestVO.getAge());
        user.setEmail(userRequestVO.getEmail());

        userRepository.save(user);

        return user.getId();
    }

    @Override
    @Transactional(rollbackFor = TransactionException.class)
    public Boolean removeUser(Long id) {
        userRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateUser(Long id, UserRequestVO userRequestVO) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new IllegalArgumentException("No such user");
        }

        User user = userOptional.get();
        user.setEmail(userRequestVO.getEmail());
        user.setAge(userRequestVO.getAge());
        user.setName(userRequestVO.getName());

        userRepository.save(user);
        return Boolean.TRUE;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }
}
