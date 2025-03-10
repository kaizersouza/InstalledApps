package ru.bychinin.installedapps.data.apps

import android.content.pm.PackageManager
import ru.bychinin.installedapps.domain.apps.App
import ru.bychinin.installedapps.domain.apps.App.Companion.mapToApp
import ru.bychinin.installedapps.domain.apps.AppErrorException
import ru.bychinin.installedapps.domain.apps.AppRepository
import ru.bychinin.installedapps.domain.apps.NoAppFoundException

class AppRepositoryImpl(
    private val packageManager: PackageManager,
) : AppRepository {

    override suspend fun getAppsList(): List<App> {
        return try {
            val appsList = packageManager
                .getInstalledApplications(PackageManager.GET_META_DATA)
                .map { it.mapToApp(packageManager) }
            if (appsList.isEmpty()) {
                throw NoAppFoundException()
            } else {
                appsList
            }
        } catch (_: NoAppFoundException) {
            throw NoAppFoundException()
        } catch (_: Exception) {
            throw AppErrorException()
        }
    }
}