package franka
package lang
package dsl

object Tests extends App {

    val Integer = TypeAst.Select.Names ('sdk, 'integer)

    val Bool = TypeAst.Select.Names ('sdk, 'boolean)
    val True = TypeAst.Select.Names ('sdk, 'boolean, 'true)
    val False = TypeAst.Select.Names ('sdk, 'boolean, 'false)

    val Person = TypeAst.Select.Names ('my, 'domain, 'person)
    val Name = TypeAst.Select.Names ('my, 'domain, 'name)

    val File = TypeAst.Select.Names ('os, 'file_system, 'file)
    val String = TypeAst.Select.Names ('sdk, 'string)

    val funcs : Set [(Name, TypeAst.Function)] = Set (
        'or -> TypeAst.Function (Bool, Bool),
        'not -> TypeAst.Function (True, False),
        'name -> TypeAst.Function (Person, Name),
        'name -> TypeAst.Function (File, String),
        'negate -> TypeAst.Function (Integer, Integer),
        'young -> TypeAst.Function (Person, Bool),
        'old -> TypeAst.Function (Person, Bool)
    )

}
