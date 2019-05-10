package script.db

databaseChangeLog(logicalFilePath: 'script/db/hiam_user_config.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hiam_user_config") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_user_config_s', startValue:"1")
        }
        createTable(tableName: "hiam_user_config", remarks: "用户默认配置") {
            column(name: "user_config_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "user_id", type: "bigint(20)",  remarks: "用户ID，iam_user.id")  {constraints(nullable:"false")}  
            column(name: "tenant_id", type: "bigint(20)",  remarks: "租户ID，hpfm_tenant.tenant_id")  {constraints(nullable:"false")}  
            column(name: "default_company_id", type: "bigint(20)",  remarks: "默认公司ID，hpfm_company.company_id")   
            column(name: "default_role_id", type: "bigint(20)",  remarks: "默认角色ID，iam_role.id")   
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"user_id,tenant_id",tableName:"hiam_user_config",constraintName: "hiam_user_config_u1")
    }
}