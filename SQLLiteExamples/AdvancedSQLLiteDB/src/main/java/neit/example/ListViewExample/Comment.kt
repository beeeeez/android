/**
 * Created by Bruce on 10/27/2014.
 */
package neit.example.ListViewExample

class Comment {
    var id: Long = 0
    var comment: String = ""

    // Will be used by the ArrayAdapter in the ListView
    override fun toString(): String {
        return comment
    }
}
