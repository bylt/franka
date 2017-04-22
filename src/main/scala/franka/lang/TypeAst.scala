package franka
package lang

object TypeAst extends Ast {

    type Data = Type

    sealed trait Type

    case object Zero extends Type
    case class Unit () extends Type

    case class TaggedSum (subTypes : Map [Name, Exp]) extends Type
    case class IndexedSum (getSubType : Int => Exp) extends Type

    case class TaggedProduct (subTypes : Map [Name, Exp]) extends Type
    case class IndexedProduct (getSubType : Int => Exp) extends Type

    case class Function (from : Exp, to : Exp) extends Type

}
