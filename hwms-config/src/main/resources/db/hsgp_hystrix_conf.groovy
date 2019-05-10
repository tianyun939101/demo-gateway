package script.db

databaseChangeLog(logicalFilePath: 'script/db/hsgp_hystrix_conf.groovy') {
    changeSet(author: "qingsheng.chen@hand-china.com", id: "2019-01-02-hsgp_hystrix_conf") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hsgp_hystrix_conf_s', startValue:"1")
        }
        createTable(tableName: "hsgp_hystrix_conf", remarks: "") {
            column(name: "conf_id", type: "bigint(20)", autoIncrement: true ,   remarks: "")  {constraints(primaryKey: true)} 
            column(name: "conf_key", type: "varchar(120)",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "conf_type_code", type: "varchar(120)",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "service_name", type: "varchar(30)",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "service_conf_label", type: "varchar(30)",  remarks: "")   
            column(name: "service_conf_profile", type: "varchar(30)",  remarks: "")   
            column(name: "enabled_flag", type: "tinyint(1)",   defaultValue:"1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "order_seq", type: "int(11)",  remarks: "")   
            column(name: "refresh_status", type: "tinyint(1)",   defaultValue:"-1",   remarks: "")   
            column(name: "refresh_message", type: "varchar(" + 360 * weight + ")",  remarks: "")   
            column(name: "refresh_time", type: "datetime",  remarks: "")   
            column(name: "remark", type: "longtext",  remarks: "")   
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

   addUniqueConstraint(columnNames:"conf_key,conf_type_code,service_name,service_conf_label,service_conf_profile",tableName:"hsgp_hystrix_conf",constraintName: "hsgp_hystrix_conf_U1")   
    }
}