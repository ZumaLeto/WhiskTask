package model;


import java.util.Map;

public class WhiskResponse {
    private Map<String, String> list;
    private Object content;
    private String code;
    private String error_code;
    private String message;

    public Object getContent() {
        return content;
    }

    public Map<String, String> getList() {
        return list;
    }

    public String getCode() {
        return code;
    }
}
