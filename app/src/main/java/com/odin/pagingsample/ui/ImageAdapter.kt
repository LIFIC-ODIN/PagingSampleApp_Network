package com.odin.pagingsample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigator
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.odin.pagingsample.databinding.ItemImageBinding
import com.odin.pagingsample.model.SearchDocuments
import com.odin.pagingsample.util.DateConverter

class ImageAdapter(
    private val clickListener: (ImageListItem, Navigator.Extras) -> Unit
) : PagingDataAdapter<SearchDocuments, ImageAdapter.ImageViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageData = getItem(position)
        imageData?.let {
            val imageListItem = ImageListItem(
                siteName = imageData.display_sitename,
                thumbnailUrl = imageData.thumbnail_url,
                time = DateConverter.getDate(imageData.datetime.time)
            )
            holder.bindTo(imageListItem, clickListener)
        }
    }

    class ImageViewHolder(
        private val binding: ItemImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(
            item: ImageListItem,
            clickListener: (ImageListItem, Navigator.Extras) -> Unit
        ) {
            binding.image = item
//            ViewCompat.setTransitionName(binding.root, "${item.id}")
//            itemView.setOnClickListener {
//                val extras = FragmentNavigatorExtras(
//                    binding.root to "${item.id}"
//                )
//                clickListener(item, extras)
//            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<SearchDocuments>() {
            override fun areItemsTheSame(
                oldItem: SearchDocuments,
                newItem: SearchDocuments
            ) = oldItem.image_url == newItem.image_url

            override fun areContentsTheSame(
                oldItem: SearchDocuments,
                newItem: SearchDocuments
            ) = oldItem == newItem
        }
    }

    data class ImageListItem(val siteName: String, val thumbnailUrl: String, val time: String)
}