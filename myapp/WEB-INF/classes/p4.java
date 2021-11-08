import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.net.* ;


public class p4 extends HttpServlet {
  public void doGet (HttpServletRequest req,
                     HttpServletResponse res)
    throws ServletException, IOException
  {
    
    PrintWriter out = res.getWriter();
    File imagesDir = new File(getServletContext().getRealPath("/images"));
    File[] imageFiles = imagesDir.listFiles();
    ArrayList <Integer> myIntArray = new ArrayList<Integer>(imageFiles.length);
    int result = (int)(Math.random()*imageFiles.length);
    //for (int i = 0; i < result+1; i++) {
               int imid = (int)(Math.random()*imageFiles.length);
               //if(!myIntArray.contains(imid)){             
                 //myIntArray.add(imid);
  		 out.println(imageFiles[imid].getName());
  		 //}
	//}
    

  }
} 
