package franka
package lang

object TypeAst extends Ast {

    type Data = Type

    sealed trait Type

    case class ModuleType (children : Map [Name, Type]) extends Type

    sealed trait ProductType extends Type
    case class TupleType (elems : Vector [Type]) extends ProductType
    case class RecordType (fields : Map [Name, Type]) extends ProductType

    sealed trait SumType extends Type
    case class TaggedUnionType (subTypes : Map [Name, Type]) extends SumType

    case class FunctionType (from : Type, to : Type) extends Type

    case class UnitType (value : ValueAst.Value) extends Type

}
