package franka
package lang

import franka.lang.Types._

package object sdk {

    val booleanType =
        taggedUnion (
            'true  -> unit,
            'false -> unit
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
