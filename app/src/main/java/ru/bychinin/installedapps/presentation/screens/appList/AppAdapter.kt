package ru.bychinin.installedapps.presentation.screens.appList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.bychinin.installedapps.databinding.ItemAppBinding
import ru.bychinin.installedapps.domain.apps.App

class AppAdapter(
    private val appList: List<App>,
    private val appClickListener: AppClickListener,
) : RecyclerView.Adapter<AppAdapter.AppViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        return AppViewHolder(
            binding = ItemAppBinding.inflate(LayoutInflater.from(parent.context)),
            appClickListener = appClickListener,
        )
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.bind(appList[position])
    }

    override fun getItemCount(): Int = appList.size

    class AppViewHolder(
        private val binding: ItemAppBinding,
        private val appClickListener: AppClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(app: App) {
            with(binding) {
                packageName.text = app.appName
                root.setOnClickListener {
                    appClickListener.onAppClick(app)
                }
            }
        }
    }
}