package franka.backend.scala_lang

import scala.meta._
import franka.lang.Types
import franka.lang.Types.Record

class ScalaTypeMapper {

    def mapType (in : Types.Type) = {
        in match {
            case Record (fields) =>
                q"case class Foo ()"
        }

    }

}
