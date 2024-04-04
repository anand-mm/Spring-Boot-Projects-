package com.gepnic.mobileservice.TenderDetails;

import lombok.Data;

@Data
public class TenderDetails {

    public TenderDetails() {
    };

    public TenderDetails(long long1, long long2, String string, String string2) {

        this.id = long1;
        this.tenderbasicid = long2;
        this.tendertitle = string;
        this.workdesc = string2;
    }

    private Long id;
    private Long tenderbasicid;
    private String tendertitle;
    private String workdesc;

}
