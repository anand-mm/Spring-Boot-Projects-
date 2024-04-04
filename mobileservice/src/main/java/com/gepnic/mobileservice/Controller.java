package com.gepnic.mobileservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gepnic.mobileservice.Model.Parameters.TenderDetailsParam;
import com.gepnic.mobileservice.TenderDetails.TenderDetails;
import com.gepnic.mobileservice.TenderDetails.TenderDetailsRepository;


@RestController
@RequestMapping("/v1")
public class Controller {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    TenderDetailsRepository tenderDetailsRepository;

    @GetMapping("/getTenders")
    public List<Map<String,Object>> getTenderDetails(){
        String sql = "select * from gep_tender_work_items limit 10";
        return jdbcTemplate.queryForList(sql);
    }
    
    @GetMapping("/getAllTenders")
    public List<TenderDetails> getMethodName(TenderDetailsParam tenderDetailsParam) {
        return tenderDetailsRepository.findAllTenders(tenderDetailsParam);
    }
    
}
