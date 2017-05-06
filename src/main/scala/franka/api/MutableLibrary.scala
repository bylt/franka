package franka
package api

class MutableLibrary extends LibraryOps {

    private var currentState : LibraryState = LibraryState.empty

    override protected def apply (change : Change) : Unit = {
        val newState = currentState.append (change)
        currentState = newState
    }

    override protected def absolutePath (path : Seq [Name]) : Seq [Name] = path

}
