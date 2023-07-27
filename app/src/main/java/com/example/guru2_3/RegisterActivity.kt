package com.example.guru2_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.guuuuruu.R

class SignUpActivity : AppCompatActivity() {

    lateinit var register_Name: EditText
    lateinit var register_ID: EditText
    lateinit var register_PW: EditText
    lateinit var register_PW_C: EditText
    lateinit var register_btn: Button
    lateinit var btnBack: ImageButton

    val TAG: String = "Register"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        /* 뒤로가기 버튼 클릭 리스너 */
        btnBack = findViewById(R.id.backbtn)
        btnBack.setOnClickListener {
            finish()
        }

        register_Name = findViewById(R.id.register_Name)
        register_ID = findViewById(R.id.register_ID)
        register_PW = findViewById(R.id.register_PW)
        register_PW_C = findViewById(R.id.register_PW_C)
        register_btn = findViewById(R.id.register_btn)

        var dbManager = DBManager(this, "User", null, 1)

        register_btn.setOnClickListener {
            val name = register_Name.text.toString()
            val id = register_ID.text.toString()
            val pw = register_PW.text.toString()
            val pw_re = register_PW_C.text.toString()

            /* 유저가 항목을 다 채우지 않았을 경우 */
            if (name.length == 0 || id.length == 0 || pw.length == 0 || pw_re.length == 0) {
                Toast.makeText(this, "모든 정보를 입력해주세요", Toast.LENGTH_SHORT).show()
            }

            /* 비밀번호 다를 경우 */
            else if (pw != pw_re) {
                Toast.makeText(this, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show()
            }

            /* 유저가 항목을 다 채웠고 비밀번호도 같을 경우 */
            else {

                /* 회원가입 성공 토스트 메세지 띄우기 */
                dbManager.insertUser(id,name, pw)
                Log.d(TAG, "회원정보 삽입")
                Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()

                /* 메인 화면으로 이동 */
                finish()
            }
        }
    }
}