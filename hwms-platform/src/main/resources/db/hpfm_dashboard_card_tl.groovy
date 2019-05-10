package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_dashboard_card_tl.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hpfm_dashboard_card_tl") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_dashboard_card_tl_s', startValue:"1")
        }
        createTable(tableName: "hpfm_dashboard_card_tl", remarks: "卡片多语言表") {
            column(name: "id", type: "bigint(20)",  remarks: "关联dashboard_card表 id")  {constraints(nullable:"false")}  
            column(name: "lang", type: "varchar(" + 16 * weight + ")",  remarks: "语言名称")  {constraints(nullable:"false")}  
            column(name: "name", type: "varchar(" + 255 * weight + ")",  remarks: "卡片名")  {constraints(nullable:"false")}  
            column(name: "description", type: "varchar(" + 255 * weight + ")",  remarks: "卡片描述")   
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"id,lang",tableName:"hpfm_dashboard_card_tl",constraintName: "hpfm_dashboard_card_tl_u1")
    }
    changeSet(author: "xiaoyu.zhao@hand-china.com", id: "2019-03-11-hpfm_dashboard_card_tl") {
        dropColumn(tableName: "hpfm_dashboard_card_tl", columnName: "creation_date")
        dropColumn(tableName: "hpfm_dashboard_card_tl", columnName: "created_by")
        dropColumn(tableName: "hpfm_dashboard_card_tl", columnName: "last_updated_by")
        dropColumn(tableName: "hpfm_dashboard_card_tl", columnName: "last_update_date")
    }
}
