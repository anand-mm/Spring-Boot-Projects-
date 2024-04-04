package com.codeforyou.recaptcha.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;



@Entity
@Table(name = "employee")
@Data
public class EmployeeModel {
	
	@Id  
	@GeneratedValue
	private Integer id;
    @Column
	private String name;
    @Column
	private Double salary;
    @Column
	private String address;
    
}
