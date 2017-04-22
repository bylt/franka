package franka
package lang

import franka.lang.TypeAst._

package object sdk {

    val booleanType =
        TaggedSum (Map (
            'true  -> Literal (Unit ()),
            'false -> Literal (Unit ())
        ))

    val integerType =
        IndexedSum {
            index =>
                Literal (Unit ())
        }

    val decimalType =
        TaggedProduct (Map (
            'unscaled_value -> Literal (integerType),
            'scale          -> Literal (integerType)
        ))

    val optionType =
        Lambda (
            'elemType,
            Literal (TaggedSum (Map (
                'some -> Ident ('elemType),
                'none -> Literal (Zero)
            )))
        )

}
