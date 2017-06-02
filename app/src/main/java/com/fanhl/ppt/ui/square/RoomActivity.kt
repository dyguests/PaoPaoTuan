package com.fanhl.ppt.ui.square

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.fanhl.ppt.R
import com.fanhl.ppt.model.Room
import com.fanhl.ppt.ui.common.BaseActivity
import com.fanhl.ppt.ui.game.GameActivity
import com.fanhl.ppt.util.extensions.subscribeBy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_room.*

/**
 * 房间，一个团在一个房间
 */
class RoomActivity : BaseActivity() {
    val room by lazy { Room(intent.getLongExtra(EXTRA_ID, 0)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        assignViews()
        refreshData()

    }

    private fun assignViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fab.setOnClickListener { GameActivity.launch(this@RoomActivity, room.gameId!!) }
    }

    private fun refreshData() {
        app.client.squareService
                .room(room.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            //etc.

                            fab.setImageResource(R.drawable.ic_play_arrow_white_24dp)
                        }
                )
    }

    companion object {
        fun launch(context: Context, roomId: Long) {
            val intent = Intent(context, RoomActivity::class.java)
            intent.putExtra(EXTRA_ID, roomId)
            context.startActivity(intent)
        }
    }
}
