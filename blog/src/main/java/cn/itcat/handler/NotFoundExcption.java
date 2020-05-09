package cn.itcat.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/*定义状态码*/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExcption extends RuntimeException {
    /*继承runtimeExcption*/
    public NotFoundExcption() {
        super();
    }

    public NotFoundExcption(String message) {
        super(message);
    }

    public NotFoundExcption(String message, Throwable cause) {
        super(message, cause);
    }
}
