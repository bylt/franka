package franka

import franka.lang.Types._

object model {

    val typeExp =
        Module (
            'franka ->
                Literal (
                    Module (
                    'name ->
                        Record (
                            'words ->
                                Apply (
                                    Select.Names ('franka, 'sdk, 'seq),
                                    Select.Names ('franka, 'sdk, 'string)
                                )
                        ),
                    'lang ->
                        lang.model.typeExp
                ))
        )

}
