package franka.backend
package scala_lang

import scala.collection.immutable.Seq
import scala.meta._
import franka.lang.Types._

class ScalaTypeMapper {

    def typeDecl (typeContext : TypeContext) : Seq[Stat] = {
        typeContext.currentType match {
            case Literal (Module (subTypes)) =>
                val subTrees =
                    for ((subName, subType) <- subTypes) yield {
                        typeDecl (typeContext.descend (subType, subName))
                    }
                if (typeContext.namePath.isEmpty) {
                    subTrees.flatten.toList
                } else {
                    Seq (q"package ${Term.Name (typeContext.currentTypeName.snake_case)} { ..${subTrees.flatten.toList} }")
                }
            case Literal (Record (fields)) =>
                val className =
                    Type.Name (typeContext.currentTypeName.TitleCase)
                val args =
                    for ((fieldName, fieldType) <- fields) yield {
                        param"${Term.Name (fieldName.camelCase)} : ${typeRef (typeContext, fieldType)}"
                    }
                Seq (
                    q"""
                    case class $className (
                        ..${args.toList}
                    )
                    """
                )
            case Literal (TaggedUnion (subTypes)) =>
                val typeName =
                    typeContext.currentTypeName.TitleCase
                val subTypeDecls =
                    for ((subTypeName, subType) <- subTypes) yield {
                        typeDecl (typeContext.descend (subType, subTypeName))
                    }
                Seq (
                    q"""
                    sealed trait ${Type.Name (typeName)}
                    """,
                    q"""
                    object ${Term.Name (typeName)} {
                        ..${subTypeDecls.flatten.toList}
                    }
                    """
                )
            /*
        case Lambda.Curry (args, body) =>
            val className =
                Type.Name (typeContext.currentTypeName.TitleCase)
            val typeVars =
                for (arg <- args) yield {
                    val typeName =
                        Type.Name (arg.TitleCase)
                    q"type $typeName"
                }
            val members =
                typeDecl (typeContext.nest (body))
            Seq (q"abstract class $className { ..${typeVars.toList}; ..${members} }")*/
            case other =>
                Seq (q"object ${Term.Name (typeContext.currentTypeName.snake_case)}")
        }

    }

    def typeRef (typeContext : TypeContext, tpe : Exp) = {
        t"scala.Predef.String"
    }

}
