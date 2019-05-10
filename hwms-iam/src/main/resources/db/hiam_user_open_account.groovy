package script.db

databaseChangeLog(logicalFilePath: 'script/db/hiam_user_open_account.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hiam_user_open_account") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_user_open_account_s', startValue:"1")
        }
        createTable(tableName: "hiam_user_open_account", remarks: "用户第三方账号") {
            column(name: "open_account_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID")  {constraints(primaryKey: true)} 
            column(name: "user_id", type: "bigint(20)",  remarks: "用户ID")  {constraints(nullable:"false")}  
            column(name: "open_id", type: "varchar(" + 60 * weight + ")",  remarks: "三方网站OpenId")  {constraints(nullable:"false")}  
            column(name: "open_name", type: "varchar(" + 60 * weight + ")",  remarks: "三方网站账户名称")  {constraints(nullable:"false")}  
            column(name: "open_app_id", type: "bigint(20)",  remarks: "三方网站ID，关联 hiam_open_app.open_app_id")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

    }
}