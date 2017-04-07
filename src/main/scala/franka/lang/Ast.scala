package franka
package lang

/**
  *
  */
abstract class Ast {

    type Data

    sealed trait Exp

    case class Literal (data : Data) extends Exp

    case class Ident (name : Name) extends Exp

    case class Apply (fun : Exp, arg : Exp) extends Exp

    object Apply {

        object Curry {

            def apply (fun : Exp, args : Exp*) : Exp =
                args match {
                    case Seq (arg) =>
                        Apply (fun, arg)
                    case other =>
                        Apply (apply (fun, other.init : _*), other.last)
                }

            def unapplySeq (exp : Exp) : Option [Seq [Exp]] =
                Some (exp) collect {
                    case Apply (Curry (fun, init @ _*), last) =>
                        fun +: (init :+ last)
                    case Apply (fun, last) =>
                        Seq (fun, last)
                }
        }

    }

    case class Lambda (argName : Name, body : Exp) extends Exp

    case class Select (target : Exp, name : Name) extends Exp

    object Select {

        object Names {

            def apply (path : Name*) : Exp =
                path match {
                    case Seq (singleName) =>
                        Ident (singleName)
                    case other =>
                        Select (apply (other.init : _*), other.last)
                }

        }

        object NameSeq {

            def apply (path : Seq [Name]) : Exp =
                path match {
                    case Seq (singleName) =>
                        Ident (singleName)
                    case other =>
                        Select (apply (other.init), other.last)
                }

            def unapply (exp : Exp) : Option [Seq [Name]] =
                Some (exp) collect {
                    case Ident (singleName) =>
                        Seq (singleName)
                    case Select (NameSeq (init), last) =>
                        init :+ last
                }

        }

    }

    case class Let (binding : (Name, Exp), in : Exp) extends Exp

}

