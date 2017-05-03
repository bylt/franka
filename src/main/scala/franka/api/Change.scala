package franka
package api

sealed trait Change

object Change {

    case class BulkChange (changes : Seq [Change]) extends Change

    case class AddUnitType (path : Seq [Name]) extends Change

    case class AddSumType (path : Seq [Name], subTypes : Seq [Def]) extends Change

    case class AddFunction (name : Name, signature : Seq [Def]) extends Change

}