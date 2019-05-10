package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_audit_login.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hpfm_audit_login") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_audit_login_s', startValue:"1")
        }
        createTable(tableName: "hpfm_audit_login", remarks: "") {
            column(name: "audit_id", type: "bigint(20)", autoIncrement: true ,   remarks: "")  {constraints(primaryKey: true)} 
            column(name: "audit_type", type: "varchar(" + 30 * weight + ")",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "user_id", type: "bigint(20)",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "login_name", type: "varchar(" + 128 * weight + ")",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "phone", type: "varchar(" + 32 * weight + ")",  remarks: "")   
            column(name: "email", type: "varchar(" + 128 * weight + ")",  remarks: "")   
            column(name: "tenant_id", type: "bigint(20)",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "tenant_name", type: "varchar(" + 120 * weight + ")",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "login_date", type: "datetime",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "login_ip", type: "varchar(" + 120 * weight + ")",  remarks: "")   
            column(name: "login_client", type: "varchar(" + 32 * weight + ")",  remarks: "")   
            column(name: "login_platform", type: "varchar(" + 250 * weight + ")",   defaultValue:"0",   remarks: "")   
            column(name: "login_os", type: "varchar(" + 250 * weight + ")",   defaultValue:"1",   remarks: "")   
            column(name: "login_browser", type: "varchar(" + 250 * weight + ")",  remarks: "")   
            column(name: "login_status", type: "tinyint(1)",   defaultValue:"1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "login_message", type: "varchar(" + 250 * weight + ")",  remarks: "")   
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

    }
}