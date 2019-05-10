package script.db

databaseChangeLog(logicalFilePath: 'script/db/hsgp_zuul_rate_limit.groovy') {
    changeSet(author: "qingsheng.chen@hand-china.com", id: "2019-01-02-hsgp_zuul_rate_limit") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hsgp_zuul_rate_limit_s', startValue:"1")
        }
        createTable(tableName: "hsgp_zuul_rate_limit", remarks: "Zuul限流设置") {
            column(name: "rate_limit_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "rate_limit_key", type: "varchar(" + 80 * weight + ")",  remarks: "代码")  {constraints(nullable:"false")}  
            column(name: "rate_limit_type", type: "varchar(" + 30 * weight + ")",  remarks: "限流方式，HPFM.ZUUL_RATE_LIMIT_TYPE")  {constraints(nullable:"false")}  
            column(name: "service_name", type: "varchar(" + 120 * weight + ")",  remarks: "Zuul微服务名称")  {constraints(nullable:"false")}  
            column(name: "service_conf_label", type: "varchar(" + 240 * weight + ")",  remarks: "Zuul微服务配置标签")   
            column(name: "service_conf_profile", type: "varchar(" + 240 * weight + ")",  remarks: "Zuul微服务配置Profile")   
            column(name: "enabled_flag", type: "tinyint(1)",   defaultValue:"1",   remarks: "是否启用。1启用，0未启用")  {constraints(nullable:"false")}  
            column(name: "refresh_status", type: "tinyint(1)",   defaultValue:"-1",   remarks: "刷新状态")   
            column(name: "refresh_message", type: "varchar(" + 360 * weight + ")",  remarks: "刷新消息")   
            column(name: "refresh_time", type: "datetime",  remarks: "最后一次刷新时间")   
            column(name: "remark", type: "longtext",  remarks: "备注说明")   
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

   addUniqueConstraint(columnNames:"rate_limit_key,rate_limit_type,service_name,service_conf_label,service_conf_profile",tableName:"hsgp_zuul_rate_limit",constraintName: "hsgp_zuul_rate_limit_u1")   
    }
}