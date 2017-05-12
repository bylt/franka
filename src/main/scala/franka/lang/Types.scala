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

    object TaggedUnion {
        def apply (subTypes : (Name, Exp)*) : TaggedUnion =
            TaggedUnion (ListMap (subTypes : _*))
    }

    object Record {
        def apply (subTypes : (Name, Exp)*) : Record =
            Record (ListMap (subTypes : _*))
    }

    object Module {
        def apply (subTypes : (Name, Exp)*) : Module =
            Module (ListMap (subTypes : _*))
    }

    case object Bottom extends Type

}
