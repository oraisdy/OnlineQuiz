package com.example.qs.entity;

/**
 * Created by phoebegl on 2017/12/12.
 */
public class ResponseInfo {

    private int responseStatus;

    private Object ResponseBody;

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Object getResponseBody() {
        return ResponseBody;
    }

    public void setResponseBody(Object responseBody) {
        ResponseBody = responseBody;
    }
}
