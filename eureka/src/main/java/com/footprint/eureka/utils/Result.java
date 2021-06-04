package com.footprint.eureka.utils;

/**
 * @author W.Q
 * @project footprint
 * @package com.footprint.eureka.utils
 * @date 2021.6.2
 * @description 向前端返回数据时提供统一的格式
 */
public class Result {

    private Integer code;
    private String msg;
    private Object data;

    public Result() {

    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
