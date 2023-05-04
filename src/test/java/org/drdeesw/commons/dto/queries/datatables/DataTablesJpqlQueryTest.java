/**
 * 
 */
package org.drdeesw.commons.dto.queries.datatables;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;


/**
 * @author gary_kephart
 *
 */
public class DataTablesJpqlQueryTest
{

  static class Content
  {

  }

  static class Reaction
  {

  }

  /**
   * @param paramStr
   * @return
   */
  private MultiValueMap<String, String> convert(
    String paramStr)
  {
    String[] tokens = paramStr.split("&");
    Map<String, List<String>> map = new HashMap<String, List<String>>();
    MultiValueMap<String, String> parameterMap = new MultiValueMapAdapter<String, String>(map);

    for (String token : tokens)
    {
      if (token.contains("="))
      {
        String[] entry = token.split("=");

        if (entry.length > 1)
          parameterMap.add(entry[0], entry[1]);
      }
    }

    return parameterMap;
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
  public void test1() throws Exception
  {
    String paramStr = "draw=1&columns[0][data]=name&columns[0][name]=&columns[0][searchable]=true&columns[0][orderable]=true&columns[0][search][value]=&columns[0][search][regex]=false&columns[1][data]=content.name&columns[1][name]=&columns[1][searchable]=true&columns[1][orderable]=true&columns[1][search][value]=&columns[1][search][regex]=false&columns[2][data]=reactionDate&columns[2][name]=&columns[2][searchable]=false&columns[2][orderable]=true&columns[2][search][value]=&columns[2][search][regex]=false&columns[3][data]=viewCount&columns[3][name]=&columns[3][searchable]=false&columns[3][orderable]=true&columns[3][search][value]=&columns[3][search][regex]=false&columns[4][data]=likeCount&columns[4][name]=&columns[4][searchable]=false&columns[4][orderable]=true&columns[4][search][value]=&columns[4][search][regex]=false&order[0][column]=2&order[0][dir]=desc&start=0&length=10&search[value]=&search[regex]=true&_=1683124992373";
    MultiValueMap<String, String> parameterMap = convert(paramStr);
    DataTablesJpqlQuery<Reaction> query = new DataTablesJpqlQuery<Reaction>(Reaction.class,
        parameterMap);

    // Arrange

    // Act
    String jpql = query.toJpql();
    String countJpql = query.toCountJpql();

    // Assert

    Assert.assertEquals("SELECT x FROM Reaction x  ORDER BY x.reactionDate DESC", jpql);
    Assert.assertEquals("SELECT COUNT(x) FROM Reaction x ", countJpql);
  }


  @Test
  public void test2() throws Exception
  {
    String paramStr = "draw=8&columns[0][data]=name&columns[0][name]=&columns[0][searchable]=true&columns[0][orderable]=true&columns[0][search][value]=&columns[0][search][regex]=false&columns[1][data]=reactionCount&columns[1][name]=&columns[1][searchable]=false&columns[1][orderable]=true&columns[1][search][value]=&columns[1][search][regex]=false&order[0][column]=1&order[0][dir]=desc&start=0&length=10&search[value]=beatles&search[regex]=true&_=1683124992381";
    MultiValueMap<String, String> parameterMap = convert(paramStr);
    DataTablesJpqlQuery<Content> query = new DataTablesJpqlQuery<Content>(Content.class,
        parameterMap);

    // Arrange

    // Act
    String jpql = query.toJpql();
    String countJpql = query.toCountJpql();

    // Assert

    Assert.assertEquals("SELECT x FROM Content x  ORDER BY x.reactionCount DESC", jpql);
    Assert.assertEquals("SELECT COUNT(x) FROM Content x ", countJpql);
  }

}
