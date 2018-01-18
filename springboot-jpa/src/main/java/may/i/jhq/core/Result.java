package may.i.jhq.core;

import lombok.Data;

/**
 * @author jinhuaquan
 * @create 2018-01-18 上午10:07
 * @desc The result response
 **/
@Data
public class Result {

    private int code;

    private String message;

    private Object data;

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
