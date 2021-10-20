package com.codembeded.productratecalculator.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.codembeded.productratecalculator.models.BagModel;
import com.codembeded.productratecalculator.models.ProductModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DatabaseName = "calculator.db";
    private static final String FIRST_TABLE_NAME = "Bag";
    private static final String SECOND_TABLE_NAME = "Product";
    private static final String CreateSecondTable = "CREATE TABLE "+ SECOND_TABLE_NAME +"(Product_Id INTEGER PRIMARY KEY AUTOINCREMENT, Product_Name TEXT, Product_Weight DOUBLE, Expense DOUBLE, Bag_Id Integer, FOREIGN KEY (Bag_Id) REFERENCES Bag(Bag_Id))";
    private static final String CreateFirstTable = "CREATE TABLE "+ FIRST_TABLE_NAME +"(Bag_Id INTEGER PRIMARY KEY AUTOINCREMENT, Bag_Name TEXT, Bag_Price DOUBLE)";

    public DatabaseHelper(Context context) {
        super(context, DatabaseName, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CreateFirstTable);
        sqLiteDatabase.execSQL(CreateSecondTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FIRST_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SECOND_TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean InsertBagData(String bag_name,String bag_price){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Bag_Name",bag_name);
        cv.put("Bag_Price",bag_price);
        long result = db.insert(FIRST_TABLE_NAME,null,cv);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean InsertProductData(String product_name,String product_weight, String bag_id, String expense){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Product_Name", product_name);
        cv.put("Product_Weight", product_weight);
        cv.put("Expense", expense);
        cv.put("Bag_Id", bag_id);
        long result = db.insert(SECOND_TABLE_NAME,null,cv);

        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean UpdateBagData(String bag_name,String bag_price, String id){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Bag_Name",bag_name);
        cv.put("Bag_Price",bag_price);
        long result = db.update(FIRST_TABLE_NAME, cv, " Bag_Id= ?", new String[]{id});
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean UpdateProductData(String product_name,String product_weight, String id, String bag_id, String expense){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Product_Name", product_name);
        cv.put("Product_Weight", product_weight);
        cv.put("Expense", expense);
        cv.put("Bag_Id", bag_id);
        long result = db.update(SECOND_TABLE_NAME, cv, "Product_Id = ?", new String[]{id});

        if (result == -1)
            return false;
        else
            return true;
    }

//    public boolean updateData(String name,String fname, String address, String contact, String classes){
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues cv = new ContentValues();
//
//        cv.put("NAME",name);
//        cv.put("FATHER_NAME",fname);
//        cv.put("ADDRESS",address);
//        cv.put("CONTACT",contact);
//        cv.put("CLASS",classes);
//
//        long result = db.update(TableName, cv ,"NAME = ?", new String[]{name});
//        if (result == -1)
//            return false;
//        else
//            return true;
//    }

    //    public Integer DeleteData(String name ){
//        SQLiteDatabase db = getWritableDatabase();
//
//        return db.delete(TableName, "NAME = ?", new String[]{name});
//    }
//
    public ArrayList<ProductModel> getProductList() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ProductModel> productList= new ArrayList<ProductModel>();
        Cursor cursor = db.rawQuery("select * from "+ SECOND_TABLE_NAME , null);
        while(cursor.moveToNext()){
            productList.add(new ProductModel(cursor.getInt(cursor.getColumnIndex("Product_Id")), cursor.getString(cursor.getColumnIndex("Product_Name")), cursor.getDouble(cursor.getColumnIndex("Product_Weight")), cursor.getDouble(cursor.getColumnIndex("Expense"))));
        }
        return productList;
    }
    public ArrayList<ProductModel> getProductListByBag(String bag_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ProductModel> productList= new ArrayList<ProductModel>();
        Cursor cursor = db.rawQuery("select * from "+ SECOND_TABLE_NAME +" where Bag_Id = "+bag_id , null);
        while(cursor.moveToNext()){
            productList.add(new ProductModel(cursor.getInt(cursor.getColumnIndex("Product_Id")), cursor.getString(cursor.getColumnIndex("Product_Name")), cursor.getDouble(cursor.getColumnIndex("Product_Weight")), cursor.getDouble(cursor.getColumnIndex("Expense"))));
        }
        return productList;
    }
    public ArrayList<BagModel> getBagList() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<BagModel> bagList= new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+ FIRST_TABLE_NAME , null);
        while(cursor.moveToNext()){
            bagList.add(new BagModel(cursor.getInt(cursor.getColumnIndex("Bag_Id")), cursor.getString(cursor.getColumnIndex("Bag_Name")), cursor.getDouble(cursor.getColumnIndex("Bag_Price"))));
        }
        return bagList;
    }

}