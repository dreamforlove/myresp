package com.engineer.result.controller;

import com.engineer.result.service.*;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    @Autowired
    public BillQuantityService billQuantityService;

    @Autowired
    public CatalogManagementService catalogManagementService;

    @Autowired
    public ChargingService chargingService;

    @Autowired
    public DifferenceService differenceService;

    @Autowired
    public MaterialsService materialsService;

    @Autowired
    public ProjectInformationService projectInformationService;

    @Autowired
    public QuotaAmendService quotaAmendService;

    @Autowired
    public QuotaListService quotaListService;

    @Autowired
    public SingleEngineerService singleEngineerService;

    @Autowired
    public UnitEngineerService unitEngineerService;

    @Autowired
    public VariableService variableService;


}
