package franka
package api

import Change._

trait LibraryOps {

    protected def absolutePath (path : Seq [Name]) : Seq [Name]

    protected def apply (change : Change) : Unit

    def newUnitType (path : Name*) : Def = {
        val p = absolutePath (path)
        apply (AddUnitType (p))
        Def.Ref (p)
    }

    def newSumType (path : Name*) (subTypes : Def*) : Def = {
        val p = absolutePath (path)
        apply (AddSumType (p, subTypes))
        Def.Ref (p)
    }

    def newFunction (name : Name, argType1 : Def, argType2 : Def) = new {
        def apply (arg1 : Def, arg2 : Def) : Def = ???
        def impl (cases : (Def, Def)*) : Unit = ???
    }

    def newFunction (name : Name, argType1 : Def, argType2 : Def, argType3 : Def) = new {
        def apply (arg1 : Def, arg2 : Def, arg3 : Def) : Def = ???
        def impl (cases : (Def, Def, Def)*) : Unit = ???
    }

    def app (name : Name, args : Def*) : Def = ???

    def rule (f : (Def, Def) => (Def, Def)) : Unit = ???

    def module (path : Name*) : LibraryOps =
        new ModuleOps (path)

    class ModuleOps (modulePath : Seq [Name]) extends LibraryOps {

        override protected def absolutePath (path : Seq [Name]) : Seq [Name] =
            modulePath ++ path

        override protected def apply (change: Change) : Unit =
            LibraryOps.this.apply (change)

    }

    def bulk (thunk : LibraryOps => Unit) : Unit = {
        val bulkOps = new BulkOps
        thunk (bulkOps)
        apply (BulkChange (bulkOps.changes))
    }

    class BulkOps extends LibraryOps {

        var changes : Seq [Change] = Seq.empty

        override protected def absolutePath (path : Seq [Name]) : Seq [Name] =
            path

        override protected def apply (change : Change) : Unit = {
            changes = changes :+ change
        }

    }

}
