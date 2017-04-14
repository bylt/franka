package franka.lang.dsl

import franka.lang.LazyAst

case class Sdk (exp : LazyAst.Exp) {

    def bool = Bool (LazyAst.Select (exp, 'bool))

}
