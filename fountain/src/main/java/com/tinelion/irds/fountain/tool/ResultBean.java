package com.tinelion.irds.fountain.tool;

/**
 * Created with IntelliJ IDEA.
 * Created by:  Vivian
 * Date: 2018/7/4 20:46
 */
public class ResultBean {
    private int code;
    private String message;
    private Object data;

    public ResultBean() {
    }

    public ResultBean(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultBean(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultBean that = (ResultBean) o;

        if (code != that.code) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        return data != null ? data.equals(that.data) : that.data == null;
    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "\"code\":\"" + code +
                "\",\"message\":\"" + message + "\"" +
                "\",\"data\":\"" + data +
                "\"}";
    }
}
