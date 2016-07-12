Narrative:  
Send message to a NASA rover
  
Scenario: Send message to rover
  
Given a NASA rover <id> 
When I send the NASA rover a command message <message>
Then the final NASA rover location should be <location>
  
Examples:  
id|message|location|  
1|5 5,1 2 N,LMLMLMLMM,3 3 E,MMRMMRMRRM|1 3 N|
2|5 5,1 2 N,LMLMLMLMM,3 3 E,MMRMMRMRRM|5 1 E|