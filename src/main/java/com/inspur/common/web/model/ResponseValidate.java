package com.inspur.common.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 校验对象
 */
@Data
@NoArgsConstructor
public class ResponseValidate implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     */
    private boolean valid;

    private String msg;

    public static ResponseValidate ok() {
        return create(true);
    }

    public static ResponseValidate fail() {
        return create(false);
    }

    public static ResponseValidate create(boolean valid) {
        return new ResponseValidate().setValid(valid);
    }
}
