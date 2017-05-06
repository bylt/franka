package franka
package lang

object Types extends Ast {

    type Data = Type

    sealed trait Type

    case class Module (childTypes : Map [Name, Exp]) extends Type

    case object Bottom extends Type
    case class Unit (path : Seq [Name]) extends Type

    case class TaggedUnion (subTypes : Seq [(Name, Exp)]) extends Type
    case class Union (values: Seq [Exp]) extends Type

    case class Record (fields: Seq [(Name, Exp)]) extends Type
    case class Tuple (elems: Seq [Exp]) extends Type

    case class Function (from : Exp, to : Exp) extends Type

}
