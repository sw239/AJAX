# COMPX575 - Task Seven
Due on **Friday the 16th of April at midday**.

# Task Goal
Learn about:
* Hosting files on a web server (Jetty)
* Linking image files in a HTML file
* Simple JavaScript programming
* Dynamically updating a page using AJAX (Asynchronous JavaScript and XML)

## Preamble
0. In the previous exercise you learned to set up a simple web app, you may want to revise that as you are building on that this week.
1. Fork this repository using the button at the top of the project page.
2. Make sure that the visibility of your project is private. (Settings > expand Permissions > Project visibility: Private; Save changes).  *Note: Class teachers and tutors will still have read access to your project for marking purposes.*
3. Clone the new repository to your computer using Git.  Store the repository in your COMPX575 directory.
4. Remember to commit and push regularly as you work on the project!  

### Part 1: Host files on the Jetty Server
1. Create a directory and initialise Jetty inside it:
```
mkdir COMPX575AssignmentSeven/mybase (or whatever you want to call it)
cd COMPX575AssignmentSeven/mybase
export JETTY_HOME=/home/compx575/jetty/jetty-9.4.9
java -jar $JETTY_HOME/start.jar --add-to-start=http,deploy
```
Remember that you will need to run *export JETTY_HOME=...* in each new terminal window.

2. Create the directory that you will be building your app in and add a *images* directory:
```
mkdir COMPX575AssignmentSeven/myapp
mkdir COMPX575AssignmentSeven/myapp/images
```
Find a few images and put them in that *images* directory.

3. Create *index.html* inside *myapp* that contains a link to one of the images:
```
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>AJAX Exercises</title>
    </head>
    <body>
        <img id="image" src="images/image1.jpg">
        <p id="image-text">the first image</p>
    </body>
</html>
```

4. In the *myapp* directory, zip the directory *images* and *index.html* into *myapp.war*: *jar cvf myapp.war images index.html*

5. Move *myapp.war* to *webapps* (which was created when you initialised Jetty)
6. Write a configuration file *myapp.xml* and put it into *webapps* (see *helloworld.xml* from the previous exercise for an example). Set the *contextPath* and *war* fields to appropriate values.
7. Start up the Jetty server: *java -jar $JETTY_HOME/start.jar*
8. In a browser, navigate to *http://localhost:8080/xxx*

Note that 8080 is the port number of your Jetty server (unless you changed it).  Replace *xxx* with the *contextPath* specified in your configuration file.

### Part 2: Write some JavaScript to load and display an image
1. In *index.html*, add a button: *\<input id="load-image" type="button" value="load an image" onclick="loadImage()">*
2. At the end of the *\<head>* element, insert:
```
<script>
    function loadImage() {
        var image = document.getElementById("image");
        image.src = "images/image2.jpg";
        var image_text = document.getElementById("image-text");
        image_text.textContent = "the second image";
    }
</script>
```
3. In the *myapp* directory, zip the directory *images* and *index.html* into *myapp.war* and move it into *webapps*.
4. In a browser, open *http://localhost:8080/xxx* and click the *load an image* button.  If things go wrong, look for errors in Jetty's output and in the browser's console. 

### Part 3: Write a web app that retrieves image file names
1. In *myapp*, create a directory called *WEB-INF* and create a directory *classes*.
2. In *classes*, write a Java servlet that reads the *images* directory, retrieves all the file names and returns one at random.  Here is how to get all the files in a directory:
```
File imagesDir = new File(getServletContext().getRealPath("/images"));
File[] imageFiles = imagesDir.listFiles();
```
Note:
* you need to import the IO package using *java.io.\*;*
* the *doGet()* method should just print a filename, without any HTML tags
* use *Random* to generate random numbers
* to get the name of an image, without the full path, use *imageFiles[i].getName()*

3. Compile the Java servlet, write a *web.xml*.
4. Zip the contents of the *myapp* directory: *jar cvf myapp.war images WEB-INF index.html*
Move *myapp.war* into *webapps*
5. In a browser, open *http://localhost:8080/xxx/yourservlet* (replace yourservlet with the servlet mapping you specified in *web.xml*). You should get a different image name each time you refresh this page.

### Part 4: Use AJAX to load images dynamically
1. Read about AJAX:
    * AJAX Introduction on W3Schools: https://www.w3schools.com/xml/ajax_intro.asp
    * AJAX on MDN: https://developer.mozilla.org/en-US/docs/Web/Guide/AJAX
2. Open *index.html* in a text editor.
3. This is the code that sends an AJAX request to the servlet you implemented above:
```
var url = "http://localhost:8080/xxx/yourservlet";
var xhr = new XMLHttpRequest();
xhr.addEventListener("load", function() {
    if (this.status == 200) {
        // loading succeeded
        alert(this.responseText);
    } else {
        console.error("error fetching " + url);
        console.error("status code: " + this.status + " " + this.statusText);
    }
});
xhr.open("GET", url);
xhr.send();
```
Your job is to put this code into the *loadImage* function so that when the user clicks the load an image button, a new image is obtained from the server and the text underneath is changed to its file name.

4. Make a copy of your servlet that returns N different random image names separated by commas (e.g. "1.jpg,2.jpg,3.jpg"), where N is specified by a parameter in the URL called numImages. The URL sent to your servlet will look like: *http://localhost:8080/xxx/yourservlet?numImages=8*

Hint: Use *HttpServletRequest*'s *getParameter* method to get the value of *numImages*.

5. Make a copy of your original servlet and use a session (which you can obtain using HttpServletRequest.getSession) to remember the images the user has seen. Each time return an image that hasn't been seen yet.

Useful link: http://www.tutorialspoint.com/servlets/servlets-session-tracking.htm

## Grading
This task is worth as much as 5% of your final grade and is marked out of 5.
* Parts 1, 2, and 3 are worth 1 mark each.
* Part 4 is worth two marks.

## Submission
On top of the commits to your Git repository, please upload your source files to moodle.