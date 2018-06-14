package com.wzc.masterblogdemo.dagger2.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wzc.masterblogdemo.dagger2.data.model.User;
import com.wzc.masterblogdemo.dagger2.di.ApplicationContext;
import com.wzc.masterblogdemo.dagger2.di.DatabaseInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author wzc
 * @date 2018/6/2
 */
@Singleton // 确保全局中这个类为单例
public class DbHelper extends SQLiteOpenHelper {
    // USER TABLE
    public static final String USER_TABLE_NAME = "users";
    public static final String USER_COLUMN_USER_ID = "id";
    public static final String USER_COLUMN_USER_NAME = "usr_name";
    public static final String USER_COLUMN_USER_ADDRESS = "usr_address";
    public static final String USER_COLUMN_USER_CREATED_AT = "created_at";
    public static final String USER_COLUMN_USER_UPDATED_AT = "updated_at";

    @Inject // 当构造这个类时, 指挥 Dagger 去收集所有的参数依赖
    public DbHelper(@ApplicationContext Context context, // 便于 DbHelper 从 dagger 的依赖图中获取 application 的 context 对象
                    @DatabaseInfo String name,
                    @DatabaseInfo Integer version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        tableCreateStatements(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(db);
    }

    private void tableCreateStatements(SQLiteDatabase db) {
        try {
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + USER_TABLE_NAME + "("
                            + USER_COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + USER_COLUMN_USER_NAME + " VARCHAR(20), "
                            + USER_COLUMN_USER_ADDRESS + " VARCHAR(50), "
                            + USER_COLUMN_USER_CREATED_AT + " VARCHAR(10) DEFAULT " + getCurrentTimeStamp() + ", "
                            + USER_COLUMN_USER_UPDATED_AT + " VARCHAR(10) DEFAULT " + getCurrentTimeStamp() + ")"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected User getUser(Long userId) throws Resources.NotFoundException, NullPointerException {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = new String[]{
                USER_COLUMN_USER_ID,
                USER_COLUMN_USER_NAME,
                USER_COLUMN_USER_ADDRESS,
                USER_COLUMN_USER_CREATED_AT,
                USER_COLUMN_USER_UPDATED_AT
        };
        String selection = USER_COLUMN_USER_ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(userId)};
        Cursor cursor = null;
        try {
            cursor = db.query(USER_TABLE_NAME, columns, selection, selectionArgs, null, null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                User user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(USER_COLUMN_USER_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_NAME)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_ADDRESS)));
                user.setCreatedAt(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_CREATED_AT)));
                user.setUpdatedAt(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_UPDATED_AT)));
                return user;
            } else {
                throw new Resources.NotFoundException("User with id " + userId + " does not exists");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }


    }

    protected Long insertUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COLUMN_USER_ID, user.getId());
        contentValues.put(USER_COLUMN_USER_NAME, user.getName());
        contentValues.put(USER_COLUMN_USER_ADDRESS, user.getAddress());
        return db.insert(USER_TABLE_NAME, null, contentValues);
    }

    private String getCurrentTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
}
