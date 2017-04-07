package franka
package lang
package sql

object SqlValueAst extends Ast {

    type Data = SqlValue

    sealed trait SqlValue

}
