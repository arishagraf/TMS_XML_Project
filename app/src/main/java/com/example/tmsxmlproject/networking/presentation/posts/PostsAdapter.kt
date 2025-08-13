package com.example.tmsxmlproject.networking.presentation.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmsxmlproject.databinding.ItemPostBinding
import com.example.tmsxmlproject.networking.data.Post

class PostsAdapter(
    private val items: List<Post>,
    private val removeAction: (String) -> Unit,
    private val editAction: (Post) -> Unit,
) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {
    private var itemList: List<Post> = items

    class PostsViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            item: Post,
            removeAction: (String) -> Unit,
            editAction: (Post) -> Unit,
        ) {
            binding.itemTitle.text = item.title
            binding.itemDescription.text = item.body
            binding.deleteItem.setOnClickListener {
                removeAction(item.id)
            }
            binding.editItem.setOnClickListener {
                binding.editLayout.visibility = View.VISIBLE
            }
            binding.itemTitleEdit.setText(item.title)
            binding.itemDescriptionEdit.setText(item.body)
            binding.saveEditedItem.setOnClickListener {
                editAction(
                    Post(
                        userId = item.userId,
                        id = item.id,
                        body = binding.itemDescriptionEdit.text.toString(),
                        title = binding.itemTitleEdit.text.toString()
                    )
                )
                binding.editLayout.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = ItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PostsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.onBind(itemList[position], removeAction, editAction)

    }

    fun updateList(newItemList: List<Post>) {
        itemList = newItemList
        notifyDataSetChanged()
    }
}