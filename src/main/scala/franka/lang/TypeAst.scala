package franka
package lang

object TypeAst extends Ast {

    type Data = Type

    sealed trait Type

    case class ModuleType (children : Map [Name, Type]) extends Type

    sealed trait ProductType extends Type
    case class TupleType (elems : Exp*) extends ProductType
    case class RecordType (fields : (Name, Exp)*) extends ProductType

    sealed trait SumType extends Type
    case class UnboundUnionType (caseLookup: PartialFunction [Name, Exp]) extends SumType
    case class EnumeratedUnionType (cases : (Name, Exp)*) extends SumType

    case class FunctionType (from : Exp, to : Exp) extends Type

    case class UnitType (value : ValueAst.Value) extends Type

    case object BottomType extends Type

}
