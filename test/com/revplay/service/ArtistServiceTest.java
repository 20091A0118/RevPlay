package com.revplay.service;

import com.revplay.dao.FakeArtistDao;
import com.revplay.model.ArtistAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArtistServiceTest {

    private ArtistServiceImpl artistService;
    private FakeArtistDao fakeDao;

    @BeforeEach
    public void setup() {
        artistService = new ArtistServiceImpl();
        fakeDao = new FakeArtistDao();
        artistService.setArtistDao(fakeDao);
    }

    @Test
    public void testRegisterArtist_Success() {
        ArtistAccount artist = new ArtistAccount();
        artist.setEmail("artist@music.com");
        artist.setPasswordHash("pass");
        artist.setStageName("The Artist");

        boolean result = artistService.registerArtist(artist);
        assertTrue(result, "Artist should be registered");
        assertNotNull(artistService.getArtistByEmail("artist@music.com"));
    }

    @Test
    public void testLogin_Success() {
        ArtistAccount artist = new ArtistAccount();
        artist.setEmail("login@music.com");
        artist.setPasswordHash("secret");
        fakeDao.registerArtist(artist);

        ArtistAccount loggedIn = artistService.login("login@music.com", "secret");
        assertNotNull(loggedIn, "Login should succeed");
    }

    @Test
    public void testLogin_Failure() {
        ArtistAccount artist = new ArtistAccount();
        artist.setEmail("fail@music.com");
        artist.setPasswordHash("secret");
        fakeDao.registerArtist(artist);

        ArtistAccount loggedIn = artistService.login("fail@music.com", "wrongpass");
        assertNull(loggedIn, "Login should fail with wrong password");
    }
}
