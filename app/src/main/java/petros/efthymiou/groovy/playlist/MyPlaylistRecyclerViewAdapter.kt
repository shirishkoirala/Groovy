package petros.efthymiou.groovy.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.databinding.PlaylistItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyPlaylistRecyclerViewAdapter(
    private val values: List<Playlist>
) : RecyclerView.Adapter<MyPlaylistRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            PlaylistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.playlistName.text = item.name
        holder.playlistCategory.text = item.category
        holder.playlistImage.setImageResource(item.image)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: PlaylistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val playlistName: TextView = binding.playlistName
        val playlistCategory: TextView = binding.playlistCategory
        val playlistImage: ImageView = binding.playlistImage
    }

}