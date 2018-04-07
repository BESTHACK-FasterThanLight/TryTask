package ru.ftl.besthack.view.menu.ui

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ru.ftl.besthack.R
import ru.ftl.besthack.data.auth.UserModel
import ru.ftl.besthack.di.GlideApp
import ru.ftl.besthack.utils.generateRandomPassword
import java.io.File

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 27.03.18
 */

class UserModelAdapter(private var users: List<UserModel>) : RecyclerView.Adapter<UserModelAdapter.ViewHolder>() {

    private var onUserClickListener: ((user: UserModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]

        holder.title.text = "${user.surname} ${user.name} ${user.middlename} "
        holder.description.text = user.about
        holder.cardView.setOnClickListener { onUserClickListener?.invoke(user) }

        if (user.imageUrl.isNotEmpty()) {
            GlideApp.with(holder.profile)
                    .load(File(user.imageUrl))
                    .circleCrop()
                    .placeholder(R.drawable.ic_action_name)
                    .into(holder.profile)
        } else {
            GlideApp.with(holder.profile)
                    .load("http://api.adorable.io/avatars/285/${generateRandomPassword()}.png")
                    .circleCrop()
                    .placeholder(R.drawable.ic_action_name)
                    .into(holder.profile)
        }
    }

    fun setList(users: List<UserModel>) {
        this.users = users
        notifyDataSetChanged()
    }

    fun setOnUserClickListener(onUserClickListener: (user: UserModel) -> Unit) {
        this.onUserClickListener = onUserClickListener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.textTitle)!!
        val description = itemView.findViewById<TextView>(R.id.textDescription)!!
        val profile = itemView.findViewById<ImageView>(R.id.profile)!!
        val cardView = itemView.findViewById<CardView>(R.id.cardview)
    }
}