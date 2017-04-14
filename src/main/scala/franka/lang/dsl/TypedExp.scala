package franka.lang.dsl

import franka.lang.LazyAst

trait TypedExp [T] {

    def typed (exp : LazyAst.Exp) : T

    def exp (t : T) : LazyAst.Exp

}
