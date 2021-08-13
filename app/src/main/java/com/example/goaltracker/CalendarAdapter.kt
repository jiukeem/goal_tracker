package com.example.goaltracker

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class CalendarAdapter(private val context: Context): RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {
    // 이 레이아웃에 연결할거에요. 뷰홀더에게 어떤 레이아웃인지 인플레이트해서 넘겨주는 역할. (inflate 란 레이아웃을 코드에서 사용할 수 있도록 객체화해주는 과정이다.)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_date, parent, false)
        return CalendarViewHolder(layout)
    }

    // 전체 갯수는 이래요. 전체 아이템이 몇개나 되는지 계산.
    override fun getItemCount(): Int {
        return 35
    }

    // n번째 아이템 안의 내용은 이렇게 설정할게요. 변수의 속성을 지정해주는 역할(visibility, text 등)
    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.imageView.apply {
            setOnClickListener { showImageSelectionPopUp(this) } // 이미지가 있는 경우는 반대로 없애야하니 viewmodel 로 콜백을 보내서 거기서 처리하자
        }
        if (position >= 28) {
            holder.lineForBottom.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
        }
    }

    // 이 레이아웃 안에서 이 항목들을 조정할거에요. 컴포넌트와 변수를 연결해주는 역할
    class CalendarViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val lineForBottom: TextView = view.findViewById(R.id.txtForBottom)
    }

    private fun showImageSelectionPopUp(imageView: ImageView) {
        AlertDialog.Builder(context)
                .setMessage("insert image?")
                .setPositiveButton("Insert", DialogInterface.OnClickListener { dialog, _ ->
                    dialog.dismiss()
                    imageView.setImageResource(R.mipmap.tracker_icon_done_foreground)
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
                    dialog.dismiss()
                })
                .show()
    }
}