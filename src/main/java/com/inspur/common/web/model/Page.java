package com.inspur.common.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wu.bing on 2018/11/6.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 3035157868344176539L;

    private int code, count;
    private String msg;
    private List<T> data;
}
