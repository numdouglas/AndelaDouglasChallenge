package com.example.user.andeladouglaschallenge;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDetailsTest {
    final UserDetails usr = new UserDetails("Verify");
@Ignore
    @Test
    public void testIfExists(){
        assertTrue(usr!=null);
    }
    @Ignore
    @Test
    public void testContentCreationSuccess() {
        assertNotEquals(usr,new UserDetails("Verify"));



        assertSame(usr.getName(), new UserDetails("Verify").getName());
        assertSame(usr.getTrack(), new UserDetails("Verify").getTrack());
        assertSame(usr.getEmail(), new UserDetails("Verify").getEmail());
        assertSame(usr.getCountry(), new UserDetails("Verify").getCountry());
        assertSame(usr.getPhone_No(), new UserDetails("Verify").getPhone_No());
        assertSame(usr.describeContents(), new UserDetails("Verify").describeContents());

        usr.setCountry("Uzbekistan");
        assertNotEquals(usr.getCountry(),new UserDetails("Verify").getCountry());

        usr.setName("Bolingo");
        assertNotEquals(usr.getName(),new UserDetails("Verify").getName());
    }


    @Ignore
    @Test
    public void writeToParcel() {
    }
}