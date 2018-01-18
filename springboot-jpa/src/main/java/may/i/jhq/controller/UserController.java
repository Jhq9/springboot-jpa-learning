package may.i.jhq.controller;

import may.i.jhq.core.Result;
import may.i.jhq.core.ResultGenerator;
import may.i.jhq.service.UserService;
import may.i.jhq.vo.UserRequestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jinhuaquan
 * @create 2018-01-18 上午10:05
 * @desc The controller of user
 **/
@RestController
@RequestMapping(value = "/users")
public class UserController {

    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(value = "")
    public Result saveUser(@RequestBody UserRequestVO userRequestVO) {
        LOGGER.info("API 调用 ： 新增一个User");

        return ResultGenerator.genSuccessResult(userService.saveUser(userRequestVO));
    }

    @DeleteMapping(value = "/{id}")
    public Result removeUser(@PathVariable Long id) {
        LOGGER.info("API 调用 ： 删除一个User");

        return ResultGenerator.genSuccessResult(userService.removeUser(id));
    }

    @PutMapping(value = "/{id}")
    public Result updateUser(@PathVariable Long id, @RequestBody UserRequestVO userRequestVO) {
        LOGGER.info("API 调用 ： 更新User的信息");

        return ResultGenerator.genSuccessResult(userService.updateUser(id, userRequestVO));
    }

    @GetMapping(value = "/{id}")
    public Result getUser(@PathVariable Long id) {
        LOGGER.info("APi 调用 ： 根据id获取User信息");

        return ResultGenerator.genSuccessResult(userService.getUserById(id));
    }

    @GetMapping(value = "")
    public Result listUser() {
        LOGGER.info("API 调用 ： 罗列User");

        return ResultGenerator.genSuccessResult(userService.listUser());
    }
}
