package franka
package lang

import franka.lang.TypeAst._

package object sdk {

    val booleanType =
        EnumeratedUnionType (
            'true  -> Literal (UnitType (ValueAst.BooleanValue (true))),
            'false -> Literal (UnitType (ValueAst.BooleanValue (false)))
        )

    val integerType =
        UnboundUnionType {
            case Name (Vector (symbol)) if ??? =>
                Literal (UnitType (ValueAst.DecimalValue (symbol.toInt)))
        }

    val decimalType =
        RecordType (
            'unscaled_value -> Literal (integerType),
            'scale          -> Literal (integerType)
        )

    val optionType =
        Lambda (
            'elemType,
            Literal (EnumeratedUnionType (
                'some -> Ident ('elemType),
                'none -> Literal (BottomType)
            ))
        )

}
