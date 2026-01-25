-- =======================================
-- RevPlay - Artist Module Schema (Updated)
-- =======================================

-- Drop child tables first
BEGIN
EXECUTE IMMEDIATE 'DROP TABLE song_stats CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

BEGIN
EXECUTE IMMEDIATE 'DROP TABLE songs CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

BEGIN
EXECUTE IMMEDIATE 'DROP TABLE albums CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

BEGIN
EXECUTE IMMEDIATE 'DROP TABLE ARTIST_ACCOUNT CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

--  Create ARTIST_ACCOUNT
CREATE TABLE ARTIST_ACCOUNT (
                                artist_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                stage_name VARCHAR2(100) NOT NULL,
                                email VARCHAR2(100) NOT NULL UNIQUE,
                                password_hash VARCHAR2(255) NOT NULL,
                                bio VARCHAR2(500),
                                genre VARCHAR2(100),
                                instagram_link VARCHAR2(255),
                                youtube_link VARCHAR2(255),
                                spotify_link VARCHAR2(255),
                                status VARCHAR2(20),
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--  Create ALBUMS
CREATE TABLE albums (
                        album_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        artist_id NUMBER NOT NULL,
                        title VARCHAR2(150) NOT NULL,
                        genre VARCHAR2(100),
                        release_date DATE,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        CONSTRAINT fk_album_artist_account
                            FOREIGN KEY (artist_id)
                                REFERENCES ARTIST_ACCOUNT(artist_id)
);

--  Create SONGS
CREATE TABLE songs (
                       song_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       artist_id NUMBER NOT NULL,
                       album_id NUMBER,
                       title VARCHAR2(150) NOT NULL,
                       genre VARCHAR2(100),
                       duration_sec NUMBER NOT NULL,
                       release_date DATE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       CONSTRAINT fk_song_artist_account
                           FOREIGN KEY (artist_id)
                               REFERENCES ARTIST_ACCOUNT(artist_id),
                       CONSTRAINT fk_song_album
                           FOREIGN KEY (album_id)
                               REFERENCES albums(album_id)
);

--  Create SONG_STATS
CREATE TABLE song_stats (
                            song_id NUMBER PRIMARY KEY,
                            play_count NUMBER DEFAULT 0,
                            favorite_count NUMBER DEFAULT 0,
                            CONSTRAINT fk_song_stats_song
                                FOREIGN KEY (song_id)
                                    REFERENCES songs(song_id)
);
