package br.com.breakingnews.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.breakingnews.databinding.ItemArticleBinding
import br.com.breakingnews.domain.entities.BreakingNewsEntity
import com.bumptech.glide.Glide
import javax.inject.Inject

class MainAdapter @Inject constructor(
    private val diffUtil: DiffUtil.ItemCallback<BreakingNewsEntity>
) : ListAdapter<BreakingNewsEntity, MainAdapter.MainVh>(diffUtil) {

    class MainVh(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BreakingNewsEntity) {
            binding.textArticleTitle.text = model.title
            binding.textArticleDescription.text = model.description
            binding.textArticleAuthor.text = model.author

            Glide
                .with(itemView)
                .load(model.urlToImage)
                .centerCrop()
                .into(binding.imageArticle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainVh {
        return MainVh(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainVh, position: Int) {
        holder.bind(currentList[position] as BreakingNewsEntity)
    }
}