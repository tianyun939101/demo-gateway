package script.db

databaseChangeLog(logicalFilePath: 'script/db/hsgp_hystrix_conf_line.groovy') {
    changeSet(author: "qingsheng.chen@hand-china.com", id: "2019-01-02-hsgp_hystrix_conf_line") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hsgp_hystrix_conf_line_s', startValue:"1")
        }
        createTable(tableName: "hsgp_hystrix_conf_line", remarks: "") {
            column(name: "conf_line_id", type: "bigint(20)", autoIncrement: true ,   remarks: "")  {constraints(primaryKey: true)} 
            column(name: "conf_id", type: "bigint(20)",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "property_name", type: "varchar(" + 240 * weight + ")",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "property_value", type: "varchar(" + 120 * weight + ")",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "enabled_flag", type: "tinyint(1)",   defaultValue:"1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "order_seq", type: "int(11)",  remarks: "")   
            column(name: "remark", type: "longtext",  remarks: "")   
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

   addUniqueConstraint(columnNames:"conf_id,property_name",tableName:"hsgp_hystrix_conf_line",constraintName: "hsgp_hystrix_conf_line_U1")   
    }
}