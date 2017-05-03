package franka
package api

trait Def

object Def {

    case class Ref (path: Seq [Name]) extends Def

}