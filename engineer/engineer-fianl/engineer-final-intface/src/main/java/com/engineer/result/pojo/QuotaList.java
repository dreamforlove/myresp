package com.engineer.result.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "zy_quota_list")
public class QuotaList {

    @Id
    private Long lmm_id;
    private Long proj_id;
    private Long sequence;
    private String lmm_name;
    private String unit;
    private Double quantity;
    private Double cost;
    private Double comb_price;
    private Long quota_id;
    private Long productid;
}
