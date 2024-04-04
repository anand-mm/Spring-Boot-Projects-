package com.gepnic.mobileservice.TenderDetails;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gepnic.mobileservice.Model.Parameters.TenderDetailsParam;

@Repository
public class TenderDetailsRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<TenderDetails> findAllTenders(TenderDetailsParam tenderDetailsParam) {


        StringBuilder sql = new StringBuilder();
         sql.append("SELECT wi.id as workitemid,wi.tendertitle,wi.workitemrefno,pc.name as productcategory,ocm.orgchain as organization,"
        + "wi.tendervalue,tbd.tenderrefno,gba.contractvalue,tfd.emdfeefixedamt,"
        +"case when wi.tenderstatus ='Retender' then 'Retender' when wi.tenderstatus ='Cancelled' then 'Cancelled' "
        +" when wi.tenderstatus ='Expired' then 'AOC' else case when wi.tender_stage ='To_be_Opened' then 'To be Opened' "
        +"when wi.tender_stage ='Bid_Opened_1' then 'Technical Bid Opened' when wi.tender_stage ='Evaluated_Technical' then 'Technical Evaluation' "
        +"when wi.tender_stage ='Bid_Opened_2' then 'Financial Bid Opened' when wi.tender_stage ='Evaluated_Financial' then 'Financial Evaluation' "
        + "when wi.tender_stage ='AOC' then 'AOC' end end as tenderstatus,approveddate as publisheddate,tcd.bidsubclosingdate as closingdate,tcd.bidopeningdate as openingdate"
        + "FROM gep_tender_work_items wi JOIN gep_tender_basic_details tbd on tbd.id=wi.tenderbasicid "
        +"JOIN gep_tender_critical_dates tcd on tcd.workitemid = wi.id JOIN gep_orgchain_master ocm ON ocm.id = tbd.actualorgid "
        +"JOIN gep_product_category pc ON pc.id = wi.productcategory JOIN gep_master_tender_type mtt ON mtt.id = tbd.tendertype "
        +"LEFT JOIN gep_bid_aoc gba ON wi.id = gba.workitemid LEFT JOIN gep_tender_fee_details tfd ON wi.id=tfd.workitemid "
        +"WHERE wi.ispublished = true AND mtt.tendertype !='Closed Limited' AND tcd.publishdate < now() ");

        // +"ORDER BY wi.id DESC OFFSET :rowNo LIMIT 10;");



        // String sql = "select * from gep_tender_work_items offset :rowNo LIMIT 10;";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("rowNo", tenderDetailsParam.getRowNo(), Types.INTEGER);

        

        return namedParameterJdbcTemplate.query(sql.toString(),mapSqlParameterSource, (rs, rowNum) -> new TenderDetails(
                rs.getLong("id"), rs.getLong("tenderbasicid"), rs.getString("tendertitle"), rs.getString("workdesc")));

    }

}
