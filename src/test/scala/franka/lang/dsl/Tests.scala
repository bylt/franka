package franka
package lang
package dsl

object Tests extends App {

    val Integer = Types.Select.Names ('sdk, 'integer)

    val Bool = Types.Select.Names ('sdk, 'boolean)
    val True = Types.Select.Names ('sdk, 'boolean, 'true)
    val False = Types.Select.Names ('sdk, 'boolean, 'false)

    val Person = Types.Select.Names ('my, 'domain, 'person)
    val Name = Types.Select.Names ('my, 'domain, 'name)

    val File = Types.Select.Names ('os, 'file_system, 'file)
    val String = Types.Select.Names ('sdk, 'string)

    val funcs : Set [(Name, Types.Function)] = Set (
        'or -> Types.Function (Bool, Bool),
        'not -> Types.Function (True, False),
        'name -> Types.Function (Person, Name),
        'name -> Types.Function (File, String),
        'negate -> Types.Function (Integer, Integer),
        'young -> Types.Function (Person, Bool),
        'old -> Types.Function (Person, Bool)
    )

}
