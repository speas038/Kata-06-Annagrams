package com.speasmachines;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class MainTest {


    @Test
    void name() throws Exception {
        String[] args = null;
        Main.main(args);
    }

    @Test
    void testNoDuplicates()
    {
        Map<String, List<String>> dictionary = new HashMap<>();

        Main.insertWord( dictionary, "test");
        Main.insertWord( dictionary, "test");

        assertThat(dictionary.size(), is(1));
        assertThat(dictionary.keySet().contains("estt"), is(true));

        assertThat(dictionary.get(0), is("test"));

    }

}