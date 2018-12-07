package cn.edu.seu.dynamic.service.drools;

/**
 * Created by Xul on 2018/10/17.
 */
public class ReturnData {
    private String subject;
    private String predicate;
    private String object;

    private String reason;

    public ReturnData() {

    }

    public ReturnData(String subject, String predicate, String object, String reason) {
        this.subject=subject;
        this.predicate=predicate;
        this.object=object;
        this.reason=reason;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPredicate(String predicate) {
        this.predicate = predicate;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSubject() {
        return subject;
    }

    public String getPredicate() {
        return predicate;
    }

    public String getObject() {
        return object;
    }

    public String getReason() {
        return reason;
    }
}
