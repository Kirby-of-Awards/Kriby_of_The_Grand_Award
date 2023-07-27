package com.example.guru2_3

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.guuuuruu.R


class LoginActivity : AppCompatActivity() {

    lateinit var dbManager: DBManager
    lateinit var sqliteDB: SQLiteDatabase

    lateinit var id: EditText
    lateinit var password: EditText
    lateinit var btnLogin: Button
    lateinit var login_register: Button

    lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /* 뒤로가기 버튼 클릭 리스너 */
        btnBack = findViewById(R.id.backbtn)
        btnBack.setOnClickListener{
            finish()
        }

        id = findViewById(R.id.login_ID)
        password = findViewById(R.id.login_PW)
        btnLogin = findViewById(R.id.longbtn)
        login_register = findViewById(R.id.login_register)

        dbManager = DBManager(this, "User", null, 1)
        sqliteDB = dbManager.readableDatabase

        /* 로그인 버튼 클릭 이벤트 핸들러 */
        btnLogin.setOnClickListener {
            /* id, password 넘겨서 DB에서 확인하는 로직 */
            var strId: String = id.text.toString()
            var strPassword: String = password.text.toString()
            var name: String = ""

            var cursor: Cursor = sqliteDB.rawQuery("select * from User where id = '$strId' and password = '$strPassword';", null)

            while (cursor.moveToNext()) {
                var validId: String = cursor.getString(0)
                name = cursor.getString(1)
                var validPassword: String = cursor.getString(2)
            }

            if (cursor.count == 1) {
                Toast.makeText(this@LoginActivity, "${name}님 환영합니다.", Toast.LENGTH_SHORT).show()

                /* 아이디 넘기는 작업 추가 */
                val userInfo = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
                val editor = userInfo.edit()
                editor.putString("userId", strId)
                editor.putString("userName", name)
                editor.apply()

                /* 로그인 완료되면 레시피_자료검색으로 넘어감 */
                //val intent = Intent(this, Search::class.java)
                //startActivity(intent)

                cursor.close()
                sqliteDB.close()

                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                finish()

            } else {
                /* 아이디나 비밀번호를 입력하지 않았을 경우 / 아이디 또는 비밀번호가 틀렸을 경우 */
                if (strId.isEmpty() || strPassword.isEmpty()) {
                    Toast.makeText(this@LoginActivity, "아이디와 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@LoginActivity, "아이디 또는 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                }
            }


        }


//        login_register.setOnClickListener {
//            /* 회원가입 버튼을 누르면 Search 액티비티로 이동 */
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
//        }
    }

}