package may.i.jhq.core;

/**
 * @author jinhuaquan
 * @create 2018-01-18 上午10:22
 * @desc The result generator
 **/
public final class ResultGenerator {

    public static final String MESSAGE_SUCCESS = "Success";

    public static Result genSuccessResult() {
        return genSuccessResult(null);
    }

    public static Result genSuccessResult(Object data) {
        return genResult(CodeEnum.SUCCESS.code, MESSAGE_SUCCESS, data);
    }

    public static Result genFailedResult(String message) {
        return genResult(CodeEnum.FAIL.code, message, null);
    }

    public static Result genFailedResult(int code, String message) {
        return genResult(code, message, null);
    }

    public static Result genResult(int code, String message, Object data) {
        return new Result(code, message, data);
    }

}
