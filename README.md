# 🎵 RevPlay - Console Based Music Streaming Application

RevPlay is a **console-based music streaming application** built using **Java + JDBC + Oracle SQL**.
It allows users to explore a music library, manage playlists, mark favorites, and simulate playback.
Artists can register, manage profiles, upload songs, create albums, and view song statistics.

---

## ✅ Tech Stack
- Java (Console Application)
- JDBC
- Oracle Database (SQL)
- IntelliJ IDEA
- Git & GitHub (Branch-based development)

---

## 📌 Modules Included

### 👤 User Module
- User Registration & Login
- Search songs / albums / artists (based on project scope)
- Favorites
- Playlist Management
- Recently Played / Listening History (based on team implementation)

---

## 🎤 Artist Module (Team Member 4)

**Branch:** `feature-artist`

### ✅ Features Implemented
- Artist Registration
- Artist Login
- Artist Profile Management
    - Update Bio
    - Update Genre
    - Update Social Media Links
- Album Management
    - Create Album
    - View My Albums
    - Update Album
    - Delete Album *(blocked if songs exist in that album)*
- Song Management
    - Upload Song *(auto creates song_stats entry)*
    - View My Songs
    - Update Song
    - Delete Song *(deletes song_stats first)*
- View Song Statistics
    - Play Count
    - Favorites Count

---

## 🗄️ Database Tables

### `artists`
Stores artist registration and profile details.

### `albums`
Stores albums created by artists.

### `songs`
Stores songs uploaded by artists (can belong to albums).

### `song_stats`
Stores stats for each song:
- play_count
- favorite_count

---

## ⚙️ Setup Instructions

### ✅ 1) Clone Repository
```bash
git clone <repo_url>
cd Rev_Project
