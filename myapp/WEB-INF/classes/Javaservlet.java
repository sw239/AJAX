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
    HttpSession session = req.getSession();
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
    String a="";
    
  		 
    if (!session.isNew()) {
    //out.println("!!!!!");
    // a session exists
    //a=(String)session.getAttribute("username");
    myIntArray =(ArrayList <Integer>)session.getAttribute("username");
    int imid = (int)(Math.random()*imageFiles.length);
               ArrayList <Integer> myIntArray1 = new ArrayList<Integer>();
               while(myIntArray1.size()<num){
                 if(myIntArray.contains(imid)){
                       imid = (int)(Math.random()*imageFiles.length);  
                      }   
                 else if(myIntArray1.contains(imid)){
                       imid = (int)(Math.random()*imageFiles.length);   
                 }
                 else{
                    myIntArray1.add(imid);
                    a+=imageFiles[imid].getName();
                    myIntArray.add(imid);
                 }
  		 }
  		 String c="";
  		 for(int i=0;i<myIntArray.size();i++){
  		   c+=imageFiles[myIntArray.get(i)].getName();
  		 }
  		 out.println(c);session.setAttribute("username", myIntArray);
     
} else {
    // no session
    int imid = (int)(Math.random()*imageFiles.length);
               
               while(myIntArray.size()<num){
                 if(myIntArray.contains(imid)){             
                     imid = (int)(Math.random()*imageFiles.length);
  		 }
  		 else{myIntArray.add(imid);
  		  //out.println(imageFiles[imid].getName());
  		  a+=imageFiles[imid].getName();
  		  }
  		 }
  		 out.println(a);session.setAttribute("username", myIntArray);
  		 
  		 
}
    }
  
}

