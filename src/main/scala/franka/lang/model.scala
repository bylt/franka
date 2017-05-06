package franka
package lang

import franka.lang.Types._

object model {

    val typeExp =
        Module (Map (
            'ast ->
                Lambda (
                    'data,
                    Module (Map (
                        'exp ->
                            TaggedUnion (Seq (
                                'literal ->
                                    Record (Seq (
                                        'data ->
                                            Ident ('data)
                                    )),
                                'ident ->
                                    Record (Seq (
                                        'name ->
                                            Select.Names ('franka, 'name)
                                    )),
                                'apply ->
                                    Record (Seq (
                                        'fun ->
                                            Ident ('exp),
                                        'arg ->
                                            Ident ('exp)
                                    )),
                                'lambda ->
                                    Record (Seq (
                                        'arg_name ->
                                            Select.Names ('franka, 'name),
                                        'body ->
                                            Ident ('exp)
                                    ))
                            ))
                    ))
                ),
            'types ->
                Apply (
                    Select.Names ('franka, 'lang, 'ast),
                    TaggedUnion (Seq (
                        'bottom ->
                            Record (Seq.empty),
                        'unit ->
                            Record (Seq (
                                'path ->
                                    Apply (
                                        Select.Names ('franka, 'sdk, 'seq),
                                        Select.Names ('franka, 'name)
                                    )
                            )),
                        'tagged_union ->
                            Record (Seq (
                                'sub_types ->
                                    Apply (
                                        Select.Names ('franka, 'sdk, 'seq),
                                        Tuple (Seq (
                                            Select.Names ('franka, 'name),
                                            Ident ('exp)
                                        ))
                                    )
                            )),
                        'union ->
                            Record (Seq (
                                'values ->
                                    Apply (
                                        Select.Names ('franka, 'sdk, 'seq),
                                        Ident ('exp)
                                    )
                            )),
                        'record ->
                            Record (Seq (
                                'fields ->
                                    Apply (
                                        Select.Names ('franka, 'sdk, 'seq),
                                        Tuple (Seq (
                                            Select.Names ('franka, 'name),
                                            Ident ('exp)
                                        ))
                                    )
                            )),
                        'tuple ->
                            Record (Seq (
                                'elems ->
                                    Apply (
                                        Select.Names ('franka, 'sdk, 'seq),
                                        Ident ('exp)
                                    )
                            )),
                        'function ->
                            Record (Seq (
                                'from ->
                                    Ident ('exp),
                                'to ->
                                    Ident ('exp)
                            )),
                        'module ->
                            Record (Seq (
                                'child_types ->
                                    Apply.Curry (
                                        Select.Names ('franka, 'sdk, 'map),
                                        Select.Names ('franka, 'name),
                                        Ident ('exp)
                                    )
                            ))
                    ))
                ),
            'values ->
                Apply (
                    Select.Names ('franka, 'lang, 'ast),
                    Bottom
                )
        ))

}
