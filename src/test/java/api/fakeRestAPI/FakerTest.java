package api.fakeRestAPI;

import org.testng.annotations.Test;

import static api.FakeRestAPI.Activities.*;
import static api.FakeRestAPI.Authors.*;

public class FakerTest {


    @Test(description = "Set of actions which create a activity and deletes it", priority = 1)
    public void performActivities() {
        createActivities();
        // getActivity(id);
        updateActivity(id);
        deleteActivity(id);
        getAllActivities();
    }


    @Test(description = "Set of Author creation, update the author details", priority = 2)
    public void performAuthorActivities() {
        createAuthor();
        getAuthor(authorId);
        getAuthorBooks(authorBookId);
    }
}
