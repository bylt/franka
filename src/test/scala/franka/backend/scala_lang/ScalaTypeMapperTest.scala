package franka.backend.scala_lang

import franka.backend.TypeContext
import franka.lang.Types._

object ScalaTypeMapperTest extends scala.App {

    val m = new ScalaTypeMapper ()

    val t = m.typeDecl (TypeContext (Literal (franka.model.typeExp)))

    println (t)

}
