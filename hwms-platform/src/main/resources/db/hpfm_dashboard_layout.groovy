package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_dashboard_layout.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hpfm_dashboard_layout") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_dashboard_layout_s', startValue:"1")
        }
        createTable(tableName: "hpfm_dashboard_layout", remarks: "工作台配置") {
            column(name: "id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "code", type: "varchar(" + 250 * weight + ")",  remarks: "卡片编码")  {constraints(nullable:"false")}  
            column(name: "w", type: "tinyint(3)",  remarks: "卡片宽度")  {constraints(nullable:"false")}  
            column(name: "h", type: "tinyint(3)",  remarks: "卡片高度")  {constraints(nullable:"false")}  
            column(name: "x", type: "tinyint(3)",  remarks: "卡片位置X")  {constraints(nullable:"false")}  
            column(name: "y", type: "tinyint(3)",  remarks: "卡片位置Y")  {constraints(nullable:"false")}  
            column(name: "user_id", type: "bigint(20)",  remarks: "用户ID")  {constraints(nullable:"false")}  
            column(name: "role_id", type: "bigint(20)",  remarks: "角色ID")  {constraints(nullable:"false")}  
            column(name: "tenant_id", type: "bigint(20)",   defaultValue:"0",   remarks: "租户ID,hpfm_tenant.tenant_id")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"code,user_id,role_id,tenant_id",tableName:"hpfm_dashboard_layout",constraintName: "hpfm_dashboard_layout_u1")
    }
}