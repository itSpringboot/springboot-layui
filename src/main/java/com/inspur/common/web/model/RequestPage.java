package com.inspur.common.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
* @ClassName: RequestPage
* @Description: 通用请求分页参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPage implements Serializable {

	private int page, limit;
}
