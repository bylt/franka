package franka
package lang

import franka.lang.Types._

package object sdk {

    val booleanType =
        TaggedUnion (
            'true  -> Literal (Unit (Seq ('franka, 'sdk, 'boolean, 'true))),
            'false -> Literal (Unit (Seq ('franka, 'sdk, 'boolean, 'false)))
        )

    val integerType =
        Select.Names ('franka, 'sdk, 'integer)

    val decimalType =
        Record (
            'unscaled_value -> integerType,
            'scale          -> integerType
        )

    val optionType =
        Lambda (
            'elemType,
            Union (Seq (
                Ident ('elemType),
                Bottom
            ))
        )

}
