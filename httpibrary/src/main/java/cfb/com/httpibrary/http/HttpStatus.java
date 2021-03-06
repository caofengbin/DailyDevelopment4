package cfb.com.httpibrary.http;

/**
 * 响应码的定义
 * Created by fengbincao on 2017/5/24.
 */

public enum HttpStatus {

    CONTINUE(100, "Continue"),
    SWITCHING_PROTOCOLS(101, "Switching Protocols"),

    /**
     * 2开头状态码
     */
    OK(200, "OK"),
    CREATED(201, "Created"),
    Accepted(202, "Accepted "),
    NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),
    NO_CONTENT(204, "No Content"),
    RESET_CONTENT(205, "Reset Content"),

    /**
     * 3开头状态码
     */
    MULTIPLE_CHOICES(300, "Multiple Choices"),
    MOVED_PERMANENTLY(301, "Moved Permanently"),
    FOUND(302, "Found"),
    SEE_OTHER(303, "See Other"),
    USE_PROXY(305, "Use Proxy "),
    UNUSED(306, "Unused"),
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),

    /**
     * 4开头状态码
     */
    BAD_REQUEST(400, "Bad Request"),
    PAYMENT_REQUIRED(402, "Payment Required"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not_Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed "),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    REQUEST_TIMEOUT(408, "Request Timeout"),
    CONFLICT(409, "Conflict"),
    GONE(410, "Gone"),
    LENGTH_REQUIRED(411, "Length Required"),
    PAYLOAD_TOO_LARGE(413, "Payload Too Large"),
    URI_TOO_LONG(414, "URI Too Long"),
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type  Server Error"),
    FAILED(417, "Failed Server Error"),
    UPGRADE_REQUIRED(426, "Upgrade Required"),

    /**
     * 5开头状态码
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    BAD_GATEWAY(502, "Bad_Gateway"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    GATEWAY_TIMEOUT(504, "Gateway Timeout"),
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version Not Supported ");

    private int mCode;

    private String mMessage;

    HttpStatus(int code, String message) {
        this.mCode = code;
        this.mMessage = message;
    }

    /**
     * 状态码以2开头的表示请求成功
     * @return          请求是否成功
     */
    public boolean isSuccess() {
        int value = mCode / 100;
        return value == 2;
    }

    public static HttpStatus getValue(int value) {
        for(HttpStatus httpStatus : values()) {
            if(value == httpStatus.mCode) {
                return httpStatus;
            }
        }
        return null;
    }
}
