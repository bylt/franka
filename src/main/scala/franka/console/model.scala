package franka
package console

import franka.lang.Types._

object model {

    val typeExp = module (
        'command -> taggedUnion (
            'scoped -> taggedUnion (
                'on_module_type -> taggedUnion (
                    'add_type -> record (
                        'name -> sel ('franka, 'name)
                    )
                )
            )
        )
    )

}
