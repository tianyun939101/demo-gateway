package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_datasource.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hpfm_datasource") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_datasource_s', startValue:"1")
        }
        createTable(tableName: "hpfm_datasource", remarks: "数据源配置") {
            column(name: "datasource_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键")  {constraints(primaryKey: true)} 
            column(name: "ds_purpose_code", type: "varchar(" + 30 * weight + ")",  remarks: "数据源用途，值集：HPFM.DATASOURCE_PURPOSE DT:数据分发 DI:数据导入 DR:数据报表")  {constraints(nullable:"false")}  
            column(name: "db_type", type: "varchar(" + 30 * weight + ")",  remarks: "数据库类型，值集:HPFM.DATABASE_TYPE")  {constraints(nullable:"false")}  
            column(name: "datasource_code", type: "varchar(" + 30 * weight + ")",  remarks: "数据源编码")  {constraints(nullable:"false")}  
            column(name: "description", type: "varchar(" + 600 * weight + ")",  remarks: "说明")   
            column(name: "driver_class", type: "varchar(" + 240 * weight + ")",  remarks: "数据源驱动类")  {constraints(nullable:"false")}  
            column(name: "datasource_url", type: "varchar(" + 600 * weight + ")",  remarks: "数据源URL地址")  {constraints(nullable:"false")}  
            column(name: "username", type: "varchar(" + 100 * weight + ")",  remarks: "用户")  {constraints(nullable:"false")}  
            column(name: "password_encrypted", type: "varchar(" + 300 * weight + ")",  remarks: "加密密码")  {constraints(nullable:"false")}  
            column(name: "db_pool_type", type: "varchar(" + 30 * weight + ")",  remarks: "连接池类型，独立值集：HPFM.DB_POOL_TYPE")  {constraints(nullable:"false")}  
            column(name: "queryer_class", type: "varchar(" + 240 * weight + ")",  remarks: "获取报表引擎查询器类名")   
            column(name: "pool_class", type: "varchar(" + 240 * weight + ")",  remarks: "报表引擎查询器使用的数据源连接池类名")   
            column(name: "options", type: "longtext",  remarks: "数据源配置选项(JSON格式）")   
            column(name: "remark", type: "varchar(" + 240 * weight + ")",  remarks: "")   
            column(name: "enabled_flag", type: "tinyint(1)",   defaultValue:"1",   remarks: "是否启用，1启用、0禁用")   
            column(name: "tenant_id", type: "bigint(20)",   defaultValue:"0",   remarks: "租户ID,hpfm_tenant.tenant_id")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }
   createIndex(tableName: "hpfm_datasource", indexName: "hpfm_datasource_u1") {
            column(name: "datasource_code")
            column(name: "tenant_id")
        }

    }
}