package org.drdeesw.commons.codegen;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;


public class CodeGenerator implements Runnable
{
  private static final String CODEGEN_PROJECT_FILENAME = "codegen.properties";
  private static final String PROJECT_RSC_DIR_NAME = "src\\main\\resources";
  private static final String PROJECT_CODEGEN_RSC_DIR_NAME = PROJECT_RSC_DIR_NAME + "\\codegen";

  public static void main(
    String[] args)
  {
    if (args.length > 0)
    {
      try
      {
        CodeGenerator app = new CodeGenerator(args);

        app.run();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    else
      System.err.println("Usage: CodeGenerator projectDirName");
  }


  private Properties projectProps;
  
  private CodeGenerator(String[] args) throws IOException
  {
    String projectDirName = args[0];
    File projectFile = new File(projectDirName + "\\" + PROJECT_CODEGEN_RSC_DIR_NAME, CODEGEN_PROJECT_FILENAME);
    FileReader projectFileReader = new FileReader(projectFile);

    this.projectProps = new Properties();
    this.projectProps.load(projectFileReader);
  }


  @Override
  public void run()
  {
    for (Entry<Object,Object> projectProp : this.projectProps.entrySet())
    {
      String key = (String)projectProp.getKey();
      
      System.out.println(key);

      if (key.startsWith("entities."))
      {
      }
    }
  }

}
