package script.db

databaseChangeLog(logicalFilePath: 'script/db/hiam_role_authority.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hiam_role_authority") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_role_authority_s', startValue:"1")
        }
        createTable(tableName: "hiam_role_authority", remarks: "角色数据权限定义") {
            column(name: "role_auth_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "role_id", type: "bigint(20)",  remarks: "角色ID，IAM_ROLE.ID")  {constraints(nullable:"false")}  
            column(name: "auth_doc_type_id", type: "bigint(20)",  remarks: "单据类型ID，HIAM_DOC_TYPE.DOC_TYPE_ID")  {constraints(nullable:"false")}  
            column(name: "auth_scope_code", type: "varchar(" + 30 * weight + ")",  remarks: "权限限制范围，HIAM.AUTHORITY_SCOPE_CODE")   
            column(name: "msg_flag", type: "tinyint(1)",   defaultValue:"0",   remarks: "消息发送标识")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"role_id,auth_doc_type_id",tableName:"hiam_role_authority",constraintName: "hiam_role_authority_u1")
    }
}