package neit.example.ListViewExample

import android.app.Activity
import android.app.ListActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import java.util.Random


class AdvancedDBActivity : ListActivity() {

    private var datasource: CommentsDataSource? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advanced_db)

        datasource = CommentsDataSource(this)
        datasource!!.open()

        val values = datasource!!.allComments

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        val adapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, values)
        listAdapter = adapter
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    fun onClick(view: View) {
        val adapter = listAdapter as ArrayAdapter<Comment>
        var comment: Comment? = null
        when (view.id) {
            R.id.add -> {
                val comments = arrayOf("Cool", "Very nice", "Hate it")
                val nextInt = Random().nextInt(3)
                // save the new comment to the database
                comment = datasource!!.createComment(comments[nextInt])
                adapter.add(comment)
            }
            R.id.delete -> if (listAdapter.count > 0) {
                comment = listAdapter.getItem(0) as Comment
                datasource!!.deleteComment(comment)
                adapter.remove(comment)
            }
        }
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        datasource!!.open()
        super.onResume()
    }

    override fun onPause() {
        datasource!!.close()
        super.onPause()
    }
}
