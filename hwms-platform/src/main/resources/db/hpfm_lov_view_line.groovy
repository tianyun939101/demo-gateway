package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_lov_view_line.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hpfm_lov_view_line") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_lov_view_line_s', startValue:"1")
        }
        createTable(tableName: "hpfm_lov_view_line", remarks: "值集查询视图行表") {
            column(name: "view_line_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键 ")  {constraints(primaryKey: true)} 
            column(name: "view_header_id", type: "bigint(20)",  remarks: "值集查询视图头表ID,hpfm_fnd_lov_view_header.view_header_id")  {constraints(nullable:"false")}  
            column(name: "tenant_id", type: "bigint(20)",   defaultValue:"0",   remarks: "租户ID")  {constraints(nullable:"false")}  
            column(name: "lov_id", type: "bigint(20)",  remarks: "值集ID,hpfm_fnd_lov.lov_id")  {constraints(nullable:"false")}  
            column(name: "display", type: "varchar(" + 30 * weight + ")",  remarks: "显示名称")  {constraints(nullable:"false")}  
            column(name: "order_seq", type: "int(11)",   defaultValue:"1",   remarks: "排序号")  {constraints(nullable:"false")}  
            column(name: "field_name", type: "varchar(" + 30 * weight + ")",   defaultValue:"1",   remarks: "字段名")  {constraints(nullable:"false")}  
            column(name: "query_field_flag", type: "tinyint(1)",   defaultValue:"1",   remarks: "是否查询字段 ")  {constraints(nullable:"false")}  
            column(name: "table_field_flag", type: "tinyint(1)",   defaultValue:"1",   remarks: "是否表格列 ")  {constraints(nullable:"false")}  
            column(name: "table_field_width", type: "int(11)",   defaultValue:"100",   remarks: "表格列宽度")  {constraints(nullable:"false")}  
            column(name: "enabled_flag", type: "tinyint(1)",   defaultValue:"1",   remarks: "是否启用 ")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁 ")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "创建时间  ")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"0",   remarks: "创建人 ")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "最后更新时间  ")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"0",   remarks: "最后更新人")  {constraints(nullable:"false")}  

        }
   createIndex(tableName: "hpfm_lov_view_line", indexName: "hpfm_lov_view_line_n1") {
            column(name: "view_header_id")
        }

    }
}