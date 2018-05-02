package lesson2;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses({
        GroupingTest.class
})
@RunWith(Categories.class)
@Categories.IncludeCategory(TestCategories.PositiveTests.class)
public class PositiveSuite {

}
