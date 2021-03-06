package com.assignment.readingisgood.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMER")
public class Customer {
    @NotNull
    @Id
    private String id;
    private String name;
    private String email_id;
    private String mobile_no;

    public Customer(String name,String email_id,String mobile_no){
        this.email_id = email_id;
        this.name = name;
        this.mobile_no = mobile_no;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email_id='" + email_id + '\'' +
                ", mobile_no='" + mobile_no + '\'' +
                '}';
    }
}
