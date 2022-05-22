package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRolenameIsJulliet() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Julliet"));
        Role result = store.findById("1");
        assertThat(result.getUsername(), is("Julliet"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Julliet"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRolenameIsJulliet() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Julliet"));
        store.add(new Role("1", "Romeo"));
        Role result = store.findById("1");
        assertThat(result.getUsername(), is("Julliet"));
    }

    @Test
    public void whenReplaceThenRolenameIsRomeo() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Julliet"));
        store.replace("1", new Role("1", "Romeo"));
        Role result = store.findById("1");
        assertThat(result.getUsername(), is("Romeo"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Julliet"));
        store.replace("10", new Role("10", "Romeo"));
        Role result = store.findById("1");
        assertThat(result.getUsername(), is("Julliet"));
    }

    @Test
    public void whenDeleteUserThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Julliet"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteUserThenRolenameIsJulliet() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Julliet"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getUsername(), is("Julliet"));
    }

}