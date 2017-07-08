package franka
package lang

import scala.collection.immutable.ListMap

object Types extends Ast {

    type Data = Type

    sealed trait Type

    case class Unit (path : Seq [Name]) extends Type

    case class TaggedUnion (subTypes : Map[Name, Exp]) extends Type

    case class Union (values: Seq [Exp]) extends Type

    case class Record (fields : Map[Name, Exp]) extends Type

    case class Tuple (elems: Seq [Exp]) extends Type

    case class Function (from : Exp, to : Exp) extends Type

    case class Module (childTypes : Map [Name, Exp]) extends Type

    def unit (path : Name*) : Unit =
        Unit (path)

    def taggedUnion (subTypes : (Name, Exp)*) : TaggedUnion =
        TaggedUnion (ListMap (subTypes : _*))

    def union (values : Exp*) : Union =
        Union (values)

    def record (subTypes : (Name, Exp)*) : Record =
        Record (ListMap (subTypes : _*))

    def tuple (elems : Exp*) : Tuple =
        Tuple (elems)

    def module (subTypes : (Name, Exp)*) : Module =
        Module (ListMap (subTypes : _*))

    case object Bottom extends Type

}
