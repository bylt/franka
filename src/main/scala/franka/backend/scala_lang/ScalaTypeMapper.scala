package franka.backend
package scala_lang

import scala.collection.immutable.Seq
import scala.meta._
import franka.lang.Types._

class ScalaTypeMapper {

    def mapType (typeContext : TypeContext) : Seq[Stat] = {
        typeContext.currentType match {
            case Literal (Module (subTypes)) =>
                val subTrees =
                    for ((subName, subType) <- subTypes) yield {
                        mapType (typeContext.descend (subType, subName))
                    }
                if (typeContext.namePath.isEmpty) {
                    subTrees.flatten.toList
                } else {
                    Seq (q"package ${Term.Name (typeContext.currentTypeName.snake_case)} { ..${subTrees.flatten.toList} }")
                }
            case Literal (Record (fields)) =>
                val className =
                    Type.Name (typeContext.currentTypeName.TitleCase)
                Seq (q"case class $className ()")
            case Literal (TaggedUnion (subTypes)) =>
                val typeName =
                    typeContext.currentTypeName.TitleCase
                Seq (
                    q"""
                    sealed trait ${Type.Name (typeName)}
                    object ${Term.Name (typeName)} {

                    }
                    """
                )
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
                    mapType (typeContext.nest (body))
                Seq (q"abstract class $className { ..${typeVars.toList}; ..${members} }")
            case other =>
                Seq (q"object ${Term.Name (typeContext.currentTypeName.snake_case)}")
        }

    }

}
