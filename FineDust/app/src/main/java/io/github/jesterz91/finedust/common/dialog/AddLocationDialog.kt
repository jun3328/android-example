package io.github.jesterz91.finedust.common.dialog

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import io.github.jesterz91.finedust.R
import io.github.jesterz91.finedust.util.delegates.fragmentArgumentDelegate

class AddLocationDialog : DialogFragment() {

    private var yesButton: ((String) -> Unit)? = null

    private var param1: Int by fragmentArgumentDelegate()
    private var param2: String by fragmentArgumentDelegate()

    companion object {
        fun newInstance(param1: Int, param2: String, callBack: (String) -> Unit) : AddLocationDialog {
            return AddLocationDialog().apply {
                this.param1 = param1
                this.param2 = param2
                yesButton = callBack
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireActivity()).inflate(R.layout.fragment_add_location, null, false)
        val cityEditText = view.findViewById<EditText>(R.id.city_edit)

        Log.i("DialogFragment", "param1: $param1, param2: $param2")

        return AlertDialog.Builder(requireActivity())
            .setTitle("위치 추가")
            .setView(view)
            .setPositiveButton("확인") { _, _ ->
                val city = cityEditText.text.toString()
                yesButton?.invoke(city)
            }
            .setNegativeButton("취소", null)
            .create()
    }
}