package com.example.diplom.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.ImageDecoder.ImageInfo
import android.icu.text.IDNA.Info
import android.util.Log

@Suppress("UNREACHABLE_CODE")
public class DBHelper(context: Context):
SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        const val DATABASE_NAME = "AistRipView.db"
        const val DATABASE_VERSION = 1

        private val Categories = "Categories"
        private val id = "id"
        private val name = "name"
        private val isService = "isService"

        private val Clients = "Clients"
        private val idClient = "idClient"
        private val firstName = "firstname"
        private val lastName = "lastName"
        private val middleName = "middleName"
        private val phoneNumber = "phoneNumber"
        private val login = "login"
        private val password = "password"

        private val CheckLineProduct = "Categories"
        private val idCheckLine = "idCheckLine"
        private val idProduct = "idProduct"
        private val idCheck = "idCheck"
        private val date = "date"

        private val Users = "Users"
        private val idRole = "idRole"
        private val loginUser = "loginUser"
        private val passwordUser = "passwordUser"

        private val CheckLineTarif = "CheckLineTarif"
        private val idCheckLineTarif = "idCheckLineTarif"
        private val idTarif = "idTarif"
        private val idCheckInTarif = "idCheckInTarif"

        private val ProductInfo = "ProductInfo"
        private val idProductInfo = "idProductInfo"
        private val info = "info"
        private val idCheckLineProduct = "idCheckLineProduct"
        private val image = "image"

        private val CheckTable = "CheckTable"
        private val idCheckInCheck = "idCheckInCheck"
        private val dateInCheck = "dateInCheck"
        private val idUserInCheck = "idCheckInCheck"
        private val sum = "sum"

        private val TarifPosition = "TarifPosition"
        private val idTarifPosition = "idTarifPosition"
        private val idProductInPosition = "idProductInPosition"
        private val idTarifInPosition = "idTarifInPosition"

        private val Tarif = "Tarif"
        private val idTarifinTarif = "id"
        private val nameTarif = "name"
        private val desctiption = "desctiption"
        private val price = "price"
        private val imageTarif = "imageTarif"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val category = ("CREATE TABLE $Categories(" +
                "id INT NOT NULL identity(1,1)," +
                "    Name NVARCHAR(255) NOT NULL," +
                "    isServise BIT NOT NULL)")
        db?.execSQL(category)

        val client = ("CREATE TABLE Client(" +
                "    Id_Client INT NOT NULL identity(1,1)," +
                "    First_Name NVARCHAR(255) NOT NULL," +
                "    Last_Name NVARCHAR(255) NOT NULL," +
                "    Middle_Name NVARCHAR(255) NOT NULL," +
                "    Phone_Number INT NOT NULL," +
                "    Login NVARCHAR(255) NOT NULL," +
                "    Password NVARCHAR(255) NOT NULL)")
        db?.execSQL(client)
        TODO("Возможно ненулёвое отчество это хуевая идея," +
                "пока оставлю так, но если что то наебнется исправлю")

        val checkLine = ("CREATE TABLE ${CheckLineProduct}(" +
                "    Id_Check_Line_Product INT NOT NULL identity(1,1)," +
                "    Id_Product INT NOT NULL," +
                "    Id_Check INT NOT NULL," +
                "    Date DATETIME NULL)")
        db?.execSQL(category)

        val users = ("CREATE TABLE User(" +
                "    Id_User INT NOT NULL identity(1,1)," +
                "    Id_Role INT NOT NULL," +
                "    Login NVARCHAR(255) NOT NULL," +
                "    Password NVARCHAR(255) NOT NULL)")
        db?.execSQL(users)

        val clTarif = ("CREATE TABLE Check_Line_Tariff(" +
                "    id_Check_Line_Tariff INT NOT NULL identity(1,1)," +
                "    id_Tariff INT NOT NULL," +
                "    id_Check INT NOT NULL)")
        db?.execSQL(clTarif)

        val productInfo = ("CREATE TABLE Product_Info(" +
                "    id_Product_Info INT NOT NULL identity(1,1)," +
                "    Info NVARCHAR(255) NOT NULL," +
                "    Id_Check_Line_Product INT NOT NULL," +
                "    Image VARBINARY(MAX) NULL)")
        db?.execSQL(productInfo)

        val checkTable = ("CREATE TABLE Check(" +
                "    Id_Check INT NOT NULL identity(1,1)," +
                "    Date DATETIME NOT NULL," +
                "    id_User INT NOT NULL," +
                "    Sum DECIMAL(8, 2) NOT NULL)")
        db?.execSQL(checkTable)

        val tarifPos = ("CREATE TABLE Tariff_Position(" +
                "    Id_Tarif_Position INT NOT NULL identity(1,1)," +
                "    Id_Product INT NOT NULL," +
                "    Id_Tariff INT NOT NULL)")
        db?.execSQL(tarifPos)

        val tarif = ("CREATE TABLE Tariff(" +
                "    Id_Tariff INT NOT NULL identity(1,1)," +
                "    Name NVARCHAR(255) NOT NULL," +
                "    Description NVARCHAR(255) NOT NULL," +
                "    Price DECIMAL(8, 2) NOT NULL," +
                "    Image VARBINARY(MAX) NOT NULL);")
        db?.execSQL(tarif)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $Categories")
        onCreate(db)

        db!!.execSQL("DROP TABLE IF EXISTS $Clients")
        onCreate(db)

        db!!.execSQL("DROP TABLE IF EXISTS $CheckLineProduct")
        onCreate(db)

        db!!.execSQL("DROP TABLE IF EXISTS $Users")
        onCreate(db)

        db!!.execSQL("DROP TABLE IF EXISTS $CheckLineTarif")
        onCreate(db)

        db!!.execSQL("DROP TABLE IF EXISTS $ProductInfo")
        onCreate(db)

        db!!.execSQL("DROP TABLE IF EXISTS ${CheckTable}")
        onCreate(db)

        db!!.execSQL("DROP TABLE IF EXISTS ${TarifPosition}")
        onCreate(db)
        TODO("Прописать условие которое будет выбирать таблицы")

        db!!.execSQL("DROP TABLE IF EXISTS ${Tarif}")
        onCreate(db)
    }

    fun getCategory():ArrayList<Categories>{
        val ctgList:ArrayList<Categories> = ArrayList()

        val query = "SELECT * FROM $Categories"
        val db = this.readableDatabase

        val cursor:Cursor?
        cursor = db.rawQuery(query, null)


        var id:Int
        var name:String
        var isService:Boolean
        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(0)
                name = cursor.getString(1)
                isService = (cursor.getInt(2) == 0) //0 or 1!!!
                val ctg = Categories(id = id, name = name, isService = isService)
                ctgList.add(ctg)
                Log.e("class categories", "${ctg}")
            }while (cursor.moveToNext())
        }
        return ctgList
    }

    fun addCategory(ctg:Categories):Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("id", ctg.id)
        values.put("name", ctg.name)
        values.put("isService", ctg.isService)

        val success = db.insert("$Categories", null, values)
        db.close()
        return success
    }

    fun getClient():ArrayList<Clients>{
        val clnList:ArrayList<Clients> = ArrayList()

        val query = "SELECT * FROM ${Clients}"
        val db = this.readableDatabase

        val cursor:Cursor?
        cursor = db.rawQuery(query, null)

        var idClient:Int
        var firstName:String
        var lastName:String
        var middleName:String
        var phoneNumber:String
        var login:String
        var password:String
        if(cursor.moveToFirst()){
            do {
                idClient = cursor.getInt(0)
                firstName = cursor.getString(1)
                lastName = cursor.getString(2)
                middleName = cursor.getString(3)
                phoneNumber = cursor.getString(4)
                login = cursor.getString(5)
                password = cursor.getString(6)
                val cln = Clients(firstName = firstName,
                    lastName = lastName, middleName = middleName,
                    phoneNumber = phoneNumber, login = login,
                    password = password)
                clnList.add(cln)
                Log.e("class client", "${cln}")
            }while (cursor.moveToNext())
        }
        return clnList
    }

    fun addClient(cln:Clients):Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("firstName", cln.firstName)
        values.put("lastName", cln.lastName)
        values.put("middleName", cln.middleName)
        values.put("phoneNumber", cln.phoneNumber)
        values.put("login", cln.login)
        values.put("password", cln.password)

        val success = db.insert("${Clients}", null, values)
        db.close()
        return success
    }

    fun getCheck():ArrayList<CheckLineProduct>{
        val clpList:ArrayList<CheckLineProduct> = ArrayList()

        val query = "SELECT * FROM ${CheckLineProduct}"
        val db = this.readableDatabase

        val cursor: Cursor?
        cursor = db.rawQuery(query, null)


        var id:Int
        var idProduct:Int
        var idCheck:Int
        var date:String
        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(0)
                idProduct = cursor.getInt(1)
                idCheck = cursor.getInt(2)
                date = cursor.getString(3)
                val clp = CheckLineProduct(id = id, idProduct = idProduct,
                    idCheck = idCheck, date = date)
                clpList.add(clp)
                Log.e("class check_line_product", "${clp}")
            }while (cursor.moveToNext())
        }
        return clpList
    }

    fun addCheck(clp:CheckLineProduct):Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("id", clp.id)
        values.put("idProduct", clp.idProduct)
        values.put("idCheck", clp.idCheck)
        values.put("date", clp.date)

        val success = db.insert("${CheckLineProduct}", null, values)
        db.close()
        return success
    }

    fun getUsers():ArrayList<User>{
        val usList:ArrayList<User> = ArrayList()

        val query = "SELECT * FROM ${Users}"
        val db = this.readableDatabase

        val cursor: Cursor?
        cursor = db.rawQuery(query, null)


        var idRole:Int
        var login:String
        var password:String
        if (cursor.moveToFirst()){
            do {
                idRole = cursor.getInt(1)
                login = cursor.getString(2)
                password = cursor.getString(3)
                val us = User(idRole = idRole,
                    login = login, password = password)
                usList.add(us)
                Log.e("class check_line_product", "${us}")
            }while (cursor.moveToNext())
        }
        return usList
    }

    fun addUsers(us:User):Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("idRole", us.idRole)
        values.put("password", us.password)

        val success = db.insert("${Users}", null, values)
        db.close()
        return success
    }

    fun autorisation(log:String, pass:String):Int{
        val query = "SELECT count(*) FROM $Users WHERE $loginUser = '${log}' && " +
                "$passwordUser = '${pass}'"
        val db = this.readableDatabase
        val cursor:Cursor?
        cursor = db.rawQuery(query, null)

        var res:Int = 0
        if(cursor.moveToFirst()){
            do{
                res = cursor.getInt(0)
            } while (cursor.moveToNext())
        }
        return res
    }

    fun getTarif():ArrayList<CheckLineTarif>{
        val tarifList:ArrayList<CheckLineTarif> = ArrayList()

        val query = "SELECT * FROM ${CheckLineTarif}"
        val db = this.readableDatabase

        val cursor: Cursor?
        cursor = db.rawQuery(query, null)


        var id:Int
        var idTarif:Int
        var idCheck:Int
        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(0)
                idTarif = cursor.getInt(1)
                idCheck = cursor.getInt(2)
                val tarif = CheckLineTarif(idCheckLineTarif = id, idTarif = idTarif,
                    idCheck = idCheck)
                tarifList.add(tarif)
                Log.e("class check_line_product", "${tarifList}")
            }while (cursor.moveToNext())
        }
        return tarifList
    }

    fun addTarif(tarif:CheckLineTarif):Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("id", tarif.idCheckLineTarif)
        values.put("idTarif", tarif.idTarif)
        values.put("idCheck", tarif.idCheck)

        val success = db.insert("${CheckLineTarif}", null, values)
        db.close()
        return success
    }

    fun getProductInfo():ArrayList<ProductInfo>{
        val productInfoList:ArrayList<ProductInfo> = ArrayList()

        val query = "SELECT * FROM ${ProductInfo}"
        val db = this.readableDatabase

        val cursor: Cursor?
        cursor = db.rawQuery(query, null)


        var id:Int
        var info:String
        var idCheck:Int
        var image:String
        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(0)
                info = cursor.getString(1)
                idCheck = cursor.getInt(2)
                image = cursor.getString(3)
                val productInfo = ProductInfo(idProductInfo = id, info = info,
                    idCheckLineProductInInfo = idCheck, image = image)
                productInfoList.add(productInfo)
                Log.e("class product_info", "${productInfoList}")
            }while (cursor.moveToNext())
        }
        return productInfoList
    }

    fun addProductInfo(productInfo:ProductInfo):Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("id", productInfo.idProductInfo)
        values.put("info", productInfo.info)
        values.put("idCheck", productInfo.idCheckLineProductInInfo)
        values.put("image", productInfo.image)

        val success = db.insert("${ProductInfo}", null, values)
        db.close()
        return success
    }

    fun getCheckTable():ArrayList<Check>{
        val checkTableList:ArrayList<Check> = ArrayList()

        val query = "SELECT * FROM $CheckTable"
        val db = this.readableDatabase

        val cursor:Cursor?
        cursor = db.rawQuery(query, null)


        var id:Int
        var date:String
        var idUser:Int
        var sum:Float
        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(0)
                date = cursor.getString(1)
                idUser = cursor.getInt(2)
                sum = cursor.getFloat(3)
                val ctg = Check(idCheck = id, date = date,
                    idUser = idUser, sum = sum)
                checkTableList.add(ctg)
                Log.e("class check", "${ctg}")
            }while (cursor.moveToNext())
        }
        return checkTableList
    }

    fun addCheckTable(ctg:Check):Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("id", ctg.idCheck)
        values.put("date", ctg.date)
        values.put("idUser", ctg.idUser)
        values.put("sum", ctg.sum)

        val success = db.insert("$CheckTable", null, values)
        db.close()
        return success
    }

    fun getTPosition():ArrayList<TarifPosition>{
        val ctgList:ArrayList<TarifPosition> = ArrayList()

        val query = "SELECT * FROM $TarifPosition"
        val db = this.readableDatabase

        val cursor:Cursor?
        cursor = db.rawQuery(query, null)


        var id:Int
        var product:Int
        var tarif:Int
        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(0)
                product = cursor.getInt(1)
                tarif = cursor.getInt(2)
                val ctg = TarifPosition(idTarifposition = id,
                    idProductInPosition = product, idTarifInPosition = tarif)
                ctgList.add(ctg)
                Log.e("class tarif position", "${ctg}")
            }while (cursor.moveToNext())
        }
        return ctgList
    }

    fun addTPosotion(ctg:TarifPosition):Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("id", ctg.idTarifposition)
        values.put("name", ctg.idProductInPosition)
        values.put("isService", ctg.idTarifInPosition)

        val success = db.insert("${TarifPosition}", null, values)
        db.close()
        return success
    }

    fun getTarit():ArrayList<Tarif>{
        val ctgList:ArrayList<Tarif> = ArrayList()

        val query = "SELECT * FROM ${Tarif}"
        val db = this.readableDatabase

        val cursor:Cursor?
        cursor = db.rawQuery(query, null)

        var id:String
        var name:String
        var desctiption:String
        var price:Int
        var image:String
        if (cursor.moveToFirst()){
            do {
                id = cursor.getString(0)
                name = cursor.getString(1)
                desctiption = cursor.getString(2)
                price = cursor.getInt(3)
                image = cursor.getString(4)
                val ctg = Tarif(id = id, name = name, desctiption = desctiption,
                    price = price, image = image)
                ctgList.add(ctg)
                Log.e("class tarif position", "${ctg}")
            }while (cursor.moveToNext())
        }
        return ctgList
    }

    fun addTarif(ctg:Tarif):Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("id", ctg.id)
        values.put("name", ctg.name)
        values.put("desctiption", ctg.desctiption)
        values.put("price", ctg.price)
        values.put("image", ctg.image)

        val success = db.insert("${Tarif}", null, values)
        db.close()
        return success
    }
}
