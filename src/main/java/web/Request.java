package web;

import web.types.RequestType;

public class Request {
    private RequestType type;
    private String domen;
    private String path;
    private String params;

    public Request(RequestType type, String domen, String path, String params) {
        this.type = type;
        this.domen = domen;
        this.path = path;
        this.params = params;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public String getDomen() {
        return domen;
    }

    public void setDomen(String domen) {
        this.domen = domen;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
