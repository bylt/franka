package franka
package lang

object Values extends Ast {

    type Data = Value

    sealed trait Value

    case object NoValue extends Value

    sealed trait UnstructuredValue extends Value
    case class BooleanValue (value : Boolean) extends UnstructuredValue
    case class IntegerValue (value : BigInt) extends UnstructuredValue
    case class StringValue (value : String) extends UnstructuredValue
    case class BytesValue (value : Array [Byte]) extends UnstructuredValue

    case class Tagged (tag : Name, value : Exp) extends Value
    case class Product (values : Seq [Exp]) extends Value

    case class Match (cases : Seq [(Value, Exp)]) extends Value

    case class Object (fields : Map [Name, Exp]) extends Value

}
