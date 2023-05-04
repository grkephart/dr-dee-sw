/**
 * 
 */
package org.drdeesw.commons.dto.queries;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * @author gary_kephart
 *
 */
public class JpqlQueryTest
{

  static class Something
  {

  }


  static class Track
  {

  }


  static class Suggestion
  {

  }

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception
  {
  }


  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception
  {
  }


  @Test
  public void testBasics()
  {
    JpqlQuery<Something> query = new JpqlQuery<Something>(Something.class);

    // Arrange

    query//
        .equals("field1", "x")//
        .notEquals("field2", "x")//
        .ge("field3", 30)//
        .gt("field4", 45)//
        .iequals("field5", "y")//
        .like("field6", "z")//
        .ilike("field7", "a")//
        .isNull("field8")//
        .isNotNull("field9")//
        .in("field10", "b", "c", "d")//
        .addOrdering("field1", true)//
        .setStart(0)//
        .setMaxResults(10);

    // Act
    String jpql = query.toJpql();
    String countJpql = query.toCountJpql();

    // Assert

    Assert.assertEquals(
      "SELECT x FROM Something x  WHERE  x.field1 = 'x' AND x.field2 <> 'x' AND x.field3 >= 30 AND x.field4 > 45 AND x.field5 = 'y' AND x.field6 LIKE '%z%' AND LOWER(x.field7) LIKE LOWER('%a%') AND x.field8 IS NULL AND x.field9 IS NOT NULL AND x.field10 IN ('b','c','d') ORDER BY x.field1 ASC",
      jpql);
    Assert.assertEquals(
      "SELECT COUNT(x) FROM Something x  WHERE  x.field1 = 'x' AND x.field2 <> 'x' AND x.field3 >= 30 AND x.field4 > 45 AND x.field5 = 'y' AND x.field6 LIKE '%z%' AND LOWER(x.field7) LIKE LOWER('%a%') AND x.field8 IS NULL AND x.field9 IS NOT NULL AND x.field10 IN ('b','c','d')",
      countJpql);
  }


  @Test
  public void testNotExists()
  {
    JpqlQuery<Suggestion> subquery = new JpqlQuery<Suggestion>(Suggestion.class)//
        .setAlias("s") //
        .equals("channelId", 5)//
        .equals("contentId", "t.id", true);
    JpqlQuery<Track> query = new JpqlQuery<Track>(Track.class)//
        .setAlias("t")//
        .notExists(subquery);

    // Arrange

    // Act
    String jpql = query.toJpql();
    String countJpql = query.toCountJpql();

    // Assert

    Assert.assertEquals("SELECT t FROM Track t  WHERE  NOT EXISTS (SELECT s FROM Suggestion s  WHERE  s.channelId = 5 AND s.contentId = t.id)", jpql);
    Assert.assertEquals("SELECT COUNT(t) FROM Track t  WHERE  NOT EXISTS (SELECT s FROM Suggestion s  WHERE  s.channelId = 5 AND s.contentId = t.id)", countJpql);

  }

}
