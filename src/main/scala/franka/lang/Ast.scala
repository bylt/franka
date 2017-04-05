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

    case class Lambda (argName : Name, body : Exp) extends Exp

    case class Select (target : Exp, name : Name) extends Exp

    case class Let (binding : (Name, Exp), in : Exp) extends Exp

}

