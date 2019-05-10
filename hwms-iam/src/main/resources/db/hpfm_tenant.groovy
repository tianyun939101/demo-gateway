package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_tenant.groovy') {
    def weight = 1
    if(helper.isSqlServer()){
        weight = 2
    } else if(helper.isOracle()){
        weight = 3
    }
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hpfm_tenant") {
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_tenant_s', startValue:"1")
        }
        createTable(tableName: "hpfm_tenant", remarks: "租户信息") {
            column(name: "tenant_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "tenant_num", type: "varchar(" + 30 * weight + ")",  remarks: "租户编码")  {constraints(nullable:"false")}  
            column(name: "tenant_name", type: "varchar(" + 120 * weight + ")",  remarks: "租户名")  {constraints(nullable:"false")}  
            column(name: "enabled_flag", type: "tinyint(1)",   defaultValue:"1",   remarks: "是否启用。1启用，0未启用")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }
        createIndex(tableName: "hpfm_tenant", indexName: "hpfm_tenant_n1") {
            column(name: "tenant_num")
        }
    }

    changeSet(author: "xiaoyu.zhao@hand-china.com", id: "2019-04-29-hpfm_tenant") {
        addColumn(tableName: "hpfm_tenant") {
            column(name: "active_users", type: "int(11)", remarks: "租户下的有效用户数，null表示不限制")
        }
    }
    changeSet(author: "xiaoyu.zhao@hand-china.com", id: "2019-05-08-hpfm_tenant") {
        renameColumn(columnDataType: 'int(11)', newColumnName: "limit_user_qty", oldColumnName: "active_users", remarks: '限制租户下的有效用户数，null表示不限制', tableName: 'hpfm_tenant')
    }
    changeSet(author: "xiaoyu.zhao@hand-china.com", id: "2019-05-10-hpfm_tenant") {
        dropIndex(tableName: "hpfm_tenant", indexName: "hpfm_tenant_n1")
        addUniqueConstraint(columnNames:"tenant_num",tableName:"hpfm_tenant",constraintName: "hpfm_tenant_u1")
    }
}
