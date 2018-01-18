/**
 * @author jinhuaquan
 * @create 2018-01-18 上午10:08
 * @desc The enum of code
 **/
package may.i.jhq.core;

public enum CodeEnum {

    /**
     * success
     */
    SUCCESS(200),
    /**
     * service failed
     */
    FAIL(400),
    /**
     * authention failed
     */
    AUTHENTICATION_FAILED(401),
    /**
     * unexcept error
     */
    INERNAL_ERROR(500);

    public int code;

    CodeEnum(int code) {
        this.code = code;
    }
}
