package script.db

databaseChangeLog(logicalFilePath: 'script/db/oauth_ldap.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-oauth_ldap") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'oauth_ldap_s', startValue:"1")
        }
        createTable(tableName: "oauth_ldap", remarks: "") {
            column(name: "id", type: "bigint(20)", autoIncrement: true ,   remarks: "")  {constraints(primaryKey: true)} 
            column(name: "name", type: "varchar(" + 64 * weight + ")",  remarks: "ldap的名称")  {constraints(nullable:"false")}  
            column(name: "organization_id", type: "bigint(20)",  remarks: "组织id")  {constraints(nullable:"false")}  
            column(name: "server_address", type: "varchar(" + 64 * weight + ")",  remarks: "ldap服务器地址")  {constraints(nullable:"false")}  
            column(name: "port", type: "varchar(" + 8 * weight + ")",   defaultValue:"389",   remarks: "端口号")  {constraints(nullable:"false")}  
            column(name: "account", type: "varchar(" + 128 * weight + ")",  remarks: "")   
            column(name: "password", type: "varchar(" + 128 * weight + ")",  remarks: "")   
            column(name: "use_ssl", type: "bigint(20)",   defaultValue:"0",   remarks: "使用ssl加密传输方式，默认情况为不使用")  {constraints(nullable:"false")}  
            column(name: "is_enabled", type: "bigint(20)",   defaultValue:"1",   remarks: "是否启用，默认为启用")  {constraints(nullable:"false")}  
            column(name: "base_dn", type: "varchar(" + 255 * weight + ")",  remarks: "基础DN")   
            column(name: "DIRECTORY_TYPE", type: "varchar(" + 64 * weight + ")",  remarks: "")  {constraints(nullable:"false")}  
            column(name: "object_class", type: "varchar(" + 64 * weight + ")",  remarks: "对象类型")  {constraints(nullable:"false")}  
            column(name: "login_name_field", type: "varchar(" + 64 * weight + ")",  remarks: "login_name对应的字段名")   
            column(name: "real_name_field", type: "varchar(" + 64 * weight + ")",  remarks: "real_name对应的字段名")   
            column(name: "email_field", type: "varchar(" + 64 * weight + ")",  remarks: "email对应的字段名")   
            column(name: "phone_field", type: "varchar(" + 64 * weight + ")",  remarks: "phone对应的字段名")   
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "")   
            column(name: "created_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   

        }

        addUniqueConstraint(columnNames:"organization_id",tableName:"oauth_ldap",constraintName: "oauth_ldap_u1")
    }

    changeSet(author: 'hzero@hand-china.com', id: '2019-04-25-oauth-ldap-add-column') {
        addColumn(tableName: 'OAUTH_LDAP') {
            column(name: 'CUSTOM_FILTER', type: "VARCHAR(256)", remarks: '同步用户的自定义过滤配置', afterColumn: 'OBJECT_CLASS')
            column(name: 'SAGA_BATCH_SIZE', type: "INTEGER UNSIGNED", remarks: '同步用户每次发送saga的用户数量', afterColumn: 'CUSTOM_FILTER', defaultValue: '500') {
                constraints(nullable: false)
            }
            column(name: 'CONNECTION_TIMEOUT', type: "INTEGER UNSIGNED", remarks: 'ldap服务器连接超时时间，单位为秒，默认值为10秒', afterColumn: 'SAGA_BATCH_SIZE', defaultValue: '10') {
                constraints(nullable: false)
            }
            column(name: 'UUID_FIELD', type: "VARCHAR(64)", remarks: 'ldap中唯一标识对象的字段', afterColumn: 'CONNECTION_TIMEOUT', defaultValue: 'entryUUID') {
                constraints(nullable: false)
            }
        }
        addNotNullConstraint(tableName:'OAUTH_LDAP', columnName: 'ACCOUNT', columnDataType:'VARCHAR(128)', defaultNullValue: 'test')
        addNotNullConstraint(tableName:'OAUTH_LDAP', columnName: 'PASSWORD', columnDataType:'VARCHAR(128)', defaultNullValue: 'test')
        renameColumn(columnDataType: 'VARCHAR(128)', newColumnName: "LDAP_PASSWORD", oldColumnName: "PASSWORD", remarks: 'ldap登陆密码', tableName: 'OAUTH_LDAP')
    }

}