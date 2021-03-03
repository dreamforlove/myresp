package com.engineer.result.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="zy_quota_amend_table")
public class QuotaAmend {
    @Id
    private Long amend_id;
    private Long proj_id;
    private String amend_symbol;
    private Long productid;
}
