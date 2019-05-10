package script.db

databaseChangeLog(logicalFilePath: 'script/db/iam_menu.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-iam_menu") {
        def weight = 1
        if (helper.isSqlServer()) {
            weight = 2
        } else if (helper.isOracle()) {
            weight = 3
        }
        if (helper.dbType().isSupportSequence()) {
            createSequence(sequenceName: 'iam_menu_s', startValue: "1")
        }
        createTable(tableName: "iam_menu", remarks: "") {
            column(name: "id", type: "bigint(20)", autoIncrement: true, remarks: "") { constraints(primaryKey: true) }
            column(name: "code", type: "varchar(" + 128 * weight + ")", remarks: "菜单的标识") {
                constraints(nullable: "false")
            }
            column(name: "name", type: "varchar(" + 128 * weight + ")", remarks: "菜单名") {
                constraints(nullable: "false")
            }
            column(name: "fd_level", type: "varchar(" + 64 * weight + ")", remarks: "菜单层级") {
                constraints(nullable: "false")
            }
            column(name: "parent_id", type: "bigint(20)", remarks: "父级菜单id") { constraints(nullable: "false") }
            column(name: "type", type: "varchar(" + 64 * weight + ")", remarks: "菜单类型， 包括三种（root, dir, menu）") {
                constraints(nullable: "false")
            }
            column(name: "sort", type: "bigint(20)", remarks: "菜单顺序")
            column(name: "is_default", type: "tinyint(3)", defaultValue: "1", remarks: "是否是默认菜单,0不是默认菜单，1是默认菜单") {
                constraints(nullable: "false")
            }
            column(name: "icon", type: "varchar(" + 128 * weight + ")", remarks: "图标的code值")
            column(name: "route", type: "varchar(" + 128 * weight + ")", remarks: "路由")
            column(name: "h_custom_flag", type: "tinyint(3)", defaultValue: "0", remarks: "客户化菜单标识") {
                constraints(nullable: "false")
            }
            column(name: "h_tenant_id", type: "bigint(20)", defaultValue: "0", remarks: "客户化菜单租户标识") {
                constraints(nullable: "false")
            }
            column(name: "h_level_path", type: "varchar(" + 360 * weight + ")", remarks: "层级路径, RootId/../ParentId/Id")
            column(name: "h_virtual_flag", type: "tinyint(3)", defaultValue: "0", remarks: "是否虚拟菜单, 虚拟菜单不参与左侧菜单栏展示")
            column(name: "object_version_number", type: "bigint(20)", defaultValue: "1", remarks: "")
            column(name: "created_by", type: "bigint(20)", defaultValue: "0", remarks: "")
            column(name: "creation_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP", remarks: "")
            column(name: "last_updated_by", type: "bigint(20)", defaultValue: "0", remarks: "")
            column(name: "last_update_date", type: "datetime", defaultValueComputed: "CURRENT_TIMESTAMP", remarks: "")
            column(name: "h_description", type: "varchar(" + 360 * weight + ")", remarks: "")
            column(name: "h_enabled_flag", type: "tinyint(3)", defaultValue: "1", remarks: "") {
                constraints(nullable: "false")
            }

        }
        createIndex(tableName: "iam_menu", indexName: "iam_menu_n2") {
            column(name: "h_level_path")
        }

        addUniqueConstraint(columnNames: "code,h_tenant_id,fd_level,type,h_custom_flag", tableName: "iam_menu", constraintName: "iam_menu_u1")
    }

    changeSet(author: "bojiangzhou", id: "2019-03-05-iam_menu") {
        addColumn(tableName: 'iam_menu') {
            column(name: 'h_quick_index', type: 'varchar(60)', remarks: '款速索引', afterColumn: 'name')
        }
    }

    changeSet(author: "bojiangzhou", id: "2019-03-26-iam_menu") {
        addColumn(tableName: 'iam_menu') {
            column(name: 'h_controller_type', type: 'varchar(30)', remarks: '控制类型', afterColumn: 'h_virtual_flag')
        }
    }

    changeSet(author: "bojiangzhou", id: "2019-04-11-iam_menu") {
        dropUniqueConstraint(constraintName: "iam_menu_u1", tableName: "iam_menu")
        addUniqueConstraint(columnNames: "code,h_tenant_id,fd_level", tableName: "iam_menu", constraintName: "iam_menu_u1")
    }

    changeSet(author: 'hzero@hand-china.com', id: '2019-04-25-add-column') {
        addColumn(tableName: 'IAM_MENU') {
            column(name: 'CATEGORY', type: 'VARCHAR(64)', remarks: '项目层菜单分类，可以为AGILE，PROGRAM，ANALYTICAL')
        }
    }
}