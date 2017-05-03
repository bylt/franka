package franka.api

class ApiTest {

    val lib = new MutableLibrary

    val sdk = lib.module ('franka, 'sdk)

    val True = sdk.newUnitType ('true)
    val False = sdk.newUnitType ('false)

    val Bool = sdk.newSumType ('boolean) (True, False)

    val Not = lib.newFunction ('not, Bool, Bool)
    Not.impl (
        True -> False,
        False -> True
    )

    val And = lib.newFunction ('and, Bool, Bool, Bool)
    lib.newFunction ('and, Bool, False, False)
    lib.newFunction ('and, False, Bool, False)
    lib.newFunction ('and, True, True, True)

    val Or = lib.newFunction ('or, Bool, Bool, Bool)
    lib.newFunction ('or, Bool, True, True)
    lib.newFunction ('or, True, Bool, True)
    lib.newFunction ('or, False, False, False)

    val Xor = lib.newFunction ('xor, Bool, Bool, Bool)
    lib.newFunction ('xor, False, True, True)
    lib.newFunction ('xor, True, False, True)
    lib.newFunction ('xor, True, True, False)

    lib.rule { (a, b) =>
        Not (And (a, b)) ->
            Or (Not (a), Not (b))
    }

}
