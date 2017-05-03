package franka.api

case class LibraryState (
    changes : Seq [Change]
) {

    def append (change : Change) : LibraryState =
        copy (changes = changes :+ change)

}

object LibraryState {

    val empty = LibraryState (Seq.empty)

}
