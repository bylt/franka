package franka
package lang

import franka.lang.TypeAst._

package object sdk {

    val booleanType =
        TaggedUnionType (
            'true  -> Literal (???),
            'false -> Literal (???)
        )

    val integerType =
        IndexedUnionType {
            case Name (Vector (symbol)) if ??? =>
                Literal (???)
        }

    val decimalType =
        RecordType (
            'unscaled_value -> Literal (integerType),
            'scale          -> Literal (integerType)
        )

    val optionType =
        Lambda (
            'elemType,
            Literal (TaggedUnionType (
                'some -> Ident ('elemType),
                'none -> Literal (BottomType)
            ))
        )

}
