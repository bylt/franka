package franka
package lang
package sql

object RelationalValueAst extends Ast {

    type Data = Value

    sealed trait Value
    sealed trait FieldValue extends Value
    case object Null extends FieldValue
    case class StringValue (value : String) extends FieldValue

}
