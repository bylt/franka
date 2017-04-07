package franka
package lang

abstract class AstExtractors [A <: Ast] (
    val ast : A,
    val prefix : Name*
) {

    abstract class Nullary (val name : Name) {

        def apply () : ast.Exp =
            ast.Select.NameSeq (prefix :+ name)

        def unapply (exp : ast.Exp) : Boolean =
            exp match {
                case ast.Select.NameSeq (path) if path == (prefix :+ name) =>
                    true
                case _ =>
                    false
            }

    }

    abstract class Unary (val name : Name) {

        def apply (arg1 : ast.Exp) : ast.Exp =
            ast.Apply (
                ast.Select.NameSeq (prefix :+ name),
                arg1
            )

        def unapply (exp : ast.Exp) : Option [ast.Exp] =
            Some (exp) collect {
                case ast.Apply (ast.Select.NameSeq (path), arg1) if path == (prefix :+ name) =>
                    arg1
            }

    }

    abstract class Binary (val name : Name) {

        def apply (arg1 : ast.Exp, arg2 : ast.Exp) : ast.Exp =
            ast.Apply.Curry (
                ast.Select.NameSeq (prefix :+ name),
                arg1, arg2
            )

        def unapply (exp : ast.Exp) : Option [(ast.Exp, ast.Exp)] =
            Some (exp) collect {
                case ast.Apply.Curry (ast.Select.NameSeq (path), arg1, arg2) if path == (prefix :+ name) =>
                    (arg1, arg2)
            }

    }

}
