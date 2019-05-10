package script.db

databaseChangeLog(logicalFilePath: 'script/db/hiam_doc_type_auth_dim.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hiam_doc_type_auth_dim") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_doc_type_auth_dim_s', startValue:"1")
        }
        createTable(tableName: "hiam_doc_type_auth_dim", remarks: "单据类型权限维度定义") {
            column(name: "auth_dim_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID")  {constraints(primaryKey: true)} 
            column(name: "doc_type_id", type: "bigint(20)",  remarks: "单据类型ID")  {constraints(nullable:"false")}  
            column(name: "auth_type_code", type: "varchar(" + 30 * weight + ")",  remarks: "权限类型代码，HIAM.AUTHORITY_TYPE_CODE")  {constraints(nullable:"false")}  
            column(name: "source_match_table", type: "varchar(" + 30 * weight + ")",  remarks: "来源匹配表名")   
            column(name: "source_match_field", type: "varchar(" + 30 * weight + ")",  remarks: "采购方匹配字段，匹配Mapper中的相关字段")   
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "sup_source_match_field", type: "varchar(" + 30 * weight + ")",  remarks: "供应方匹配字段，匹配Mapper中的相关字段")   

        }

        addUniqueConstraint(columnNames:"doc_type_id,auth_type_code",tableName:"hiam_doc_type_auth_dim",constraintName: "hiam_doc_type_auth_dim_u1")
    }
}