package kursivee.com.material

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_forgot.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val sheet = layoutInflater.inflate(R.layout.bottom_sheet_main, null)
            dialog.setContentView(sheet)
            dialog.show()
        }
    }
}
