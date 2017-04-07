package franka
package lang
package sql

object RelationalOps extends AstExtractors (RelationalValueAst, 'franka, 'lang, 'sql, 'relational) {

    object WhereClause extends Binary ('where)

}
