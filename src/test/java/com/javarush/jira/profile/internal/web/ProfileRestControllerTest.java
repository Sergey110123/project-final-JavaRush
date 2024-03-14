package com.javarush.jira.profile.internal.web;

import com.javarush.jira.AbstractControllerTest;
import com.javarush.jira.profile.ContactTo;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Set;

import static com.javarush.jira.common.util.JsonUtil.writeValue;
import static com.javarush.jira.login.internal.web.UserTestData.ADMIN_MAIL;
import static com.javarush.jira.login.internal.web.UserTestData.USER_MAIL;
import static com.javarush.jira.profile.internal.web.ProfileTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class ProfileRestControllerTest extends AbstractControllerTest {
    public static final String REST_URL = "/api/profile";
    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());

    }
    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("2"));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateAuth() throws Exception {
        USER_PROFILE_TO.setContacts(Set.of(new ContactTo("skype", "newSkype"),
                new ContactTo("mobile", "+380987654321"),
                new ContactTo("website", "new.com"),
                new ContactTo("github", "newGitHub"),
                new ContactTo("tg", "newTg"),
                new ContactTo("vk", "newVk"),
                new ContactTo("linkedin", "newLinkedin")));
        USER_PROFILE_TO.setMailNotifications(
                Set.of("assigned", "three_days_before_deadline", "two_days_before_deadline",
                        "one_day_before_deadline", "deadline", "overdue"));

        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(USER_PROFILE_TO)))
                .andDo(print())
                .andExpect(status().isNoContent());
        PROFILE_TO_MATCHER.assertMatch(USER_PROFILE_TO, ProfileTestData.getUpdatedTo());
    }
    @Test
    void updateUnAuth() throws Exception {
        USER_PROFILE_TO.setContacts(Set.of(new ContactTo("skype", "newSkype"),
                new ContactTo("mobile", "+380987654321"),
                new ContactTo("website", "new.com"),
                new ContactTo("github", "newGitHub"),
                new ContactTo("tg", "newTg"),
                new ContactTo("vk", "newVk"),
                new ContactTo("linkedin", "newLinkedin")));
        USER_PROFILE_TO.setMailNotifications(
                Set.of("assigned", "three_days_before_deadline", "two_days_before_deadline",
                        "one_day_before_deadline", "deadline", "overdue"));

        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(USER_PROFILE_TO)))
                .andDo(print())
                .andExpect(status().isUnauthorized());

    }
}