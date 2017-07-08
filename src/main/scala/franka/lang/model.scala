package franka
package lang

import franka.lang.Types._

object model {

    val typeExp =
        module (
            'ast ->
                lam ('data) {
                    module (
                        'exp ->
                            Literal (
                                taggedUnion (
                                'literal -> record (
                                    'data -> 'data
                                ),
                                'ident ->
                                    record (
                                        'name ->
                                            sel ('franka, 'name)
                                    ),
                                'app ->
                                    record (
                                        'fun ->
                                            'exp,
                                        'arg ->
                                            'exp
                                    ),
                                'lambda ->
                                    record (
                                        'arg_name ->
                                            sel ('franka, 'name),
                                        'body ->
                                            'exp
                                    )
                                )
                            )
                    )
                },
            'types ->
                app (
                    sel ('franka, 'lang, 'ast),
                    taggedUnion (
                        'bottom ->
                            record (),
                        'unit ->
                            record (
                                'path ->
                                    app (
                                        sel ('franka, 'sdk, 'seq),
                                        sel ('franka, 'name)
                                    )
                            ),
                        'tagged_union ->
                            record (
                                'sub_types ->
                                    app (
                                        sel ('franka, 'sdk, 'seq),
                                        tuple (
                                            sel ('franka, 'name),
                                            'exp
                                        )
                                    )
                            ),
                        'union ->
                            record (
                                'values ->
                                    app (
                                        sel ('franka, 'sdk, 'seq),
                                        'exp
                                    )
                            ),
                        'record ->
                            record (
                                'fields ->
                                    app (
                                        sel ('franka, 'sdk, 'seq),
                                        tuple (
                                            sel ('franka, 'name),
                                            'exp
                                        )
                                    )
                            ),
                        'tuple ->
                            record (
                                'elems ->
                                    app (
                                        sel ('franka, 'sdk, 'seq),
                                        'exp
                                    )
                            ),
                        'function ->
                            record (
                                'from ->
                                    'exp,
                                'to ->
                                    'exp
                            ),
                        'module ->
                            record (
                                'child_types ->
                                    app (
                                        sel ('franka, 'sdk, 'map),
                                        sel ('franka, 'name),
                                        'exp
                                    )
                            )
                    )
                ),
            'values ->
                app (
                    sel ('franka, 'lang, 'ast),
                    Bottom
                )
        )

}
