# Termu — contoh aplikasi Android

Proyek ini adalah scaffold minimal aplikasi Android bernama Termu (Kotlin).

Setup lokal

- Instal Android Studio atau set Android SDK pada PATH.
- Pastikan JDK 17 terpasang.
- Jalankan build dengan Gradle (menggunakan Gradle CLI):

```bash
# dari root repo
gradle assembleDebug
# atau jika Anda punya gradle wrapper
./gradlew assembleDebug
```

Hasil APK: `app/build/outputs/apk/debug/app-debug.apk`

GitHub Actions

Workflow CI `.github/workflows/android-build.yml` akan membangun APK pada setiap push dan mengunggah artifact `Termu-debug-apk`.

Termux

Jika Anda ingin membangun di Termux pada perangkat Android, instal paket `openjdk` dan `gradle` di Termux lalu jalankan perintah `gradle assembleDebug` setelah menginstal Android SDK command-line tools dan mengatur `ANDROID_HOME`.
