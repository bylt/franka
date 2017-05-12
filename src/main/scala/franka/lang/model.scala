package franka
package lang

import franka.lang.Types._

object model {

    val typeExp =
        Module (
            'ast ->
                Lambda (
                    'data,
                    Module (
                        'exp ->
                            Literal (
                                TaggedUnion (
                                'literal ->
                                    Record (
                                        'data ->
                                            Ident ('data)
                                    ),
                                'ident ->
                                    Record (
                                        'name ->
                                            Select.Names ('franka, 'name)
                                    ),
                                'apply ->
                                    Record (
                                        'fun ->
                                            Ident ('exp),
                                        'arg ->
                                            Ident ('exp)
                                    ),
                                'lambda ->
                                    Record (
                                        'arg_name ->
                                            Select.Names ('franka, 'name),
                                        'body ->
                                            Ident ('exp)
                                    )
                                )
                            )
                    )
                ),
            'types ->
                Apply (
                    Select.Names ('franka, 'lang, 'ast),
                    TaggedUnion (
                        'bottom ->
                            Record (),
                        'unit ->
                            Record (
                                'path ->
                                    Apply (
                                        Select.Names ('franka, 'sdk, 'seq),
                                        Select.Names ('franka, 'name)
                                    )
                            ),
                        'tagged_union ->
                            Record (
                                'sub_types ->
                                    Apply (
                                        Select.Names ('franka, 'sdk, 'seq),
                                        Tuple (Seq (
                                            Select.Names ('franka, 'name),
                                            Ident ('exp)
                                        ))
                                    )
                            ),
                        'union ->
                            Record (
                                'values ->
                                    Apply (
                                        Select.Names ('franka, 'sdk, 'seq),
                                        Ident ('exp)
                                    )
                            ),
                        'record ->
                            Record (
                                'fields ->
                                    Apply (
                                        Select.Names ('franka, 'sdk, 'seq),
                                        Tuple (Seq (
                                            Select.Names ('franka, 'name),
                                            Ident ('exp)
                                        ))
                                    )
                            ),
                        'tuple ->
                            Record (
                                'elems ->
                                    Apply (
                                        Select.Names ('franka, 'sdk, 'seq),
                                        Ident ('exp)
                                    )
                            ),
                        'function ->
                            Record (
                                'from ->
                                    Ident ('exp),
                                'to ->
                                    Ident ('exp)
                            ),
                        'module ->
                            Record (
                                'child_types ->
                                    Apply.Curry (
                                        Select.Names ('franka, 'sdk, 'map),
                                        Select.Names ('franka, 'name),
                                        Ident ('exp)
                                    )
                            )
                    )
                ),
            'values ->
                Apply (
                    Select.Names ('franka, 'lang, 'ast),
                    Bottom
                )
        )

}
