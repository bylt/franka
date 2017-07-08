package franka
package lang

import scala.collection.immutable.ListMap

object Types extends Ast {

    type Data = Type

    sealed trait Type

    case object Unit extends Type

    case class TaggedUnion (subTypes : Map[Name, Exp]) extends Type

    case class Union (values: Seq [Exp]) extends Type

    case class Record (fields : Map[Name, Exp]) extends Type

    case class Tuple (elems: Seq [Exp]) extends Type

    case class Function (from : Exp, to : Exp) extends Type

    case class Module (childTypes : Map [Name, Exp]) extends Type

    val unit = Unit

    def taggedUnion (headType : (Name, Exp), tailTypes : (Name, Exp)*) : TaggedUnion =
        TaggedUnion (ListMap (headType +: tailTypes : _*))

    def union (headValue : Exp, tailValues : Exp*) : Union =
        Union (headValue +: tailValues)

    def record (headType : (Name, Exp), tailTypes : (Name, Exp)*) : Record =
        Record (ListMap (headType +: tailTypes : _*))

    def tuple (elem1 : Exp, elem2 : Exp, elems : Exp*) : Tuple =
        Tuple (elem1 +: elem2 +: elems)

    def module (subTypes : (Name, Exp)*) : Module =
        Module (ListMap (subTypes : _*))

    case object Bottom extends Type

}
