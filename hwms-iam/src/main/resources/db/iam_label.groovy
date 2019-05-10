package script.db

databaseChangeLog(logicalFilePath: 'script/db/iam_label.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-iam_label") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'iam_label_s', startValue:"1")
        }
        createTable(tableName: "iam_label", remarks: "") {
            column(name: "id", type: "bigint(20)", autoIncrement: true ,   remarks: "")  {constraints(primaryKey: true)} 
            column(name: "name", type: "varchar(" + 64 * weight + ")",  remarks: "名称")  {constraints(nullable:"false")}  
            column(name: "type", type: "varchar(" + 32 * weight + ")",  remarks: "类型")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "")   
            column(name: "created_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   

        }

        addUniqueConstraint(columnNames:"name,type",tableName:"iam_label",constraintName: "iam_label_u1")
    }

    changeSet(author: 'hzero@hand-china.com', id: '2019-04-25-iam-label-add-column') {
        addColumn(tableName: 'IAM_LABEL') {
            column(name: 'FD_LEVEL', type: "VARCHAR(32)", remarks: '层级', afterColumn: 'TYPE') {
                constraints(nullable: false)
            }
            column(name: 'DESCRIPTION', type: "VARCHAR(128)", remarks: '描述', afterColumn: 'FD_LEVEL')
        }
    }

}