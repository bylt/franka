package franka.backend.scala_lang

import franka.lang.Types.Record

object ScalaTypeMapperTest extends scala.App {

    val m = new ScalaTypeMapper ()

    val t = m.mapType (Record ())

    println (t)

}
