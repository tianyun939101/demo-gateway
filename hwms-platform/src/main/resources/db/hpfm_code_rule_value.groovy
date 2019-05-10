package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_code_rule_value.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hpfm_code_rule_value") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_code_rule_value_s', startValue:"1")
        }
        createTable(tableName: "hpfm_code_rule_value", remarks: "编码规则明细值") {
            column(name: "rule_value_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "rule_detail_id", type: "bigint(20)",  remarks: "hpfm_code_rule_detail.rule_detail_id")  {constraints(nullable:"false")}  
            column(name: "level_value", type: "varchar(" + 30 * weight + ")",  remarks: "应用层级值")  {constraints(nullable:"false")}  
            column(name: "current_value", type: "bigint(20)",  remarks: "当前值")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"rule_detail_id,level_value",tableName:"hpfm_code_rule_value",constraintName: "hpfm_code_rule_value_u1")
    }
}