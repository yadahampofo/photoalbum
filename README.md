# PROJECT OVERVIEW: Shape Photo Album Application 
This project uses Object-oriented programming to create a Shape Photo Album Application in which a user can perform a multitude of transformations.

# MOTIVATION
The motivation for this project is to apply SOLID Principles, Separations of Concerns and to create High Quality Design using the Java programming language.

# TECH FRAMEWORK USED

## Model Overview 
The Photo Album Shape Application Model has a series of concrete classes and methods that allow data parsed in to be manipulated based on the Users commands on how to alter the photos within the album and their screenshots. The primary function of the Model in this Design is to ensure that the Photo Album data is properly managed and all transformations are able to occur successfully based on the User’s commands in the terminal. 

## Controller Overview  & Design 
The Photo Album Shape Application Controller contains methods that allow it to successfully serve as the intermediary between the Photo Album Model and Views (Web View &  the Graphical Swing Window). 

The Photo Album Controller Design was created In accordance with SOLID Principles. The PhotoAlbumController concrete class implements the IPhotoAlbumController, which only exposes the Controller class to methods that are necessary to the PhotoAlbumController functionality based on  the Interface Segregation principle.  

Furthermore to allow for dual functionality for both view types, a String view type was included in the PhotoAlbum constructor to serve as the user's way of letting the controller know which View was being requested. Thus, the Go method served as the primary function to create the graphical view object or the webview object. 

The use of this controller design allowed for both views to be serviced without making individual controllers for each of them, thus reducing overall complexity and improving the quality of the program. 

Furthermore, the open-closed principle was also employed in the construction of the Photo Album Controller concrete class. This is evident in that it can be extended to make modifications to the controller class without causing any errors to the existing controller concrete classes.

## View Overview  
The Photo Album Shape Application View has a series of concrete classes and methods that allow the controller to instruct for a web or graphic view based on the user's preferences. The primary function of the View in this Design is to display the Photo Album Snapshots based on the instructions published in the input file.

## View Design Specifications
In accordance with the Interface Segregation principle, the IPhotoAlbumView interface was created to expose view class types to methods that are necessary for their functionality. In this specific case of this PhotoAlbumApplication, the Draw method was the only commonality between both View types.
 
The View also contains a Color Adapter class to ensure that the color inputs passed in from the user are recognized by java.awt.color class. This ensured a seamless translation between the model color object and the java.awt.color object. The Dependency Inversion principle was also applied in the creation of this class. The use of composition has ensured that the color adapter class is not dependent on any of the IPhotoAlbumView concrete classes and that they are decoupled. 

The Web View Concrete class and the GraphicalSwingView concrete class are separated to ensure low coupling between both classes and allow for increased flexibility in code construction. 
