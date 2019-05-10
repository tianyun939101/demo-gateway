package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_dashboard_card.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hpfm_dashboard_card") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_dashboard_card_s', startValue:"1")
        }
        createTable(tableName: "hpfm_dashboard_card", remarks: "平台卡片表") {
            column(name: "id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "code", type: "varchar(" + 250 * weight + ")",  remarks: "卡片编码")  {constraints(nullable:"false")}  
            column(name: "fd_level", type: "varchar(" + 32 * weight + ")",  remarks: "卡片所属级别")  {constraints(nullable:"false")}  
            column(name: "catalog_type", type: "varchar(" + 32 * weight + ")",  remarks: "目录类型")  {constraints(nullable:"false")}  
            column(name: "name", type: "varchar(" + 32 * weight + ")",  remarks: "卡片名称")  {constraints(nullable:"false")}  
            column(name: "description", type: "varchar(" + 250 * weight + ")",  remarks: "卡片描述")   
            column(name: "w", type: "tinyint(3)",  remarks: "卡片宽")  {constraints(nullable:"false")}  
            column(name: "h", type: "tinyint(3)",  remarks: "卡片高")  {constraints(nullable:"false")}  
            column(name: "enabled_flag", type: "tinyint(1)",   defaultValue:"1",   remarks: "是否启用。1启用，0未启用")  {constraints(nullable:"false")}  
            column(name: "logo", type: "varchar(" + 300 * weight + ")",  remarks: "卡片图标")   
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"code",tableName:"hpfm_dashboard_card",constraintName: "hpfm_dashboard_card_u1")
    }

    changeSet(author: "xiaoyu.zhao@hand-china.com", id: "2019-04-11-hpfm_dashboard_card") {
        addColumn(tableName: "hpfm_dashboard_card"){
            column(name: "tenant_id", type: "bigint(20)",  remarks: "租户ID")  {constraints(nullable:"false")}
        }
        dropUniqueConstraint(tableName: "hpfm_dashboard_card", constraintName: "hpfm_dashboard_card_u1")
        addUniqueConstraint(columnNames: "code,tenant_id", tableName: "hpfm_dashboard_card", constraintName: "hpfm_dashboard_card_u1")
    }
    changeSet(author: "xiaoyu.zhao@hand-china.com", id: "2019-05-08-hpfm_dashboard_card") {
        addDefaultValue(tableName: "hpfm_dashboard_card", columnName: "tenant_id", defaultValue: "0")
    }
}
