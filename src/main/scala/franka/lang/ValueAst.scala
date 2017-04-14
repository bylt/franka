package franka
package lang

class ValueAst [T] extends Ast {

    type Data = Value

    sealed trait Value

    case class SpecificValue (value : T) extends Value

    case class RecordValue (fields : Map [Name, Exp]) extends Value
    case class TaggedValue (tag : Name, value : Exp) extends Value

}
