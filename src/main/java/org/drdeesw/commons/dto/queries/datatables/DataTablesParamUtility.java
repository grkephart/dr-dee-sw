package org.drdeesw.commons.dto.queries.datatables;


import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.MultiValueMap;


/**
 * @see https://datatables.net/manual/index
 * @author gary_kephart
 *
 */
public class DataTablesParamUtility implements Serializable
{
  private static final long                   serialVersionUID         = -8101240805709345774L;
  private static Log                          log                      = LogFactory
      .getLog(DataTablesParamUtility.class);
  private static final String                 SEARCH_CASE_KEY          = "search[caseInsensitive]";
  private static final String                 SEARCH_REGEX_KEY         = "search[regex]";
  private static final String                 SEARCH_VALUE_KEY         = "search[value]";
  public static final String                  LENGTH_KEY               = "length";
  public static final String                  START_KEY                = "start";
  public static final String                  DRAW_KEY                 = "draw";
  private static final String                 COL_DATA_REGEX           = "columns\\[(\\d+)]\\[data]";
  private static final String                 COL_NAME_REGEX           = "columns\\[(\\d+)]\\[name]";
  private static final String                 COL_ORDERABLE_REGEX      = "columns\\[(\\d+)]\\[orderable]";
  private static final String                 COL_SEARCH_REGEX_REGEX   = "columns\\[(\\d+)]\\[search]\\[regex]";
  private static final String                 COL_SEARCH_VALUE_REGEX   = "columns\\[(\\d+)]\\[search]\\[value]";
  private static final String                 COL_SEARCHABLE_REGEX     = "columns\\[(\\d+)]\\[searchable]";
  private static final String                 ORDER_COLUMN_REGEX       = "order\\[(\\d+)]\\[column]";
  private static final String                 ORDER_DIR_REGEX          = "order\\[(\\d+)]\\[dir]";
  private static final Pattern                COL_DATA_PATTERN         = Pattern
      .compile(COL_DATA_REGEX);
  private static final Pattern                COL_NAME_PATTERN         = Pattern
      .compile(COL_NAME_REGEX);
  private static final Pattern                COL_ORDERABLE_PATTERN    = Pattern
      .compile(COL_ORDERABLE_REGEX);
  private static final Pattern                COL_SEARCH_REGEX_PATTERN = Pattern
      .compile(COL_SEARCH_REGEX_REGEX);
  private static final Pattern                COL_SEARCH_VALUE_PATTERN = Pattern
      .compile(COL_SEARCH_VALUE_REGEX);
  private static final Pattern                COL_SEARCHABLE_PATTERN   = Pattern
      .compile(COL_SEARCHABLE_REGEX);
  private static final Pattern                ORDER_COLUMN_PATTERN     = Pattern
      .compile(ORDER_COLUMN_REGEX);
  private static final Pattern                ORDER_DIR_PATTERN        = Pattern
      .compile(ORDER_DIR_REGEX);
  private static final Map<Pattern, Class<?>> INDEXED_PATTERNS         = initPatterns();
  private static final String                 PERFORM_COUNT_KEY        = "performCount";
  //  private static final Pattern                SINGLE_VALUE_PATTERN     = Pattern
  //                                                                           .compile("\\['(\\d+)',\\w*]");

  private static int getMaxIndex(
    Map<Integer, ?> values)
  {
    int maxIndex = 0;

    for (Integer key : values.keySet())
    {
      int index = key.intValue() + 1;

      if (index > maxIndex)
        maxIndex = index;
    }

    return maxIndex;
  }


  @SuppressWarnings("unchecked")
  public static JQueryDataTableParamModel getParamModel(
    MultiValueMap<String, String> parameterMap)
  {
    JQueryDataTableParamModel param = new JQueryDataTableParamModel();
    Matcher m;
    Map<Pattern, Map<Integer, ? extends Object>> values = new HashMap<Pattern, Map<Integer, ? extends Object>>();

    for (Entry<String, List<String>> entry : parameterMap.entrySet())
    {
      List<String> parameter = entry.getValue();
      String[] paramValues = new String[parameter.size()];

      paramValues = parameter.toArray(paramValues);

      log.debug("[getParamModel] " + entry.getKey() + " = " + Arrays.toString(paramValues));
    }

    values.put(COL_DATA_PATTERN, new HashMap<Integer, String>());
    values.put(COL_NAME_PATTERN, new HashMap<Integer, String>());
    values.put(COL_ORDERABLE_PATTERN, new HashMap<Integer, Boolean>());
    values.put(COL_SEARCH_REGEX_PATTERN, new HashMap<Integer, Boolean>());
    values.put(COL_SEARCH_VALUE_PATTERN, new HashMap<Integer, String>());
    values.put(COL_SEARCHABLE_PATTERN, new HashMap<Integer, Boolean>());

    values.put(ORDER_COLUMN_PATTERN, new HashMap<Integer, Integer>());
    values.put(ORDER_DIR_PATTERN, new HashMap<Integer, Boolean>());

    for (Entry<String, List<String>> entry : parameterMap.entrySet())
    {
      String key = entry.getKey();
      List<String> valuesArray = entry.getValue();

      if (valuesArray != null && valuesArray.size() > 0)
      {
        String value = valuesArray.get(0);

        if (key.equals(DRAW_KEY))
        {
          try
          {
            param.setDraw(Integer.valueOf(value));
          }
          catch (NumberFormatException e)
          {
            param.setDraw(Integer.valueOf(0));
          }
        }
        else if (key.equals(START_KEY))
        {
          try
          {
            param.setStart(Integer.valueOf(value));
          }
          catch (NumberFormatException e)
          {
            // ignore param
          }
        }
        else if (key.equals(LENGTH_KEY))
        {
          try
          {
            param.setLength(Integer.valueOf(value));
          }
          catch (NumberFormatException e)
          {
            // ignore param
          }
        }
        else if (key.equals(SEARCH_CASE_KEY))
        {
          param.setSearchCaseInsensitive(Boolean.valueOf(value));
        }
        else if (key.equals(SEARCH_VALUE_KEY))
        {
          param.setSearchValue(value);
        }
        else if (key.equals(SEARCH_REGEX_KEY))
        {
          param.setSearchRegex(Boolean.valueOf(value));
        }
        else if (key.equals(PERFORM_COUNT_KEY))
        {
          param.setPerformCount(Boolean.valueOf(value));
        }
        else
        {
          boolean isIndexedPattern = false;

          for (Entry<Pattern, Class<?>> entry2 : INDEXED_PATTERNS.entrySet())
          {
            Pattern indexedPattern = entry2.getKey();

            m = indexedPattern.matcher(key);

            if (m.matches())
            {
              Integer index = Integer.valueOf(m.group(1));
              Class<?> clazz = entry2.getValue();

              isIndexedPattern = true;

              if (clazz.equals(Boolean.class))
              {
                Map<Integer, Boolean> patternValues = (Map<Integer, Boolean>)values
                    .get(indexedPattern);
                Boolean castValue;

                if ("asc".equals(value))
                  castValue = Boolean.TRUE;
                else if ("desc".equals(value))
                  castValue = Boolean.FALSE;
                else
                  castValue = Boolean.valueOf(value);

                patternValues.put(index, castValue);
              }
              else if (clazz.equals(String.class))
              {
                Map<Integer, String> patternValues = (Map<Integer, String>)values
                    .get(indexedPattern);

                patternValues.put(index, value);
              }
              else if (clazz.equals(Integer.class))
              {
                Map<Integer, Integer> patternValues = (Map<Integer, Integer>)values
                    .get(indexedPattern);

                try
                {
                  patternValues.put(index, Integer.valueOf(value));
                }
                catch (NumberFormatException e)
                {
                  // ignore param
                }
              }

              break;
            } // end if matches pattern
          } // end loop thru indexed patterns

          if (!isIndexedPattern && !"_".equals(key))
          {
            try
            {
              Long longValue = Long.parseLong(value);

              param.setAjaxData(key, longValue);
            }
            catch (NumberFormatException e)
            {
              param.setAjaxData(key, value);
            }
          }
        } // end if check for indexed params
      } // end if there are param values
    } // end loop through params

    param.setColumnData(toStringArray((Map<Integer, String>)values.get(COL_DATA_PATTERN)));
    param.setColumnNames(toStringArray((Map<Integer, String>)values.get(COL_NAME_PATTERN)));
    param.setColumnOrderables(
      toBooleanArray((Map<Integer, Boolean>)values.get(COL_ORDERABLE_PATTERN)));
    param.setColumnSearchable(
      toBooleanArray((Map<Integer, Boolean>)values.get(COL_SEARCHABLE_PATTERN)));
    param.setColumnSearchRegex(
      toBooleanArray((Map<Integer, Boolean>)values.get(COL_SEARCH_REGEX_PATTERN)));
    param.setColumnSearchValues(
      toStringArray((Map<Integer, String>)values.get(COL_SEARCH_VALUE_PATTERN)));

    param.setOrderColumns(toIntArray((Map<Integer, Integer>)values.get(ORDER_COLUMN_PATTERN)));
    param.setOrderDirs(toBooleanArray((Map<Integer, Boolean>)values.get(ORDER_DIR_PATTERN)));

    return param;
  }


  private static Map<Pattern, Class<?>> initPatterns()
  {
    Map<Pattern, Class<?>> patterns = new HashMap<Pattern, Class<?>>();

    patterns.put(COL_DATA_PATTERN, String.class);
    patterns.put(COL_NAME_PATTERN, String.class);
    patterns.put(COL_ORDERABLE_PATTERN, Boolean.class);
    patterns.put(COL_SEARCH_REGEX_PATTERN, Boolean.class);
    patterns.put(COL_SEARCH_VALUE_PATTERN, String.class);
    patterns.put(COL_SEARCHABLE_PATTERN, Boolean.class);

    patterns.put(ORDER_COLUMN_PATTERN, Integer.class);
    patterns.put(ORDER_DIR_PATTERN, Boolean.class);

    return patterns;
  }


  private static boolean[] toBooleanArray(
    Map<Integer, Boolean> values)
  {
    boolean[] array = new boolean[getMaxIndex(values)];

    for (Entry<Integer, Boolean> entry : values.entrySet())
    {
      array[entry.getKey().intValue()] = entry.getValue().booleanValue();
    }

    return array;
  }


  private static int[] toIntArray(
    Map<Integer, Integer> values)
  {
    int[] array = new int[getMaxIndex(values)];

    for (Entry<Integer, Integer> entry : values.entrySet())
    {
      array[entry.getKey().intValue()] = entry.getValue().intValue();
    }

    return array;
  }


  private static String[] toStringArray(
    Map<Integer, String> values)
  {
    String[] array = new String[getMaxIndex(values)];

    for (Entry<Integer, String> entry : values.entrySet())
    {
      array[entry.getKey().intValue()] = entry.getValue();
    }

    return array;
  }
}
