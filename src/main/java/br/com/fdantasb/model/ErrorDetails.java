package br.com.fdantasb.model;

import java.io.Serializable;

public class ErrorDetails implements Serializable{
	
	private static final long serialVersionUID = 4429686955717261603L;
	
	private String title;
    private Long status;
    private Long timestamp;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
