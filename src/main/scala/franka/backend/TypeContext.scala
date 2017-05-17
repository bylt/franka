package franka.backend

import franka.Name
import franka.lang.Types

case class TypeContext (
    parent   : Option[TypeContext],
    rootType : Types.Exp,
    typePath : Seq[Types.Exp],
    namePath : Seq[Name]
) {

    val currentType : Types.Exp =
        typePath.last

    def currentTypeName : Name =
        namePath.lastOption match {
            case Some (last) =>
                last
            case None =>
                sys.error ("There is no 'currentTypeName' at the root level.")
        }

    def descend (nextType : Types.Exp, nextName : Name) : TypeContext =
        copy (
            typePath = typePath :+ nextType,
            namePath = namePath :+ nextName
        )

    def nest (nestedRoot : Types.Exp) : TypeContext =
        TypeContext (nestedRoot).copy (
            parent = Some (this)
        )

}

object TypeContext {

    def apply (rootType : Types.Exp) : TypeContext =
        TypeContext (None, rootType, Seq (rootType), Seq.empty)

}