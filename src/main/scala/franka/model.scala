package franka

import franka.lang.Types._

object model {

    val typeExp = module (
        'franka -> module (
            'name -> record (
                'words -> app (
                    sel ('franka, 'sdk, 'seq),
                    sel ('franka, 'sdk, 'string)
                )
            ),
            'lang -> lang.model.typeExp,
            'console -> console.model.typeExp
        )
    )

}
