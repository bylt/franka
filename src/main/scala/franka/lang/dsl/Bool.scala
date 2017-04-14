package franka
package lang
package dsl
import franka.lang.LazyAst

case class Bool (exp : LazyAst.Exp) {

    def `true` = Bool (LazyAst.Select (exp, 'true))

    def `false` = Bool (LazyAst.Select (exp, 'false))

    def and (a : Bool, b : Bool) =
        Bool (LazyAst.Apply.Curry (LazyAst.Select (exp, 'and), a.exp, b.exp))

    def ifThenElse [T] (cond : Bool, whenTrue : T, whenFalse : T) (implicit typedExp : TypedExp [T]) : T =
        typedExp.typed (
            LazyAst.Branch (
                cond.exp,
                'true -> typedExp.exp (whenTrue),
                'false -> typedExp.exp (whenFalse)
            )
        )

}

object Bool {

    implicit object BoolTypedExp extends TypedExp [Bool] {
        override def typed (exp : LazyAst.Exp) : Bool = Bool (exp)
        override def exp (t : Bool) : LazyAst.Exp = t.exp
    }

}
