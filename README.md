# 🎵 RevPlay - Console Based Music Streaming Application

RevPlay is a **console-based music streaming application** built using **Java + JDBC + Oracle SQL**.
It allows users to access songs, albums, artists, playlists, and simulate playback.
Artists can register, manage profiles, upload songs, create albums, and view song stats.

---

## ✅ Tech Stack
- Java (Console Application)
- JDBC
- Oracle SQL
- IntelliJ IDEA
- Git & GitHub (Branch-based development)

---

## ✅ Project Structure (Packages)
- `com.revplay.controller` → Console menus / user interaction
- `com.revplay.service` → Business logic layer
- `com.revplay.dao` → Database access layer (Interface + Implementation)
- `com.revplay.model` → POJO classes
- `com.revplay.util` → JDBC Utilities / DB Config loader

---

## 🎤 Artist Module (Team Member 4)

**Branch:** `feature-artist`

### ✅ Features Implemented
- Artist Registration
- Artist Login
- Artist Profile Management
  - Bio
  - Genre
  - Social Links:
    - Instagram
    - YouTube
- Album Management
  - Create Album
  - View My Albums
  - Update Album
  - Delete Album *(blocked if songs exist in that album)*
- Song Management
  - Upload Song *(auto inserts into song_stats)*
  - View My Songs
  - Update Song
  - Delete Song *(deletes song_stats first)*
- View Song Statistics
  - Play Count
  - Favorite Count

---

## 🗄️ Database Tables Used (Artist Module)
- `artists`
- `albums`
- `songs`
- `song_stats`

---

## ⚙️ DAO Layer Pattern Used
DAO is implemented using **Interface + Implementation** pattern:

- `IArtistDAO` → `ArtistDAOImpl`
- `IAlbumDAO` → `AlbumDAOImpl`
- `ISongDAO` → `SongDAOImpl`
- `ISongStatsDAO` → `SongStatsDAOImpl`

This improves modularity and follows clean layered architecture.

---

## 🔌 Database Connection Setup
Database connection is handled only in:
✅ `com.revplay.util.JDBCUtil`

DB credentials are stored in:
✅ `db.properties`

### ✅ db.properties format
Create file: `src/db.properties`

```properties
db.url=jdbc:oracle:thin:@HOST:1521/SERVICE_NAME
db.username=YOUR_DB_USERNAME
db.password=YOUR_DB_PASSWORD
