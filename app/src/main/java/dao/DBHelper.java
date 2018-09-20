package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "DBprueba";
    private static final int DB_VERSOION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSOION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios(_id integer primary key autoincrement," +
                "nombre text no null, clave text no null)");
        db.execSQL("insert into usuarios(nombre, clave) values('rgt', '456')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public static class Usuarios{
        public static final String TABLE = "usuarios";
        public static final String _ID = "_id";
        public static final String NOMBRE = "nombre";
        public static final String CLAVE = "clave";
        public static final String[] COLUMNAS = new String[] {_ID, NOMBRE, CLAVE};
    }
}
