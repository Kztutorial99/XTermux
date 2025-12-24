# Termux Info — Android App

Aplikasi sederhana yang menampilkan informasi Termux, daftar package source/repo, dan panduan setup. Dilengkapi workflow GitHub Actions untuk membuild APK secara otomatis.

## Fitur contoh
- Halaman informasi singkat tentang Termux
- Daftar repository (repo) Termux umum dan perintah untuk menambahkannya
- Panduan dasar setup (update, install paket)
- Tombol copy untuk menyalin perintah (implementasi dasar)

## Cara pakai / Setup di GitHub
1. Buat repository baru (mis: `termux-info-app`) dan push kode ini.
2. Tambahkan secrets GitHub (Repository -> Settings -> Secrets and variables -> Actions):
   - `KEYSTORE_BASE64` (opsional): isi base64 dari file keystore (.jks). Jika kosong, build tetap berjalan tetapi APK tidak akan ditandatangani.
   - `KEYSTORE_PASSWORD` (required jika Anda memberikan keystore)
   - `KEY_ALIAS` (required jika Anda memberikan keystore)
   - `KEY_PASSWORD` (required jika Anda memberikan keystore)
3. Push ke branch `main` (atau ubah workflow sesuai branch).
4. Workflow `.github/workflows/android-build.yml` akan:
   - Men-setup Android SDK
   - Menerjemahkan `KEYSTORE_BASE64` ke file `keystore.jks` bila disediakan
   - Menjalankan `./gradlew assembleRelease`
   - Mengupload APK sebagai artifact action

## Build lokal
- Pastikan Android SDK & JDK terinstall
- Jalankan: `./gradlew assembleRelease`
- Jika ingin menandatangani lokal, buat `keystore.jks` dan set environment variables:
  - `KEYSTORE_PATH`, `KEYSTORE_PASSWORD`, `KEY_ALIAS`, `KEY_PASSWORD`
  - Atau edit `app/build.gradle` signingConfigs sesuai kebutuhan

## Catatan tentang konten Termux
- Perintah dasar:
  - `pkg update && pkg upgrade`
  - `pkg install git python`
  - Tambah repo: `pkg install root-repo`, `pkg install x11-repo`, `pkg install unstable-repo`, `pkg install game-repo`, `pkg install science-repo`
- Selalu verifikasi sumber resmi Termux jika membuat panduan publik.

Jika mau, saya bisa:
- Menambahkan ikon & resources
- Memperluas UI (RecyclerView, screen detail per-paket)
- Menghubungkan release otomatis ke GitHub Releases (publish signed APK)
- Mengubah build untuk target SDK tertentu
