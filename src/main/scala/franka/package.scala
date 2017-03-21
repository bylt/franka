import scala.language.implicitConversions

package object franka {

    implicit def symbolToName (symbol : Symbol) : Name =
        Name.parse.snake_case (symbol.name)

}
