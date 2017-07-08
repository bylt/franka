package franka
package lang

import franka.lang.Types._

package object sdk {

    val booleanType =
        taggedUnion (
            'true  -> unit ('franka, 'lang, 'sdk, 'boolean, 'true),
            'false -> unit ('franka, 'lang, 'sdk, 'boolean, 'false)
        )

    val integerType =
        sel ('franka, 'lang, 'sdk, 'integer)

    val decimalType =
        record (
            'unscaled_value -> integerType,
            'scale          -> integerType
        )

    val optionType =
        lam ('elemType) {
            union (
                'elemType,
                Bottom
            )
        }

}
