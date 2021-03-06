package com.arkapp.sortizydemo.utils

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.showSnack(msg: String?) {
    try {
        Snackbar.make(
            this,
            msg!!,
            Snackbar.LENGTH_SHORT
        ).show()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun View.showSnackLong(msg: String?) {
    try {
        Snackbar.make(
            this,
            msg!!,
            Snackbar.LENGTH_LONG
        ).show()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun View.showIndefiniteSnack(msg: String?): Snackbar? {
    try {
        return Snackbar.make(
            this,
            msg!!,
            Snackbar.LENGTH_INDEFINITE
        ).also { it.show() }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

fun Context?.makeCall(phoneNo: String?) {
    if (!phoneNo.isNullOrEmpty()) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNo, null))
        this!!.startActivity(intent)
    } else
        this!!.toast("Oops! Phone no. is invalid")
}

fun getScreenWidthInDPs(context: Context): Int {

    val dm = DisplayMetrics()
    val windowManager: WindowManager =
        context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    windowManager.defaultDisplay.getMetrics(dm)
    return (dm.widthPixels / dm.density).roundToInt()
}

fun getScreenHeightInDPs(context: Context): Int {

    val dm = DisplayMetrics()
    val windowManager: WindowManager =
        context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    windowManager.defaultDisplay.getMetrics(dm)
    return (dm.heightPixels / dm.density).roundToInt()
}

fun convertDpToPixel(dpValue: Float, context: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dpValue,
        context.resources.displayMetrics
    )
}

fun Window.disableTouch() {
    setFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
    )
}

fun Window.enableTouch() {
    clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun Window.setTransparentEdges() {
    decorView.setBackgroundResource(android.R.color.transparent)
}

fun Window.setFullWidth() {
    setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
}

fun Window.setFullScreen() {
    setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
}

fun BottomSheetDialog.expandBottomDialogOnOpen() {

    //used to set the height to fullscreen
    setOnShowListener { dialogInterface ->
        val d = dialogInterface as BottomSheetDialog
        val bottomSheetInternal: View =
            d.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
        BottomSheetBehavior.from(bottomSheetInternal).state = BottomSheetBehavior.STATE_EXPANDED
    }
}

fun Context.dpFromPx(px: Float) = px / resources.displayMetrics.density

fun Context.pxFromDp(dp: Float) = dp * resources.displayMetrics.density

fun RecyclerView.initVerticalAdapter(
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
    hasFixedSize: Boolean
) {
    val llm = LinearLayoutManager(this.context)
    this.setHasFixedSize(hasFixedSize)
    this.setItemViewCacheSize(10)
    this.layoutManager = llm
    adapter.setHasStableIds(true)
    this.adapter = adapter
}

fun Context.showAlertDialog(
    title: String,
    message: String,
    positiveTxt: String?,
    negativeTxt: String,
    positiveListener: DialogInterface.OnClickListener?
) {

    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(message)
        .setNegativeButton(negativeTxt) { dialog, _ -> dialog.dismiss() }
        .apply {
            if (!positiveTxt.isNullOrEmpty()) {
                setPositiveButton(positiveTxt, positiveListener)
            }
            show()
        }
}

fun Date.getFormattedDate(): String {
    val sdf = SimpleDateFormat("dd MMM-yyyy", Locale.getDefault())
    val date = this
    return sdf.format(date)
}

fun Date.getFormattedDateTime(): String {
    val sdf = SimpleDateFormat("dd MMM-yyyy hh:mm a", Locale.getDefault())
    val date = this
    return sdf.format(date)
}

fun Date.getFormattedMonth(): String {
    val sdf = SimpleDateFormat("MMM", Locale.getDefault())
    val date = this
    return sdf.format(date).toUpperCase()
}

fun Date.getFormattedDay(): String {
    val sdf = SimpleDateFormat("dd", Locale.getDefault())
    val date = this
    return sdf.format(date)
}

fun Date.getFormattedTime(): String {
    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val date = this
    return sdf.format(date)
}


fun Date.getDaysLeft(): Int {
    val cal = Calendar.getInstance()
    val currentCal = Calendar.getInstance()

    cal.time = this

    val diff: Long = cal.timeInMillis - currentCal.timeInMillis

    return if (diff > 0) {
        val dayCount = diff.toFloat() / (24 * 60 * 60 * 1000)
        dayCount.toInt()
    } else 0
}

var LAST_CLICK_TIME: Long = 0


fun isDoubleClicked(minimumClickTimeInMilli: Long): Boolean {
    return if (getCurrentTimestamp() - LAST_CLICK_TIME < minimumClickTimeInMilli) {
        true
    } else {
        LAST_CLICK_TIME = getCurrentTimestamp()
        false
    }
}

fun getCurrentTimestamp() = System.currentTimeMillis()

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun TextInputEditText.value() = text.toString()


