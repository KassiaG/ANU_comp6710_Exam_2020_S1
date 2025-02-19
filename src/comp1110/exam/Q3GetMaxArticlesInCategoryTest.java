package comp1110.exam;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * COMP1110 Exam, Question 3.2
 */
public class Q3GetMaxArticlesInCategoryTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(500);

    public void testGetMaxArticlesInCategory(){
        Integer[] articleIds = new Integer[]{31353, 8091, 50223, 72758, 159979, 159973, 25781, 142540};
        String[] names = new String[]{
                "The Hitchhikers Guide to the Galaxy",
                "Douglas Adams",
                "Dirk Gently's Holistic Detective Agency",
                "BBC Radio 4",
                "Arthur Dent",
                "Marvin the Paranoid Android",
                "Robot",
                "Mos Def"};
        String[] categories = new String[]{
                "Book",
                "Person",
                "Book",
                "Radio Station",
                "Fictional Character",
                "Fictional Character",
                "Technology",
                "Person"};
        Q3Wiki wiki = new Q3Wiki();
        // Add articles to the wiki
        for (int i = 0; i < articleIds.length; i++) {
//            wiki.addArticle(articleIds[i], names[i], categories[i]);
        }
        // Test the getMaxArticlesInCategory method
        Assert.assertEquals("Book", wiki.getMaxArticlesInCategory());
    }


}



    // FIXME add one ore more JUnit unit tests that test the getMaxArticlesInCategory() method of the Q3Wiki class

