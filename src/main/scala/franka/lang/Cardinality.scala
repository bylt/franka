package franka.lang

sealed trait Cardinality

object Cardinality {

    case class Finite (value : BigInt) extends Cardinality
    case class Infinite (aleph : Int) extends Cardinality

}
