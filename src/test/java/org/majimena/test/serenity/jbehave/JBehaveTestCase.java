package org.majimena.test.serenity.jbehave;

import net.serenitybdd.jbehave.SerenityStories;

/**
 * Created by akiba on 6/14/15.
 */
public class JBehaveTestCase extends SerenityStories {

    public JBehaveTestCase() {
        runSerenity().inASingleSession();
    }
}
