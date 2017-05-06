package franka

import franka.lang.Types._

object model {

    val typeExp =
        Module (Map (
            'franka ->
                Module (Map (
                    'name ->
                        Record (Seq (
                            'words ->
                                Apply (
                                    Select.Names ('franka, 'sdk, 'seq),
                                    Select.Names ('franka, 'sdk, 'string)
                                )
                        )),
                    'lang ->
                        lang.model.typeExp
                ))
        ))

}
