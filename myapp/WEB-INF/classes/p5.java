import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.net.* ;


public class Javaservlet extends HttpServlet {
  public void doGet (HttpServletRequest req,
                     HttpServletResponse res)
    throws ServletException, IOException
  {
    
    PrintWriter out = res.getWriter();
    String id = req.getParameter("numImages");
    //public static ArrayList <Integer> myIntArray;
    res.setContentType("text/plain");
   
    int num=Integer.parseInt(id);  
    //out.println("Id is " + num);
    File imagesDir = new File(getServletContext().getRealPath("/images"));
    File[] imageFiles = imagesDir.listFiles();
    ArrayList <Integer> myIntArray = new ArrayList<Integer>();
    int result = (int)(Math.random()*imageFiles.length);
    
               int imid = (int)(Math.random()*imageFiles.length);
               
               while(myIntArray.size()<num){
                 if(myIntArray.contains(imid)){             
                     imid = (int)(Math.random()*imageFiles.length);
  		 }
  		 else{myIntArray.add(imid);
  		  out.println(imageFiles[imid].getName());}
  		 }
	
	
    

  }
  
}

