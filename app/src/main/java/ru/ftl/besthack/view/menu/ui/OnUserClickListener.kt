package ru.ftl.besthack.view.menu.ui

import ru.ftl.besthack.data.auth.UserModel

interface OnUserClickListener {
    fun onUserClick(userModel: UserModel)
}