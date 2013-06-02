ZooFriends
==========
ABOUT 
This project represents simple type of Zoo and its animal. 
MVC pattern is selected in application design. 

Short description of implementation

Project logically divided into 2 separate parts: 
1) Design of MCV:
Model contains Animals and friend relationships
View extends from JFrame and "draw" in text formal representations of Model to textarea.
Controller monitors button pressing and add remove friends in Model. 
2) Animals classes design: 
Hierarchical model was selected. Where Animal is the super class, and it’s implements logic for friendships and other basic logic. Bird inherited from Animal with extra information, and so on.  
One of the important method is “getAnimalCharactestics”, this method defined in Animal and return array of the string, each string contains simple phrase about animal like “preferred food: grains”, inherited classes override this method by adding own information to the ready array. 

How to run
Open project in Eclipse;
Or:
Compile and run from command line. “mvn package” 

Testing 
Most of the classes from Model are tested with unit tests. 

Coverage
Right now function coverage of the project is 55%. Appendix 1 and Appendix 2 has more details. 
Note:  project is compatible with Sonar. 
To run sonar static of the project:
1)  Please extract sonar to your hard drive http://www.sonarsource.org/downloads/
2)  Run sonar service “StartSonar.bat” or “StartNTService.bat”
3)  Got to the project dir and run  “mvn sonar:sonar” or “mvn sonar:sonar -Pcoverage-per-test“
4)  Open static http://localhost:9000/

Missing Parts
Save and load is not jet implemented. 
Automation with version control system, where automation is able to fetch project from repository, compile, send mail to developers about build success. 
Coverage should be increased to 95%.
