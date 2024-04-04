package com.gepnic.mobileservice.TenderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TenderDetailsRowMapper implements RowMapper<TenderDetails> {

    @Override
    public TenderDetails mapRow(ResultSet rs, int rowNum) throws SQLException {

        TenderDetails tenderDetails = new TenderDetails();
        tenderDetails.setId(rs.getLong("id"));
        tenderDetails.setTenderbasicid(rs.getLong("tenderbasicid"));
        tenderDetails.setTendertitle(rs.getString("tendertitle"));
        tenderDetails.setWorkdesc(rs.getString("workdesc"));
        return tenderDetails;

    }

}
