package com.codeforyou.recaptcha.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeforyou.recaptcha.Model.EmployeeModel;

@Repository
public interface EmployeeRepository  extends JpaRepository<EmployeeModel,Integer>{
    
}
