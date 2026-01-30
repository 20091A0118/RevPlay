package com.revplay.dao;

import com.revplay.model.ArtistAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FakeArtistDaoTest {

    private FakeArtistDao dao;

    @BeforeEach
    public void setup() {
        dao = new FakeArtistDao();
    }

    @Test
    public void testRegisterArtist() {
        ArtistAccount artist = new ArtistAccount();
        artist.setEmail("artist@test.com");
        artist.setPasswordHash("pass");
        artist.setStageName("Artist One");

        boolean result = dao.registerArtist(artist);
        assertTrue(result);
        assertNotNull(dao.getArtistByEmail("artist@test.com"));
    }

    @Test
    public void testRetrieveCredentials() {
        ArtistAccount artist = new ArtistAccount();
        artist.setEmail("login@test.com");
        artist.setPasswordHash("secret");
        dao.registerArtist(artist);

        ArtistAccount retrieved = dao.getArtistByEmail("login@test.com");
        assertNotNull(retrieved);
        assertEquals("secret", retrieved.getPasswordHash());
    }
}
