package com.engineer.common.enums.impl;

import com.engineer.common.enums.ExceptionEnum;

public enum FinalResultEnum implements ExceptionEnum {

    DifferenceList_Not_found(404,"未查询到调差"),
    Difference_Save_Failed(500,"保存调差失败"),
    Difference_Update_Failed(500,"更新调差失败"),
    Difference_Delete_Failed(500,"删除调差失败"),
    Difference_Upload_Failed(500,"上传文件失败"),
    Difference_Download_Failed(500,"下载文件失败"),


    VariableList_Not_found(404,"未查询到变量"),
    Variable_Save_Failed(500,"保存变量失败"),
    Variable_Update_Failed(500,"更新变量失败"),
    Variable_Delete_Failed(500,"删除变量失败"),
    Variable_Download_Failed(500,"下载文件失败"),
    Variable_Upload_Failed(500,"上传文件失败"),

    List_Not_Found(404,"未查询到菜单"),
    Save_Failed(500,"保存失败"),
    Update_Failed(500,"更新失败"),
   Delete_Failed(500,"删除失败"),
    Download_Failed(500,"下载文件失败"),
    Upload_Failed(500,"上传文件失败"),
    Find_Failed(500,"查询失败"),


    QuotaList_Update_Failed(500,"更新失败（非法格式），请勿修改工料机编码！"),
    QuotaAmend_Update_Failed(500,"更新失败（非法格式），请勿修改修正ID！")


    ;

    private int code;
    private String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    FinalResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
