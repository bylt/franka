package franka
package lang
package sql

object RelationalOps extends AstExtractors (SqlValueAst, 'franka, 'lang, 'sql, 'relational) {

    object WhereClause extends Binary ('where)

}
