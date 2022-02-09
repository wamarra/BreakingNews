package br.com.breakingnews.presentation.main

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.breakingnews.R
import br.com.breakingnews.databinding.ItemArticleBinding
import br.com.breakingnews.domain.entities.BreakingNewsEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import javax.inject.Inject


class MainAdapter @Inject constructor(
    private val diffUtil: DiffUtil.ItemCallback<BreakingNewsEntity>
) : ListAdapter<BreakingNewsEntity, MainAdapter.MainVh>(diffUtil) {

    class MainVh(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BreakingNewsEntity) {
            binding.textArticleTitle.text = model.title
            binding.textArticleDescription.text = model.description
            binding.textArticleAuthor.text = model.author

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(model.url))

            setOnClickListener(browserIntent)

            Glide
                .with(itemView)
                .load(model.urlToImage)
                .placeholder(R.drawable.ic_placeholder)
                .centerCrop()
                .into(binding.imageArticle)
        }

        private fun setOnClickListener(browserIntent: Intent) {
            binding.textArticleTitle.setOnClickListener {
                startActivity(binding.root.context, browserIntent, null)
            }
            binding.textArticleDescription.setOnClickListener {
                startActivity(binding.root.context, browserIntent, null)
            }
            binding.imageArticle.setOnClickListener {
                startActivity(binding.root.context, browserIntent, null)
            }
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