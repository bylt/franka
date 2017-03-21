package franka
package lang

object ValueAst extends Ast {

    type Data = Value

    sealed trait Value

    case class BooleanValue (value : Boolean) extends Value
    case class DecimalValue (value : BigDecimal) extends Value
    case class StringValue (value : String) extends Value

    case class ArrayValue (value : Vector [Value]) extends Value
    case class ObjectValue (fields : Map [Name, Value]) extends Value

    case class Lazy (exp : Exp) extends Value

}
