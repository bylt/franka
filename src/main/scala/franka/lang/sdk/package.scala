package franka
package lang

import franka.lang.Types._

package object sdk {

    val booleanType =
        TaggedUnion (Seq (
            'true  -> Literal (Unit (Seq ('franka, 'sdk, 'boolean, 'true))),
            'false -> Literal (Unit (Seq ('franka, 'sdk, 'boolean, 'false)))
        ))

    val integerType =
        Select.Names ('franka, 'sdk, 'integer)

    val decimalType =
        Record (Seq (
            'unscaled_value -> integerType,
            'scale          -> integerType
        ))

    val optionType =
        Lambda (
            'elemType,
            TaggedUnion (Seq (
                'some -> Ident ('elemType),
                'none -> Bottom
            ))
        )

}
