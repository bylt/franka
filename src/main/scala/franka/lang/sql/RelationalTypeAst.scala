package franka
package lang
package sql

object RelationalTypeAst extends Ast {

    type Data = DataType

    sealed trait DataType
    sealed trait ColumnType extends DataType
    case class VarcharType (maxLength : Int) extends ColumnType
    case class NullableType (columnType : ColumnType) extends ColumnType

    case class RowType (columns : (Name, ColumnType)*) extends DataType
    case class JoinedRowType (rowTypes : (Name, RowType)*) extends DataType
    case class TableType (rowType : RowType) extends DataType
    case class SchemaType (tableTypes : (Name, TableType)*) extends DataType

}
