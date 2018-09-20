package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import modelo.Usuario;

public class UsuarioDAO {
    private DBHelper helper;
    private SQLiteDatabase database;
    public UsuarioDAO(Context context) { helper = new DBHelper(context); }
    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = helper.getWritableDatabase();
        }
        return database;
    }
    private Usuario create(Cursor cursor){
        Usuario u = new Usuario(
                cursor.getInt(cursor.getColumnIndex(DBHelper.Usuarios._ID)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Usuarios.NOMBRE)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Usuarios.CLAVE))
        );
        return u;
    }
    public List<Usuario> listar(){
        Cursor cursor = getDatabase().query(DBHelper.Usuarios.TABLE,DBHelper.Usuarios.COLUMNAS, null, null, null, null, null);
        List<Usuario> lista = new ArrayList<Usuario>();
        while(cursor.moveToNext()){
            Usuario mo = create(cursor);
            lista.add(mo);
        }
        return  lista;
    }
    public long edit(Usuario u){
        ContentValues val = new ContentValues();
        val.put(DBHelper.Usuarios.NOMBRE, u.getNombre());
        val.put(DBHelper.Usuarios.CLAVE, u.getClave());
        if(u.get_id() != null){
            return database.update(DBHelper.Usuarios.TABLE, val, "_id = ?", new String[]{u.get_id().toString()});
        }
        return getDatabase().insert(DBHelper.Usuarios.TABLE, null,val);
    }
    public boolean delete(int id){
        return getDatabase().delete(DBHelper.Usuarios.TABLE, "_id = ?", new String[]{Integer.toString(id)}) > 0;
    }
    public Usuario buscarUsuarioPorID(int id){
        Cursor cursor = getDatabase().query(DBHelper.Usuarios.TABLE,
                DBHelper.Usuarios.COLUMNAS, "_id = ?", new String[]{ Integer.toString(id)}, null, null, null);
        if(cursor.moveToNext()){
            Usuario model = create(cursor);
            cursor.close();
            return model;
        }
        return null;
    }
    public boolean logueoUser(String user, String pass){
        Cursor cursor = getDatabase().query(DBHelper.Usuarios.TABLE,null,
                "NOMBRE = ? AND CLAVE = ?", new String[]{user, pass}, null, null,null);
        if(cursor.moveToNext()){
            return true;
        }
        return false;
    }
    public void cerrarDB(){
        helper.close();
        database = null;
    }
}
