package franka.lang.dsl

import franka.lang.LazyAst

object Tests extends App {

    val exp1 =
        (sdk : Sdk) =>
            sdk.bool.ifThenElse (
                sdk.bool.and (sdk.bool.`true`, sdk.bool.`false`),
                sdk.bool.`true`,
                sdk.bool.`false`
            )

    println (exp1 (Sdk (LazyAst.Ident ('foo))))
}
