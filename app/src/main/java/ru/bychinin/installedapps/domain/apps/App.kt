package ru.bychinin.installedapps.domain.apps

import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.File
import java.security.MessageDigest

@Parcelize
data class App(
    val appName: String,
    val appFileName: File,
    val packageName: String,
    val appVersion: String,
) : Parcelable {

    @OptIn(ExperimentalStdlibApi::class)
    fun md5(): String {
        return try {
            val md = MessageDigest.getInstance("MD5")
            val digest = md.digest(appFileName.readBytes())
            digest.toHexString()
        } catch (_: Exception) {
            ""
        }
    }

    companion object {
        fun ApplicationInfo.mapToApp(
            packageManager: PackageManager,
        ): App {
            val packageInfo: PackageInfo =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    packageManager.getPackageInfo(
                        packageName,
                        PackageManager.PackageInfoFlags.of(0)
                    )
                } else {
                    packageManager.getPackageInfo(packageName, 0)
                }

            return App(
                appName = loadLabel(packageManager).toString(),
                appFileName = File(sourceDir),
                packageName = packageName.orEmpty(),
                appVersion = packageInfo.versionName.orEmpty(),
            )
        }
    }
}

class NoAppFoundException : Exception()
class AppErrorException : Exception()