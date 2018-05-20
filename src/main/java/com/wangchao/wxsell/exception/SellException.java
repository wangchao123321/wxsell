package com.wangchao.wxsell.exception;

import com.wangchao.wxsell.enums.ResultEnum;
import lombok.Data;

@Data
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }
}
