package franka
package lang

import franka.lang.Types._

object model {

    val typeExp =
        module (
            'ast ->
                lam ('data) {
                    taggedUnion (
                        'literal -> record (
                            'data -> 'data
                        ),
                        'ident -> record (
                            'name -> sel ('franka, 'name)
                        ),
                        'app -> record (
                            'fun -> 'exp,
                            'arg -> 'exp
                        ),
                        'lambda -> record (
                            'arg_name -> sel ('franka, 'name),
                            'body -> 'exp
                        )
                    )
                },
            'types -> module (
                'exp -> app (
                    sel ('franka, 'lang, 'ast),
                    sel ('franka, 'lang, 'types, 'type)
                ),
                'type -> taggedUnion (
                    'bottom -> unit,
                    'unit -> unit,
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
