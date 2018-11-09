package com.inspur.common.web.controller;

import java.io.Serializable;

public interface BaseController<T extends Serializable, P extends Serializable>
        extends BaseCreateController<T>, BaseUpdateController<T>, BaseDeleteController<T, P>, BaseQueryController<T>, BaseGetController<T, P>, BaseValidateController<T> {
}
