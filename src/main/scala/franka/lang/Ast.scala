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

    /** A [[Select]] node allows you to select a specific field of a [[TypeAst.ProductType]] value.
      * @param target the target expression
      * @param name the name of the field to select
      */
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

    /** A [[Branch]] node allows you to branch out based on a [[TypeAst.SumType]] value.
      * @param target the target expression, should be of [[TypeAst.SumType]]
      * @param map the tag to expression mapping
      */
    case class Branch (target : Exp, map : (Name, Exp)*) extends Exp

    case class Let (binding : (Name, Exp), in : Exp) extends Exp

    // sdk => sdk.int.add sdk.int.type.1 sdk.int.type.2
    // ModuleType => FunctionType (sdk.int.type, sdk.int.type, sdk.int.type) sdk.int.type sdk.int.type
    Lambda ('sdk, Apply (Select.Names ('sdk, 'int, 'add), Select.Names ('sdk, 'int, 'type, 'one)))

    object sdk {
        object int {
            def add (a : BigInt, b : BigInt) : BigInt = a + b
            def apply (x : String) : BigInt = BigInt (x)
        }
        object bool {
            def `true` : Int = 1
            def `false` : Int = 0
            def and (a : Int, b : Int) : Int = a * b
            def or (a : Int, b : Int) : Int = (a + b).signum
        }
    }

    sdk.int.add (sdk.int ("1"), sdk.int ("2"))

    sdk.bool.and (sdk.bool.`true`, sdk.bool.`false`)

}

