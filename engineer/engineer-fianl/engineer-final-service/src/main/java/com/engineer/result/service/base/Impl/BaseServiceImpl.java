package com.engineer.result.service.base.Impl;

import com.engineer.result.mapper.*;
import com.engineer.result.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    public BillQuantityMapper billQuantityMapper;
    @Autowired
    public CatalogManagementMapper catalogManagementMapper;
    @Autowired
    public ChargingMapper chargingMapper;
    @Autowired
    public DifferenceMapper differenceMapper;
    @Autowired
    public MaterialsMapper materialsMapper;
    @Autowired
    public ProjectInformationMapper projectInformationMapper;
    @Autowired
    public QuotaAmendMapper quotaAmendMapper;
    @Autowired
    public QuotaListMapper quotaListMapper;
    @Autowired
    public SingleEngineerMapper singleEngineerMapper;
    @Autowired
    public UnitEngineerMapper unitEngineerMapper;
    @Autowired
    public VariableMapper variableMapper;

}
