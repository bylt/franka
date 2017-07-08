package franka
package lang

import scala.language.implicitConversions

abstract class Ast {

    type Data

    sealed trait Exp

    case class Literal (data : Data) extends Exp
    implicit def dataToLiteral (data : Data) : Exp = Literal (data)

    case class Ident (name : Name) extends Exp
    implicit def nameToIdent (name : Name) : Ident = Ident (name)
    implicit def symbolToIdent (name : Symbol) : Ident = Ident (name)

    case class Apply (fun : Exp, arg : Exp) extends Exp

    case class Lambda (argName : Name, body : Exp) extends Exp

    /** A [[Select]] node allows you to select a specific field of a [[Types.Record]] value.
      *
      * @param target the target expression
      * @param tag    the name of the field to select
      */
    case class Select (target : Exp, tag : Name) extends Exp

    case class Let (binding : (Name, Exp), in : Exp) extends Exp

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
    def app (fun: Exp, args: Exp*) : Exp =
        Apply.Curry (fun, args : _*)

    object Lambda {

        object Curry {

            def apply (args : Seq[Name], body : Exp) : Exp =
                args match {
                    case Seq (arg) =>
                        Lambda (arg, body)
                    case args =>
                        Lambda (args.head, apply (args.tail, body))
                }

            def unapply (exp : Exp) : Option[(Seq[Name], Exp)] =
                Some (exp) collect {
                    case Lambda (argHead, Curry (argTail, body)) =>
                        (argHead +: argTail, body)
                    case Lambda (arg, body) =>
                        (Seq (arg), body)
                }
        }

    }
    def lam (args : Name*)(body : Exp) : Exp =
        Lambda.Curry (args, body)

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
    def sel (path    : Name*) : Exp =
        Select.Names (path : _*)

}

