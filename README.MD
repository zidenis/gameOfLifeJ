## Game of Life
### v1.0 - 06/12/2014
@zidenis
http://github.com/zidenis

To run this game of life, simply double click the GoL.jar file. 

To download: http://github.com/zidenis/gameOfLifeJ/releases

Use the swing window to run your simulations.
![GUI](https://cloud.githubusercontent.com/assets/4968411/5328682/e0203014-7d70-11e4-95cb-3ae1238dca5a.png)
**Additional rules can be put in ExtraRules.json file.** 
For this, you need to use JSON format. E.g.:
	
>	{
>	  "rulestring" : "Assimilation", 
>	  "survivalSet" : [4, 5, 6, 7],
>	  "birthSet" : [3, 4, 5]
>	}

To run from console/terminal (**try this**), use the command bellow:

$ java -jar GoL.jar

This way you will also see the "game grid" on terminal.
![Console UI](https://cloud.githubusercontent.com/assets/4968411/5328686/06c80796-7d71-11e4-96fa-adecf53fab1d.png)
