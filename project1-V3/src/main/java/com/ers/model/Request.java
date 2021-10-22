package com.ers.model;

import javax.persistence.*;
import java.sql.Timestamp;

@NamedQueries(
    value= {
            @NamedQuery(name="getAllRequests", query="from Request req")
    }
)

@Entity
@Table(name="reimbursement_request_history")
public class Request
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id", nullable = false)
    private int id;

    private Timestamp time_created;
    private Double amount;
    private int employee_id;
    private String reason;
    private String status;
    private Boolean approved;
    private Integer manager_id;
    private String manager_comment;

    public int getId()
    {
        return id;
    }

    public void setId(int request_id)
    {
        this.id = request_id;
    }

    public Timestamp getTimeCreated() {
        return time_created;
    }

    public void setTime_created(Timestamp time_created) {
        this.time_created = time_created;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean isApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Integer getManager_id() {
        return manager_id;
    }

    public void setManager_id(Integer manager_id) {
        this.manager_id = manager_id;
    }

    public String getManager_comment() {
        return manager_comment;
    }

    public void setManager_comment(String manager_comment) {
        this.manager_comment = manager_comment;
    }

    @Override
    public String toString()
    {
        return "Request{" +
                "id=" + id +
                ", time_created=" + time_created +
                ", amount=" + amount +
                ", employee_id=" + employee_id +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                ", approved=" + approved +
                ", manager_id=" + manager_id +
                ", manager_comment='" + manager_comment + '\'' +
                '}';
    }
}
